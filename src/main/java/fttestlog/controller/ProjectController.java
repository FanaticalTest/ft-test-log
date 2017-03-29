package fttestlog.controller;

import fttestlog.model.Customer;
import fttestlog.model.Project;
import fttestlog.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
