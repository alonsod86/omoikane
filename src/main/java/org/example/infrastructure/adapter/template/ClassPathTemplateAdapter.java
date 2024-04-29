package org.example.infrastructure.adapter.template;

import org.example.application.ports.PromptTemplatePort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClassPathTemplateAdapter implements PromptTemplatePort {

  private final ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

  @Override
  public Resource getTemplate(String name) {
    final String basePath = "agents";
    return resolver.getResource(String.format("classpath:/%s/%s", basePath, name));
  }
}
