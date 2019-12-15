package com.pjwstk.domain.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(
        name = "Recipe.findTop10Recipes",
        query = "SELECT rs.recipeName, COUNT (rs.recipeName) AS QUANTITY FROM RecipeStatistics rs WHERE rs.type = 1 GROUP BY rs.recipeName ORDER BY quantity DESC")
})

@Entity
@Table(name = "statistics")
public class RecipeStatistics {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type")
  private int type;

  @Column(name = "recipe_id")
  private Long recipeId;

  @Column(name = "recipe_name")
  private String recipeName;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  private Category category;

  @ElementCollection
  @Column(name = "categories")
  private Set<Long> categories;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Long getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(Long recipeId) {
    this.recipeId = recipeId;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Set<Long> getCategories() {
    return categories;
  }

  public void setCategories(Set<Long> categories) {
    this.categories = categories;
  }
}
