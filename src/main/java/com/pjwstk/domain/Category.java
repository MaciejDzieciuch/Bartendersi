package com.pjwstk.domain;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private int id;

  @Column(name = "Name")
  private String Name;

  @Column(name = "Recipe_ID")
  private BigInteger Recipe_ID;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public BigInteger getRecipe_ID() {
    return Recipe_ID;
  }

  public void setRecipe_ID(BigInteger recipe_ID) {
    Recipe_ID = recipe_ID;
  }
}
