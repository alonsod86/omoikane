package org.example.application.service;

import org.example.application.ports.ModelExecutionPort;
import org.example.domain.model.agent.Agent;
import org.example.domain.model.execution.Context;
import org.example.domain.model.execution.ModelExecutionContext;
import org.example.domain.model.execution.ModelExecutionResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AgentOrchestrationService {

  private final ModelExecutionPort executionPort;

  public String zeroShot(Agent agent, Context context, String query) {
    final ModelExecutionResponse response = executionPort.execute(ModelExecutionContext.builder()
        .agent(agent)
        .context(context)
        .query(query)
        .build());
    return response.getResponse();
  }

}
