package org.example.infrastructure.configuration;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmbeddingsConfig {

//  @Bean
//  @ConditionalOnProperty(name = "application.embeddings.type", havingValue = "ollama")
//  public EmbeddingClient ollamaEmbeddingClient(OllamaApi api) {
//    log.info("Using ollama embeddings client");
//    return new OllamaEmbeddingClient(api);
//  }

//  @Bean
//  @ConditionalOnProperty(name = "application.embeddings.type", havingValue = "openai")
//  public EmbeddingClient openaiEembeddingClient(OpenAiApi api) {
//    log.info("Using openai embeddings client");
//    return new OpenAiEmbeddingClient(api);
//  }

}
