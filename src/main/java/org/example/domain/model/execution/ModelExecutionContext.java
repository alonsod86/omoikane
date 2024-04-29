package org.example.domain.model.execution;

import org.example.domain.model.agent.Agent;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ModelExecutionContext {

  Agent agent;
  Context context;
  String query;

}
