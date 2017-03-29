package fttestlog;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fttestlog.lib.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

  private final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);

  private Property prop = new Property();
  private final String FT_ADMIN_USERNAME = prop.read("ft_admin_username");
  private final String FT_ADMIN_PASSWORD = prop.read("ft_admin_password");
  private final String FT_USER_USERNAME = prop.read("ft_user_username");
  private final String FT_USER_PASSWORD = prop.read("ft_user_password");
  private final String FT_CUSTOMER_ID = prop.read("ft_customer_id");
  private final String FT_CUSTOMER_NAME = prop.read("ft_customer_name");

  private final String LONG_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sit amet nisi dignissim, vehicula arcu nec, congue odio. 131 char.";
  private final String WRONG_PASSWORD = "toto";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void listCustomerWithAdmin() throws Exception{
    logger.info("List customer with admin user");
    this.mockMvc.perform(get("/customer/list").with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(FT_CUSTOMER_ID)))
        .andExpect(content().string(containsString(FT_CUSTOMER_NAME)));
  }

  @Test
  public void listCustomerWithUser() throws Exception{
    logger.info("List customer with user");
    this.mockMvc.perform(get("/customer/list").with(httpBasic(FT_USER_USERNAME,FT_USER_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(FT_CUSTOMER_ID)));
  }

  @Test
  public void listCustomerWrongPassword() throws Exception{
    logger.info("List customer using user with wrong password");
    this.mockMvc.perform(get("/customer/list").with(httpBasic(FT_ADMIN_USERNAME,WRONG_PASSWORD)))
        .andDo(print()).andExpect(status().isUnauthorized());
  }

  @Test
  public void listCustomerWithoutUser() throws Exception{
    logger.info("List customer without authentication");
    this.mockMvc.perform(get("/customer/list"))
        .andDo(print()).andExpect(status().isUnauthorized());
  }

  @Test
  public void addNewCustomerHappyPath() throws Exception{
    logger.info("Add new customer happy path");
    this.mockMvc.perform(get("/customer/add?customer_id=C1&name=New Customer").with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("New Customer")));
  }

  @Test
  public void addNewCustomerNotAdmin() throws Exception{
    logger.info("Add new customer not admin");
    this.mockMvc.perform(get("/customer/add?customer_id=C1&name=New Customer").with(httpBasic(FT_USER_USERNAME,FT_USER_PASSWORD)))
        .andDo(print()).andExpect(status().isForbidden());
  }

  @Test
  public void addNewCustomerWrongCredential() throws Exception{
    logger.info("Add new customer wrong credential");
    this.mockMvc.perform(get("/customer/add?customer_id=C1&name=New Customer").with(httpBasic(FT_ADMIN_USERNAME,WRONG_PASSWORD)))
        .andDo(print()).andExpect(status().isUnauthorized());
  }

  @Test
  public void addNewCustomerCustomerIdHuge() throws Exception{
    logger.info("Add new customer using more 20 char for customer_id");
    String customerId = LONG_STRING;
    this.mockMvc.perform(get("/customer/add?customer_id="+customerId+"&name=New Customer").with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Server error")));
  }

  @Test
  public void addNewCustomerCustomerNameHuge() throws Exception{
    logger.info("Add new customer using more 255 char for customer name");
    String customerName = LONG_STRING;
    customerName = customerName + customerName + customerName;
    this.mockMvc.perform(get("/customer/add?customer_id=C1&name="+customerName).with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Server error")));
  }
  
}
