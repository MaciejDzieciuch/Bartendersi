package com.pjwstk.domain;

import java.math.BigInteger;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Recipe")
public class Recipe {

  @Id
  @Column(name = "ID")
  private BigInteger id;

  @Column(name = "ID_Custom")
  private boolean is_custom;

  @Column(name = "ID_Approved")
  private boolean is_approved;

  @Column(name = "Instruction")
  private String instruction;

  @Column(name = "Drink_Type")
  private String drink_type;

  @Column(name = "Glass_Type")
  private String glass_type;

  @Column(name = "Modification_Date")
  private Date mmodification_date;

  @Column(name = "Image")
  private Blob image;

  @Column(name = "Category_ID")
  private int category_id;

  @Column(name = "Ingredients_ID")
  private int ingredients_id;

  @Column(name = "User_ID")
  private int user_id;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public boolean isIs_custom() {
    return is_custom;
  }

  public void setIs_custom(boolean is_custom) {
    this.is_custom = is_custom;
  }

  public boolean isIs_approved() {
    return is_approved;
  }

  public void setIs_approved(boolean is_approved) {
    this.is_approved = is_approved;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public String getDrink_type() {
    return drink_type;
  }

  public void setDrink_type(String drink_type) {
    this.drink_type = drink_type;
  }

  public String getGlass_type() {
    return glass_type;
  }

  public void setGlass_type(String glass_type) {
    this.glass_type = glass_type;
  }

  public Date getMmodification_date() {
    return mmodification_date;
  }

  public void setMmodification_date(Date mmodification_date) {
    this.mmodification_date = mmodification_date;
  }

  public Blob getImage() {
    return image;
  }

  public void setImage(Blob image) {
    this.image = image;
  }

  public int getCategory_id() {
    return category_id;
  }

  public void setCategory_id(int category_id) {
    this.category_id = category_id;
  }

  public int getIngredients_id() {
    return ingredients_id;
  }

  public void setIngredients_id(int ingredients_id) {
    this.ingredients_id = ingredients_id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
}
