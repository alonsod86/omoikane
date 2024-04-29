package org.example.domain.model.agent;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContextualAgent implements Agent {

  String id;
  String persona;

}
