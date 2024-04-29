package org.example.application.service;

import java.util.Arrays;

import org.example.domain.model.execution.ArticleContext;
import org.example.domain.model.execution.Context;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import io.vavr.Tuple2;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContextBuilderService {
  private final VectorStore vectorStore;

  public void buildFromContext() {
    try {
      TokenTextSplitter textSplitter = new TokenTextSplitter();
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      Resource[] resources = resolver.getResources("classpath:/feeds/sanchez/*.txt");
      Arrays.stream(resources)
          .map(resource -> new Tuple2<>(resource, new TextReader(resource)))
          .map(this::updateSource)
          .forEach(reader -> vectorStore.accept(textSplitter.apply(reader._2().get())));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Context query(String query) {
    return ArticleContext.builder()
        .documents(vectorStore.similaritySearch(query))
        .build();
  }

  private Tuple2<Resource, TextReader> updateSource(Tuple2<Resource, TextReader> tuple) {
    tuple._2().getCustomMetadata().put(ArticleContext.SOURCE_TAG, tuple._1().getFilename());
    return tuple;
  }

}
