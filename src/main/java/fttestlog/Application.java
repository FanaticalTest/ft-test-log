package fttestlog;

import fttestlog.lib.Property;
import fttestlog.model.Customer;
import fttestlog.repository.CustomerRepository;
import fttestlog.storage.StorageProperties;
import fttestlog.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application implements CommandLineRunner {

  private Property prop = new Property();
  private final String FT_CUSTOMER_ID = prop.read("ft_customer_id");
  private final String FT_CUSTOMER_NAME = prop.read("ft_customer_name");

  @Autowired
  private CustomerRepository customerRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      //storageService.deleteAll();
      //storageService.init();
    };
  }

  @Override
  public void run(String... args) throws Exception {

    customerRepository.deleteAll();

    customerRepository.save(new Customer(FT_CUSTOMER_ID,FT_CUSTOMER_NAME));

  }
}