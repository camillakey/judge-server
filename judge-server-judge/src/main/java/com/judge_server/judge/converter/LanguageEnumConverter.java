package com.judge_server.judge.converter;

import com.judge_server.core.entity.judge.Language;
import com.judge_server.runner.dto.RunnerRequest;

/**
 * Converts {@link Language Language} to {@link RunnerRequest.Language RunnerResult.Language}.
 */
public class LanguageEnumConverter {

    public static RunnerRequest.Language convert(Language language) {
        switch (language) {
            case C:
                return RunnerRequest.Language.C;
            case Cpp:
                return RunnerRequest.Language.Cpp;
            case Java:
                return RunnerRequest.Language.Java;
            default:
                return null;
        }
    }

}
