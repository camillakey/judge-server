package com.judge_server.runner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Runner request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunnerRequest {

    /**
     * Source code language.
     */
    private Language language;

    /**
     * Source code.
     */
    private String source;

    /**
     * Standard input.
     */
    private String stdin;

    /**
     * Time limit in milliseconds.
     */
    private Long timeLimit;

    /**
     * Memory limit in kilobytes.
     */
    private Long memoryLimit;


    /**
     * Language.
     */
    public enum Language {
        C,
        Cpp,
        Java,
    }
}
