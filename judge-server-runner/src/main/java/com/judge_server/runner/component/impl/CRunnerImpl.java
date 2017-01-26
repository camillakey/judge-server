package com.judge_server.runner.component.impl;

import com.judge_server.runner.component.CRunner;
import org.springframework.stereotype.Component;

@Component
public class CRunnerImpl extends AbstractRunnerImpl implements CRunner {

    @Override
    String getExtension() {
        return ".c";
    }

    @Override
    String getCompileCommand(String target) {
        return "gcc -o " + target + ".out " + target + ".c";
    }

    @Override
    String getExecuteCommand(String target) {
        return "./" + target + ".out";
    }

}
