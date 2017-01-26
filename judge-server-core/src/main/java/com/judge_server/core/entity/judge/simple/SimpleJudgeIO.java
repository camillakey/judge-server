package com.judge_server.core.entity.judge.simple;

import com.judge_server.core.entity.AbstractEntity;
import com.judge_server.core.entity.judge.Problem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "simple_judge_io")
@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleJudgeIO extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "problem", nullable = false)
    private Problem problem;

    @Column(name = "input", nullable = false)
    private String input;

    @Column(name = "output", nullable = false)
    private String output;

}
