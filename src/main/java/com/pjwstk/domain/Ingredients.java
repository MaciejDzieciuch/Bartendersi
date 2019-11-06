package com.pjwstk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ingredients")
public class Ingredients {
  @Id
  @Column(name = "ID")
  private int id;

  @Column(name = "Name")
  private String Name;

  @Column(name = "Measure")
  private String Measure;

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

  public String getMeasure() {
    return Measure;
  }

  public void setMeasure(String measure) {
    Measure = measure;
  }
}
