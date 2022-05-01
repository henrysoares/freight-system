package com.hencarvalho.freightsystem.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

/** Enum que representa os possiveis status de score (pontuação). */
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum UserScore {
  NONE,
  SLIGHTLY_GOOD,
  GOOD,
  SLIGHTLY_BAD,
  BAD,
  SUSPECT,
  BLOCKED
}
