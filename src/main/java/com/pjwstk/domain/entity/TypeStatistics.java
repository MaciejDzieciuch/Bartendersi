package com.pjwstk.domain.entity;

public enum TypeStatistics {
  SINGLE_VIEW (1),
  CHECKED_CATEGORY (2);

  private final int type;

  TypeStatistics(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }
}
