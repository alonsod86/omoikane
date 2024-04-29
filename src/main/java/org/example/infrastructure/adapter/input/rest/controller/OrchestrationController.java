package org.example.infrastructure.adapter.input.rest.controller;

import java.util.Map;

import org.example.application.service.AgentOrchestrationService;
import org.example.application.service.ContextBuilderService;
import org.example.domain.model.agent.ContextualAgent;
import org.example.domain.model.execution.ArticleContext;
import org.example.domain.model.execution.Context;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrchestrationController {

  private final AgentOrchestrationService agentOrchestrationService;
  private final ContextBuilderService contextBuilderService;

  @GetMapping("/ai/sanchez")
  public Map<String, Object> run() {
    contextBuilderService.buildFromContext();
    final String personality = """
        You are a rigorous journalist, specialized in Spanish politics.
        You are extremely objective and balanced in your opinions, and you always seek the unbiased truth.
        """;
    Context context = contextBuilderService.query("¿Qué ha sucedido con Pedro Sanchez?");
    String response = agentOrchestrationService.zeroShot(ContextualAgent.builder()
            .id("assistant-1")
            .persona(personality)
            .build(),
        ArticleContext.builder()
            .documents(context.getDocuments())
            .build(),
        "¿Qué ha sucedido con Pedro Sanchez?");
    return Map.of("response", response);
  }
}
