package com.judge_server.sandbox.component.impl;

import com.judge_server.sandbox.component.SandboxComponent;
import com.judge_server.sandbox.core.Sandbox;
import com.judge_server.sandbox.dto.SandboxEnvironment;
import com.judge_server.sandbox.dto.SandboxResult;
import com.judge_server.sandbox.exception.SandboxException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Execution on system call.
 */
@Component("noSandbox")
@Primary
public class NoSandboxComponentImpl implements SandboxComponent {

    @Override
    public Sandbox create(SandboxEnvironment sandboxEnvironment) {
        return new NoSandbox(sandboxEnvironment);
    }

    /**
     * Sandbox that uses system call.
     */
    private static class NoSandbox implements Sandbox {

        /**
         * Sandbox environment.
         */
        private SandboxEnvironment sandboxEnvironment;

        /**
         * Current directory.
         */
        private File directory;

        /**
         * Constructs with sandbox environment.
         * @param sandboxEnvironment sandbox environment.
         */
        private NoSandbox(SandboxEnvironment sandboxEnvironment) {
            this.sandboxEnvironment = sandboxEnvironment;
            this.directory = new File(System.getProperty("user.dir") + File.separator + UUID.randomUUID().toString());
            this.directory.mkdir();
        }

        @Override
        public SandboxResult run(String command) throws SandboxException {
            return run(command, "");
        }

        @Override
        public SandboxResult run(String command, String stdin) throws SandboxException {
            try {
                long currentTime = System.currentTimeMillis();

                Process process = new ProcessBuilder("bash", "-c", command).directory(directory).start();

                BufferedOutputStream stdoutStream = new BufferedOutputStream(process.getOutputStream());
                stdoutStream.write(stdin.getBytes());
                stdoutStream.close();

                InputStreamTask stdoutTask = new InputStreamTask(process.getInputStream());
                InputStreamTask stderrTask = new InputStreamTask(process.getErrorStream());

                ExecutorService executorService = Executors.newCachedThreadPool();
                Future stdoutTaskFuture = executorService.submit(stdoutTask);
                Future stderrTaskFuture = executorService.submit(stderrTask);

                boolean finished = process.waitFor(sandboxEnvironment.getTimeLimit(), TimeUnit.MILLISECONDS);
                if (!finished) {
                    process.destroyForcibly();
                }

                stdoutTaskFuture.get();
                stderrTaskFuture.get();

                executorService.shutdown();


                String stdout = stdoutTask.getString();
                String stderr = stderrTask.getString();
                int exitValue = finished ? process.exitValue() : -1;
                long usedTime = System.currentTimeMillis() - currentTime;

                SandboxResult sandboxResult = new SandboxResult(exitValue, stdout, stderr);
                sandboxResult.setUsedTime(usedTime);
                sandboxResult.setTimeLimited(!finished);

                return sandboxResult;
            } catch (IOException
                    | ExecutionException
                    | InterruptedException e) {
                throw new SandboxException(e);
            }
        }

        @Override
        public void close() throws SandboxException {
            FileSystemUtils.deleteRecursively(directory);
        }
    }

    /**
     * Input task.
     */
    private static class InputStreamTask implements Runnable {

        /**
         * Input stream.
         */
        private InputStream inputStream;

        /**
         * String builder used to read strings.
         */
        private StringBuilder stringBuilder;

        /**
         * Constructs InputStreamTask.
         * @param inputStream input stream.
         */
        private InputStreamTask(InputStream inputStream) {
            this.inputStream = inputStream;
            this.stringBuilder = new StringBuilder();
        }

        @Override
        public void run() {
            try (BufferedInputStream bis = new BufferedInputStream(inputStream)) {
                int ch;

                while ((ch = bis.read()) != -1) {
                    stringBuilder.append(Character.toChars(ch));
                }

                bis.close();
            } catch (IOException e) {
                throw new SandboxException(e);
            }
        }

        /**
         * Returns read string.
         * @return read string.
         */
        String getString() {
            return stringBuilder.toString();
        }
    }
}
