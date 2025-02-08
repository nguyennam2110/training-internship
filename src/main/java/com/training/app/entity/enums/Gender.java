package com.training.app.entity.enums;

public enum Gender {
  MALE(1),
  FEMALE(0);

  private final Integer code;

  Gender(Integer code) {
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }

  public static Gender convertToValue(Integer code) {
    for (Gender value : Gender.values()) {
      if (value.getCode().equals(code)) {
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid code");
  }
}
