package fttestlog.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

  /**
   * Folder location for storing files
   */
  private String location = System.getenv("FTTESTLOG_LOG_FOLDER");

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

}