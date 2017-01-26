package com.judge_server.runner.component.impl;

import com.judge_server.runner.component.JavaRunner;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JavaRunnerImpl extends AbstractRunnerImpl implements JavaRunner {

    /**
     * Returns public class name.
     * @param source source code.
     * @return public class name.
     */
    @Override
    String getTarget(String source) {
        Matcher declarationMatcher = Pattern.compile("public\\s+class\\s+\\w+(\\s+\\w+)*\\s*\\{")
                .matcher(source.replaceAll(System.lineSeparator(), " "));

        if (declarationMatcher.find()) {
            String declaration = declarationMatcher.group();

            Matcher beforeMatcher = Pattern.compile("public\\s+class\\s+").matcher(declaration);
            beforeMatcher.find();

            String before = beforeMatcher.group();
            String after = declaration.substring(before.length());

            int indexOfSpace = after.indexOf(" ");
            int indexOfBrace = after.indexOf("{");

            if (indexOfSpace != -1) {
                return after.substring(0, indexOfSpace);
            } else {
                return after.substring(0, indexOfBrace);
            }
        } else {
            return super.getTarget(source);
        }
    }

    @Override
    String getExtension() {
        return ".java";
    }

    @Override
    String getCompileCommand(String target) {
        return "javac " + target + ".java";
    }

    @Override
    String getExecuteCommand(String target) {
        return "java " + target;
    }

}
