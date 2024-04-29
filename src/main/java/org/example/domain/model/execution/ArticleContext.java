package org.example.domain.model.execution;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.document.Document;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ArticleContext implements Context {
  public static final String SOURCE_TAG = "source";
  
  List<Document> documents;

  @Override
  public String summarize() {
    if (isEmpty()) {
      return null;
    }
    return documents.stream()
        .map(doc -> String.format("SOURCE: %s%nARTICLE: %s", doc.getMetadata().get("source"), doc.getContent()))
        .collect(Collectors.joining("\n"));
  }
}
