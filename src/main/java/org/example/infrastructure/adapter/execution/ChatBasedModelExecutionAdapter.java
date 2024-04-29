package org.example.infrastructure.adapter.execution;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.application.ports.ModelExecutionPort;
import org.example.application.ports.PromptTemplatePort;
import org.example.domain.model.agent.ContextualAgent;
import org.example.domain.model.execution.ModelExecutionContext;
import org.example.domain.model.execution.ModelExecutionResponse;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatBasedModelExecutionAdapter implements ModelExecutionPort {

  private final PromptTemplatePort promptTemplatePort;
  private final ChatClient chatClient;

  @Override
  public ModelExecutionResponse execute(ModelExecutionContext context) {
    final Message userMessage = new UserMessage(context.getQuery());
    final Message systemMessage = buildMessageFromContext(context);
    final ChatResponse response = chatClient.call(new Prompt(List.of(userMessage, systemMessage)));
    return ModelExecutionResponse.builder()
        // TODO: improve result management
        .response(response.getResult().toString())
        .build();
  }

  private Message buildMessageFromContext(ModelExecutionContext context) {
    Message systemMessage;
    SystemPromptTemplate systemPrompt;

    if (context.getAgent() instanceof ContextualAgent contextual) {
      systemPrompt = new SystemPromptTemplate(promptTemplatePort.getTemplate("embeddings-assistant.st"));
      systemMessage = systemPrompt.createMessage(Map.of(
          "personality", contextual.getPersona(),
          "information", context.getContext().summarize()));
    } else {
      systemPrompt = new SystemPromptTemplate(promptTemplatePort.getTemplate("standard-assistant.st"));
      systemMessage = systemPrompt.createMessage();
    }

    return systemMessage;
  }
}
