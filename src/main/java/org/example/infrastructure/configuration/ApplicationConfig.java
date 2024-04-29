package org.example.infrastructure.configuration;

import org.example.application.ports.ModelExecutionPort;
import org.example.application.ports.PromptTemplatePort;
import org.example.application.service.AgentOrchestrationService;
import org.example.application.service.ContextBuilderService;
import org.example.infrastructure.adapter.execution.ChatBasedModelExecutionAdapter;
import org.example.infrastructure.adapter.template.ClassPathTemplateAdapter;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public AgentOrchestrationService agentOrchestrationService(ModelExecutionPort modelExecutionPort) {
    return new AgentOrchestrationService(modelExecutionPort);
  }

  @Bean
  public ContextBuilderService contextBuilderService(VectorStore vectorStore) {
    return new ContextBuilderService(vectorStore);
  }

  @Bean
  public ModelExecutionPort chatBasedModelExecutionPort(PromptTemplatePort promptTemplatePort, ChatClient chatClient) {
    return new ChatBasedModelExecutionAdapter(promptTemplatePort, chatClient);
  }

  @Bean
  public PromptTemplatePort classPathPromptTemplateAdapter() {
    return new ClassPathTemplateAdapter();
  }
}
