package com.judge_server.judge.component;

import com.judge_server.core.entity.judge.Submit;

/**
 * Judge component.
 */
public interface JudgeComponent {

    /**
     * Judges submit.
     * @param submit submit.
     */
    void judge(Submit submit);

}
