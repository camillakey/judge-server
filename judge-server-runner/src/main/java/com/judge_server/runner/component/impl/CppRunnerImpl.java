package com.judge_server.runner.component.impl;

import com.judge_server.runner.component.CppRunner;
import org.springframework.stereotype.Component;

@Component
public class CppRunnerImpl extends AbstractRunnerImpl implements CppRunner {

    @Override
    String getExtension() {
        return ".cpp";
    }

    @Override
    String getCompileCommand(String target) {
        return "g++ -o " + target + ".out " + target + ".cpp";
    }

    @Override
    String getExecuteCommand(String target) {
        return "./" + target + ".out";
    }

}
