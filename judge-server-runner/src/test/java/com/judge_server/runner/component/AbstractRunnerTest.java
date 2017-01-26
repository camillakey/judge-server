package com.judge_server.runner.component;

import com.judge_server.runner.dto.RunnerRequest;
import com.judge_server.runner.dto.RunnerResult;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public abstract class AbstractRunnerTest {

    @Test
    public void success() throws Exception {
        TestData testData = getSuccessTestData();
        RunnerResult result = run(testData);

        assertThat(result.getState(), is(RunnerResult.State.Success));
        assertThat(result.getStdout(), is(testData.getOutput()));
        assertThat(result.getStderr(), is(""));
        assertThat(result.getUsedTime(), is(greaterThanOrEqualTo(0L)));
        assertThat(result.getUsedMemory(), is(greaterThanOrEqualTo(0L)));
        assertThat(result.isTimeLimited(), is(false));
        assertThat(result.isMemoryLimited(), is(false));
    }

    @Test
    public void compileError() throws Exception {
        TestData testData = getCompileErrorTestData();
        RunnerResult result = run(testData);

        assertThat(result.getState(), is(RunnerResult.State.CompileError));
        assertThat(result.getCompileMessage(), is(not("")));
    }

    @Test
    public void runtimeError() throws Exception {
        TestData testData = getRuntimeErrorTestData();
        RunnerResult result = run(testData);

        assertThat(result.getState(), is(RunnerResult.State.RuntimeError));
    }

    @Test
    public void timeLimited() throws Exception {
        TestData testData = getTimeLimitedTestData();
        RunnerResult result = run(testData);

        assertThat(result.getState(), is(RunnerResult.State.TimeLimited));
        assertThat(result.getUsedTime(), is(greaterThanOrEqualTo(defaultTimeLimit())));
        assertThat(result.isTimeLimited(), is(true));
    }

    @Test
    public void memoryLimited() throws Exception {
        TestData testData = getMemoryLimitedTestData();
        RunnerResult result = run(testData);

        assertThat(result.getState(), is(RunnerResult.State.MemoryLimited));
        assertThat(result.getUsedMemory(), is(greaterThanOrEqualTo(defaultMemoryLimit())));
        assertThat(result.isMemoryLimited(), is(true));
    }


    abstract RunnerRequest.Language language();

    long defaultTimeLimit() {
        return 2000L;
    }

    long defaultMemoryLimit() {
        return 65536L;
    }


    private RunnerResult run(TestData testData) {
        RunnerRequest runnerRequest = new RunnerRequest(
                language(), testData.getSource(), testData.getInput(), defaultTimeLimit(), defaultMemoryLimit());

        return run(runnerRequest);
    }

    abstract RunnerResult run(RunnerRequest runnerRequest);


    private TestData getSuccessTestData() {
        return getTestData(getTestDataDirectoryPath() + File.separator + "success");
    }

    private TestData getCompileErrorTestData() {
        return getTestData(getTestDataDirectoryPath() + File.separator + "compileError");
    }

    private TestData getRuntimeErrorTestData() {
        return getTestData(getTestDataDirectoryPath() + File.separator + "runtimeError");
    }

    private TestData getTimeLimitedTestData() {
        return getTestData(getTestDataDirectoryPath() + File.separator + "timeLimited");
    }

    private TestData getMemoryLimitedTestData() {
        return getTestData(getTestDataDirectoryPath() + File.separator + "memoryLimited");
    }

    private String getTestDataDirectoryPath() {
        return System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "resources"
                + File.separator + "test"
                + File.separator + language().toString();
    }


    private static TestData getTestData(String directory) {
        File src = new File(directory + File.separator + "src.txt");
        File in = new File(directory + File.separator + "in.txt");
        File out = new File(directory + File.separator + "out.txt");

        if (src.exists() && in.exists() && out.exists()) {
            try {
                return new TestData(read(src), read(in), read(out));
            } catch (IOException e) {
                throw new TestDataReadException(e);
            }
        } else {
            throw new TestDataReadException();
        }
    }

    private static String read(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String result = "";

            String str;
            while((str = br.readLine()) != null) {
                result += str + System.lineSeparator();
            }

            return result;
        }
    }


    static class TestData {
        private String source;
        private String input;
        private String output;

        TestData(String source, String input, String output) {
            this.source = source;
            this.input = input;
            this.output = output;
        }

        String getSource() {
            return source;
        }

        String getInput() {
            return input;
        }

        String getOutput() {
            return output;
        }
    }

    static class TestDataReadException extends RuntimeException {
        TestDataReadException() {
        }

        TestDataReadException(String message) {
            super(message);
        }

        TestDataReadException(String message, Throwable cause) {
            super(message, cause);
        }

        TestDataReadException(Throwable cause) {
            super(cause);
        }

        TestDataReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
