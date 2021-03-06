package fttestlog;

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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {

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
  public void listProjectUsingAdmin() throws Exception{
    logger.info("List project with admin user");
    this.mockMvc.perform(get("/project/list").with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("FT1")))
        .andExpect(content().string(containsString("ft-test-log")))
        .andExpect(content().string(containsString(FT_CUSTOMER_ID)))
        .andExpect(content().string(containsString(FT_CUSTOMER_NAME)));
  }

  @Test
  public void listProjectUsingUser() throws Exception{
    logger.info("List project with user");
    this.mockMvc.perform(get("/project/list").with(httpBasic(FT_USER_USERNAME,FT_USER_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("FT1")))
        .andExpect(content().string(containsString("ft-test-log")))
        .andExpect(content().string(containsString(FT_CUSTOMER_ID)))
        .andExpect(content().string(containsString(FT_CUSTOMER_NAME)));
  }

  @Test
  public void listProjectUsingWrongCredential() throws Exception{
    logger.info("List project with wrong credential");
    this.mockMvc.perform(get("/project/list").with(httpBasic(FT_USER_USERNAME,WRONG_PASSWORD)))
        .andDo(print()).andExpect(status().isUnauthorized());
  }

  @Test
  public void addProjectUsingAdmin()throws Exception{
    logger.info("Add project with admin user");
    this.mockMvc.perform(get("/project/add?customer_id=1&project_name=Toto Project&project_id_name=T1").with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Toto Project")));
  }

  @Test
  public void addProjectUsingUser()throws Exception{
    logger.info("Add project with user");
    this.mockMvc.perform(get("/project/add?customer_id=1&project_name=Toto Project&project_id_name=T1").with(httpBasic(FT_USER_USERNAME,FT_USER_PASSWORD)))
        .andDo(print()).andExpect(status().isForbidden());
  }

  @Test
  public void addProjectWithHugeProjectId()throws Exception{
    logger.info("Add project with huge project_id");
    String projectId = LONG_STRING;
    this.mockMvc.perform(get("/project/add?customer_id=1&project_name=Toto Project&project_id_name="+projectId).with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Server error")));
  }

  @Test
  public void addProjectWithHugeProjectName()throws Exception{
    logger.info("Add project with huge project_name");
    String projectName = LONG_STRING;
    projectName = projectName + projectName + projectName;
    this.mockMvc.perform(get("/project/add?customer_id=1&project_name="+projectName+"&project_id_name=T2").with(httpBasic(FT_ADMIN_USERNAME,FT_ADMIN_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Server error")));
  }

  @Test
  public void listTestLogByProjectIdName()throws Exception {
    logger.info("List project by project id name");
    this.mockMvc.perform(get("/project/findByProjectIdName?project_id_name=FT1").with(httpBasic(FT_USER_USERNAME,FT_USER_PASSWORD)))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("FT1")))
        .andExpect(content().string(containsString("ft-test-log")))
        .andExpect(content().string(containsString(FT_CUSTOMER_ID)))
        .andExpect(content().string(containsString(FT_CUSTOMER_NAME)));
  }
}
