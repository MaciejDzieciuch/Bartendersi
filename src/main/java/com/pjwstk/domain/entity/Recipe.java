package com.pjwstk.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Recipe")
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "ID_Custom")
  private boolean isCustom;

  @Column(name = "ID_Approved")
  private boolean isApproved;

  @Column(name = "Instruction")
  private String instruction;

  @Column(name = "Drink_Type")
  private String drinkType;

  @Column(name = "Glass_Type")
  private String glassType;

  @Column(name = "Modification_Date")
  private String modificationDate;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "Category_ID")
  private int categoryId;

  @Column(name = "Ingredients_ID")
  private int ingredientsId;

  @Column(name = "User_ID")
  private int userId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public int getIngredientsId() {
    return ingredientsId;
  }

  public void setIngredientsId(int ingredientsId) {
    this.ingredientsId = ingredientsId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}