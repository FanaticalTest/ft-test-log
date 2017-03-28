package fttestlog.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", uniqueConstraints={@UniqueConstraint(columnNames={"customer_id"})})
public class Customer implements Serializable{

  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Column(name = "customer_id", nullable = false, length = 20)
  private String customerId;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  public Customer(){}

  public Customer(String customerId, String name)
  {
    super();
    this.customerId = customerId;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(String customerId){
    this.customerId = customerId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getCustomerId() + " - " + getName();
  }

}
