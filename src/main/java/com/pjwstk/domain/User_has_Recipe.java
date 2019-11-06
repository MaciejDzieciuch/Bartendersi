package com.pjwstk.domain;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "User_has_Recipe")
public class User_has_Recipe {

  @Column(name = "User_ID")
  private int User_ID;

  @Column(name = "Recipe_ID")
  private BigInteger Recipe_ID;
}
