package com.pjwstk.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "User.getUsersList",
        query = "SELECT u FROM User u"),
    @NamedQuery(
        name = "User.findUserByEmail",
        query = "SELECT u FROM User u WHERE u.email LIKE :email")
})

@Entity
@Table(name = "User", indexes = {@Index(name = "user_email", columnList = "email")})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "Name")
  @NotNull
  private String name;

  @Column(name = "Email")
  @NotNull
  private String email;

  @Column(name = "User_Type")
  @NotNull
  private String user_type;

  @Column(name = "Password")
  @NotNull
  private String password;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "user_favourite_recipe",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "ID")},
      inverseJoinColumns = {@JoinColumn(name = "recipe_id", referencedColumnName = "ID")})
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUser_type() {
    return user_type;
  }

  public void setUser_type(String user_type) {
    this.user_type = user_type;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }
}
