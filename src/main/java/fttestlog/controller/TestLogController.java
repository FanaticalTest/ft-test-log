package fttestlog.controller;

import fttestlog.model.Customer;
import fttestlog.model.Project;
import fttestlog.model.TestLog;
import fttestlog.repository.TestLogRepository;
import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping(path="/findByProjectId")
  public @ResponseBody
  Iterable<TestLog> findByProjectId(@RequestParam long project_id) {
    logger.info("Request findByProjectId()");
    return testLogRepository.findByProjectId(project_id);
  }

  @GetMapping(path="/findByProjectIdName")
  public @ResponseBody
  Iterable<TestLog> findByProjectIdName(@RequestParam String project_id_name) {
    logger.info("Request findByProjectIdName()");
    return testLogRepository.findByProjectIdName(project_id_name);
  }

  @GetMapping(path="/log")
  public @ResponseBody String addNewTestLog (@RequestParam long project_id, @RequestParam String feature,
                                             @RequestParam String test_suite, @RequestParam String scenario_id,
                                             @RequestParam String scenario_name, @RequestParam String screenshot_url,
                                             @RequestParam String tags, @RequestParam String test_status,
                                             @RequestParam String test_timeout, @RequestParam String test_windows_size,
                                             @RequestParam Timestamp test_start_date, @RequestParam Timestamp test_end_date)
  {

    try{
      Project p = new Project();
      p.setId(project_id);

      TestLog t = new TestLog();
      t.setProject(p);
      t.setFeature(feature);
      t.setTestSuite(test_suite);
      t.setScenarioId(scenario_id);
      t.setScenarioName(scenario_name);
      t.setScreenshotUrl(screenshot_url);
      t.setTags(tags);
      t.setTestStatus(test_status);
      t.setTestTimeout(test_timeout);
      t.setTestWindowsSize(test_windows_size);
      t.setTestStartDate(test_start_date);
      t.setTestEndDate(test_end_date);

      testLogRepository.save(t);

      logger.info("New test log added : {} - {} - {} ", test_start_date, project_id, feature);
      return "New test log added : " + test_start_date;
    }
    catch (Exception e)
    {
      logger.debug(e.toString());
      return "Server error";
    }
  }
}
