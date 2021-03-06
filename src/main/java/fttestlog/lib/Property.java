package fttestlog.lib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

  public String read(String value) {
    Properties prop = new Properties();
    InputStream input = null;

    String propertyValue = "";

    try {

      input = new FileInputStream(System.getenv("FTTESTLOG_APP_PROPERTIES"));

      // load a properties file
      prop.load(input);

      propertyValue = prop.getProperty(value);

    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return propertyValue;
  }

}