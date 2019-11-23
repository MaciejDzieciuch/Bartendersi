package com.pjwstk.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Recipe")
@NamedQueries({
    @NamedQuery(
        name = "Recipe.getRecipesList",
        query = "SELECT r FROM Recipe r WHERE r.isApproved = true")
})
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "name", unique = true, length = 100)
  @NotNull
  private String name;

  @Column(name = "ID_Custom")
  @NotNull
  private boolean isCustom;

  @Column(name = "ID_Approved")
  @NotNull
  private boolean isApproved;

  @Column(name = "Instruction", length = 10000)
  @NotNull
  private String instruction;

  @Column(name = "Drink_Type")
  @NotNull
  private String drinkType;

  @Column(name = "Glass_Type")
  @NotNull
  private String glassType;

  @Column(name = "Modification_Date")
  @NotNull
  private String modificationDate;

  @Column(name = "image_url", length = 1000)
  @NotNull
  private String imageUrl;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "recipe_to_ingredient",
      joinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "ingredient_id", referencedColumnName = "ID")})
  private List<Ingredients> ingredients = new ArrayList<>();

  @ManyToMany(mappedBy = "recipes")
  private List<User> users = new ArrayList<>();

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

  public boolean isCustom() {
    return isCustom;
  }

  public void setCustom(boolean custom) {
    isCustom = custom;
  }

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean approved) {
    isApproved = approved;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public String getDrinkType() {
    return drinkType;
  }

  public void setDrinkType(String drinkType) {
    this.drinkType = drinkType;
  }

  public String getGlassType() {
    return glassType;
  }

  public void setGlassType(String glassType) {
    this.glassType = glassType;
  }

  public String getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(String modificationDate) {
    this.modificationDate = modificationDate;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<Ingredients> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Ingredients> ingredients) {
    this.ingredients = ingredients;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}