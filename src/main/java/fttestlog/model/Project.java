package fttestlog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "projects", uniqueConstraints = {@UniqueConstraint(columnNames = {"project_id"})})
public class Project {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Column(name = "project_id", nullable = false, length = 20)
  private String projectId;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Project() {
  }

  public Project(String projectId, String name) {
    super();
    this.name = name;
    this.projectId = projectId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProjectId() {
    return this.projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return getProjectId() + " - "+ getName();
  }
}
