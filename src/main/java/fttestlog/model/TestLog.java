package fttestlog.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "test_logs")
public class TestLog {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  @Column(name = "feature", nullable = true, length = 1024)
  private String feature;

  @Column(name = "test_suite", nullable = true, length = 1024)
  private String testSuite;

  @Column(name = "scenario_id", nullable = true, length = 255)
  private String scenarioId;

  @Column(name = "scenario_name", nullable = true, length = 1024)
  private String scenarioName;

  @Column(name = "screenshot_url", nullable = true, length = 1024)
  private String screenshotUrl;

  @Column(name = "tags", nullable = true, length = 1024)
  private String tags;

  @Column(name = "test_start_date", nullable = true)
  private java.sql.Timestamp testStartDate;

  @Column(name = "test_end_date", nullable = true)
  private java.sql.Timestamp testEndDate;

  @Column(name = "test_status", nullable = true, length = 20)
  private String testStatus;

  @Column(name = "test_timeout", nullable = true, length = 255)
  private String testTimeout;

  @Column(name = "test_windows_size", nullable = true, length = 255)
  private String testWindowsSize;

  public TestLog() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public String getFeature() {
    return feature;
  }

  public void setFeature(String feature) {
    this.feature = feature;
  }

  public String getTestSuite() {
    return testSuite;
  }

  public void setTestSuite(String testSuite) {
    this.testSuite = testSuite;
  }

  public String getScenarioId() {
    return scenarioId;
  }

  public void setScenarioId(String scenarioId) {
    this.scenarioId = scenarioId;
  }

  public String getScenarioName() {
    return scenarioName;
  }

  public void setScenarioName(String scenarioName) {
    this.scenarioName = scenarioName;
  }

  public String getScreenshotUrl() {
    return screenshotUrl;
  }

  public void setScreenshotUrl(String screenshotUrl) {
    this.screenshotUrl = screenshotUrl;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public Timestamp getTestStartDate() {
    return testStartDate;
  }

  public void setTestStartDate(Timestamp testStartDate) {
    this.testStartDate = testStartDate;
  }

  public Timestamp getTestEndDate() {
    return testEndDate;
  }

  public void setTestEndDate(Timestamp testEndDate) {
    this.testEndDate = testEndDate;
  }

  public String getTestStatus() {
    return testStatus;
  }

  public void setTestStatus(String testStatus) {
    this.testStatus = testStatus;
  }

  public String getTestTimeout() {
    return testTimeout;
  }

  public void setTestTimeout(String testTimeout) {
    this.testTimeout = testTimeout;
  }

  public String getTestWindowsSize() {
    return testWindowsSize;
  }

  public void setTestWindowsSize(String testWindowsSize) {
    this.testWindowsSize = testWindowsSize;
  }
}
