package com.judge_server.sandbox.component;

import com.judge_server.sandbox.core.Sandbox;
import com.judge_server.sandbox.dto.SandboxEnvironment;
import com.judge_server.sandbox.dto.SandboxResult;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public abstract class AbstractSandboxComponentTest {

    @Test
    public void success() throws Exception {
        try (Sandbox sandbox = create(new SandboxEnvironment(1000L, 65536L))) {
            assertThat(sandbox.run("echo \"aaa\" > test.txt").getExitValue(), is(0));

            SandboxResult sandboxResult = sandbox.run("ls -l");

            assertThat(sandboxResult.getExitValue(), is(0));
            assertThat(sandboxResult.getStdout(), is(not("")));
            assertThat(sandboxResult.getStderr(), is(""));
            assertThat(sandboxResult.getUsedTime(), is(greaterThan(0L)));
            assertThat(sandboxResult.isTimeLimited(), is(false));
        }
    }

    @Test
    public void timeLimited() throws Exception {
        try (Sandbox sandbox = create(new SandboxEnvironment(1000L, 65536L))) {
            SandboxResult result = sandbox.run("sleep 5");
            assertThat(result.getExitValue(), is(not(0)));
            assertThat(result.isTimeLimited(), is(true));
        }
    }

    @Test
    public void memoryLimited() throws Exception {
        try (Sandbox sandbox = create(new SandboxEnvironment(1000L, 65536L))) {
            SandboxResult result = sandbox.run("/dev/null < $(yes)");
            assertThat(result.getExitValue(), is(not(0)));
            assertThat(result.isMemoryLimited(), is(true));
        }
    }

    abstract Sandbox create(SandboxEnvironment sandboxEnvironment);

}
