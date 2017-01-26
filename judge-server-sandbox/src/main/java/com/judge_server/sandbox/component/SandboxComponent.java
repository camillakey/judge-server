package com.judge_server.sandbox.component;

import com.judge_server.sandbox.core.Sandbox;
import com.judge_server.sandbox.dto.SandboxEnvironment;

/**
 * Create, destroy sandbox.
 */
public interface SandboxComponent {

    /**
     * Creates sandbox.
     * @param sandboxEnvironment sandbox environment.
     * @return  created sandbox.
     */
    Sandbox create(SandboxEnvironment sandboxEnvironment);

}
