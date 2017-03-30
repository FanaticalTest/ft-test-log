package fttestlog.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_name"})})
public class Customer implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Column(name = "id_name", nullable = false, length = 20)
  private String idName;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  public Customer() {
  }

  public Customer(String idName, String name) {
    super();
    this.idName = idName;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getIdName() {
    return this.idName;
  }

  public void setIdName(String idName) {
    this.idName = idName;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getIdName() + " - " + getName();
  }

}
