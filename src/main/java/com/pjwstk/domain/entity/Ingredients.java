package com.pjwstk.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "Ingredients.findIngredientByName",
        query = "SELECT DISTINCT i.name FROM Ingredients i WHERE i.name IN :names"),
    @NamedQuery(
        name = "Ingredient.getIngredientList",
        query = "SELECT DISTINCT i.name FROM Ingredients i"),
    @NamedQuery(
        name = "Ingredient.findIngredientByLiveSearch",
        query = "SELECT DISTINCT i.name FROM Ingredients i WHERE i.name LIKE :nameChars")
})

@Entity
@Table(name = "Ingredients", indexes = {@Index(name = "idx_name", columnList = "name")})
public class Ingredients {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "Name", length = 100)
  @NotNull
  private String name;

  @Column(name = "Measure")
  @NotNull
  private String measure;

  @ManyToMany(mappedBy = "ingredients")
  private List<Recipe> recipes = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMeasure() {
    return measure;
  }

  public void setMeasure(String measure) {
    this.measure = measure;
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }
}
