package org.example.domain.model.execution;

import java.util.List;

import org.springframework.ai.document.Document;

public interface Context {

  List<Document> getDocuments();

  String summarize();

  default boolean isEmpty() {
    return getDocuments() == null || getDocuments().isEmpty();
  }

}
