package fttestlog.controller;

import fttestlog.model.Project;
import fttestlog.model.TestLog;
import fttestlog.repository.TestLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/testLog")
public class TestLogController {

  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private TestLogRepository testLogRepository;

  @GetMapping(path="/list")
  public @ResponseBody
  Iterable<TestLog> listTestLog() {
    logger.info("Request findAll()");
    return testLogRepository.findAll();
  }
}
