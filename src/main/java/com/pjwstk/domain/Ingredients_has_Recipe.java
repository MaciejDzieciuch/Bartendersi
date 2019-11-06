package com.pjwstk.domain;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Ingredients_has_Recipe")
public class Ingredients_has_Recipe {

  @Column(name = "Ingredients_ID")
  private int Ingredients_ID;

  @Column(name = "Recipe_ID")
  private BigInteger Recipe_ID;
}
