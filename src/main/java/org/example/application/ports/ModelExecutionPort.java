package org.example.application.ports;

import org.example.domain.model.execution.ModelExecutionContext;
import org.example.domain.model.execution.ModelExecutionResponse;

public interface ModelExecutionPort {

  ModelExecutionResponse execute(ModelExecutionContext request);

}
