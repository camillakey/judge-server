package com.judge_server.core.entity.judge;

import com.judge_server.core.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "problem")
@Data
@EqualsAndHashCode(callSuper = true)
public class Problem extends AbstractEntity {

    @Column(name = "problem_id", unique = true, nullable = false)
    private String problemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "judge_system", nullable = false)
    private JudgeSystem judgeSystem;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "time_limit", nullable = false)
    private long timeLimit;

    @Column(name = "memory_limit", nullable = false)
    private long memoryLimit;

}
