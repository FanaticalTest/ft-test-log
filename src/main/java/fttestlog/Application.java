package fttestlog;

import fttestlog.lib.Property;
import fttestlog.model.Customer;
import fttestlog.model.Project;
import fttestlog.model.TestLog;
import fttestlog.repository.CustomerRepository;
import fttestlog.repository.ProjectRepository;
import fttestlog.repository.TestLogRepository;
import fttestlog.storage.StorageProperties;
import fttestlog.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import java.sql.Timestamp;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application implements CommandLineRunner {

  private Property prop = new Property();
  private final String FT_CUSTOMER_ID = prop.read("ft_customer_id");
  private final String FT_CUSTOMER_NAME = prop.read("ft_customer_name");
  private final int FT_LOAD_TEST_DATA = Integer.parseInt(prop.read("ft_load_test_data"));

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private TestLogRepository testLogRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      if (FT_LOAD_TEST_DATA == 0){
        storageService.deleteAll();
        storageService.init();
      }
    };
  }

  @Override
  public void run(String... args) throws Exception {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    customerRepository.deleteAll();
    projectRepository.deleteAll();
    testLogRepository.deleteAll();

    Customer c = new Customer(FT_CUSTOMER_ID,FT_CUSTOMER_NAME);
    Project p = new Project("FT1","ft-test-log");
    p.setCustomer(c);
    TestLog t = new TestLog();
    t.setFeature("Feature01");
    t.setProject(p);
    t.setScenarioId("ScenarioId01");
    t.setScenarioName("Scenario Name 01");
    t.setScreenshotUrl("url_screenshot.png");
    t.setTags("tag01, tag02");
    t.setTestStartDate(timestamp);
    t.setTestEndDate(timestamp);
    t.setTestTimeout("20s");
    t.setTestWindowsSize("1200 by 800");
    t.setTestSuite("Test Suite name");
    t.setTestStatus("PASSED");

    if (FT_LOAD_TEST_DATA == 1){
      customerRepository.save(c);
      projectRepository.save(p);
      testLogRepository.save(t);
    }

  }
}