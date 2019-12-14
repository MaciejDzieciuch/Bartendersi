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


@NamedQueries({
    @NamedQuery(
        name = "Recipe.getRecipesList",
        query = "SELECT r FROM Recipe r"),
    @NamedQuery(
        name = "Type.findTypeByName",
        query = "SELECT DISTINCT r.drinkType FROM Recipe r WHERE r.drinkType IN :types"),
    @NamedQuery(
        name = "Recipe.findRecipeByCategoryIdAndIngredientNameAndType",
        query = "SELECT r FROM Recipe r JOIN r.ingredients i WHERE r.category IN :categories AND r.drinkType IN :drinkTypes AND (i.name IN :ingredients) GROUP BY r HAVING COUNT (DISTINCT i.name) =: namesLength ORDER BY r.name ASC"),
    @NamedQuery(
        name = "Recipe.findRecipeByCategoryIdAndType",
        query = "SELECT r FROM Recipe r  WHERE r.category IN :categories AND r.drinkType IN :drinkTypes ORDER BY r.name ASC"),
    @NamedQuery(
        name = "Recipe.findRecipeByCategoryIdAndTypeAndFavourites",
        query = "SELECT r FROM Recipe r JOIN r.users u WHERE u.id=:id AND r.category IN :categories AND r.drinkType IN :drinkTypes ORDER BY r.name ASC"),
    @NamedQuery(
        name = "Recipe.findRecipeByCategoryAndIngredientAndTypeAndFavourites",
        query = "SELECT r FROM Recipe r  JOIN r.ingredients i JOIN r.users u WHERE  u.id=:id AND r.category IN :categories AND r.drinkType IN :drinkTypes AND (i.name IN :ingredients) GROUP BY r HAVING COUNT(DISTINCT i.name)=:namesLength ORDER BY r.name ASC"),
    @NamedQuery(
        name = "Recipe.getRecipeTypes",
        query = "SELECT DISTINCT r.drinkType FROM Recipe r"),
    @NamedQuery(
        name = "Recipe.getFavouritesListIdsForUser",
        query = "SELECT r.id FROM Recipe r JOIN r.users u WHERE u.id=:id"),
    @NamedQuery(
        name = "Recipe.findRecipeByLiveSearch",
        query = "SELECT r FROM Recipe r WHERE r.name LIKE :nameChars"),
    @NamedQuery(
        name = "Recipe.getUnauthorizedRecipes",
        query = "SELECT r FROM Recipe r"),
    @NamedQuery(
        name = "Recipe.getTheBiggestId",
        query = "SELECT MAX(r.id) FROM Recipe r")
})

@Entity
@Table(name = "Recipe")
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