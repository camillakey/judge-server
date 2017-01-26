package com.judge_server.core.entity.judge;

import com.judge_server.core.entity.AbstractEntity;
import com.judge_server.core.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "submit")
@Data
@EqualsAndHashCode(callSuper = true)
public class Submit extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem", nullable = false)
    private Problem problem;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "submit_state", nullable = false)
    private SubmitState submitState;

    @Column(name = "source", nullable = false)
    private String source;

}
