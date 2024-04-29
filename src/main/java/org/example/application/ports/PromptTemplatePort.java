package org.example.application.ports;

import org.springframework.core.io.Resource;

public interface PromptTemplatePort {

  Resource getTemplate(String name);

}
