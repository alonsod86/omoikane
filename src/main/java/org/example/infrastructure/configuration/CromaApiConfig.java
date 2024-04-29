package org.example.infrastructure.configuration;

import org.springframework.ai.chroma.ChromaApi;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorsore.ChromaVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "application.vector-store.type", havingValue = "chroma")
public class CromaApiConfig {

	@Bean
	public VectorStore chromaVectorStore(EmbeddingClient embeddingClient, ChromaApi chromaApi) {
		log.info("Using chroma vector store");
		return new ChromaVectorStore(embeddingClient, chromaApi, "TestCollection");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ChromaApi chromaApi(RestTemplate restTemplate) {
		String chromaUrl = "http://localhost:8000";
		return new ChromaApi(chromaUrl, restTemplate);
	}
}