package fttestlog.controller;

import fttestlog.model.Customer;
import fttestlog.model.Project;
import fttestlog.model.TestLog;
import fttestlog.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/project")
public class ProjectController {

  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private ProjectRepository projectRepository;

  @GetMapping(path="/list")
  public @ResponseBody
  Iterable<Project> listProject() {
    logger.info("Request findAll()");
    return projectRepository.findAll();
  }

  @GetMapping(path="/findByProjectIdName")
  public @ResponseBody
  Iterable<Project> findByProjectIdName(@RequestParam String project_id_name) {
    logger.info("Request findByProjectIdName()");
    return projectRepository.findByProjectIdName(project_id_name);
  }

  @GetMapping(path="/add")
  public @ResponseBody String addNewProject (@RequestParam Long customer_id, @RequestParam String project_name, @RequestParam String project_id_name){
    try{
      Customer c = new Customer();
      c.setId(customer_id);

      Project p = new Project();
      p.setName(project_name);
      p.setIdName(project_id_name);
      p.setCustomer(c);
      projectRepository.save(p);

      logger.info("New project added : {} - {} ", project_name, project_id_name);
      return "New project added : " + project_name;
    }
    catch (Exception e)
    {
      logger.debug(e.toString());
      return "Server error";
    }
  }

}
