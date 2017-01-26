package com.judge_server.sandbox.component;

import com.judge_server.sandbox.core.Sandbox;
import com.judge_server.sandbox.dto.SandboxEnvironment;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoSandboxComponentTest extends AbstractSandboxComponentTest {

    @Autowired
    @Qualifier("noSandbox")
    private SandboxComponent sandboxComponent;

    @Override
    Sandbox create(SandboxEnvironment sandboxEnvironment) {
        return sandboxComponent.create(sandboxEnvironment);
    }

    @Override
    public void memoryLimited() throws Exception {
        fail();
    }

}
