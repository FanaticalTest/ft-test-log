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

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void listCustomer() throws Exception{
    logger.info("List customer");
    this.mockMvc.perform(get("/customer/list").with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("F1")));
  }
  
}
