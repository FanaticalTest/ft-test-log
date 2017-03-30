package fttestlog.controller;

import fttestlog.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import fttestlog.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/customer")
public class CustomerController {

  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private CustomerRepository customerRepository;

  @GetMapping(path="/list")
  public @ResponseBody Iterable<Customer> listCustomer() {
    logger.info("Request findAll()");
    return customerRepository.findAll();
  }

  @GetMapping(path="/add")
  public @ResponseBody String addNewCustomer (@RequestParam String id_name, @RequestParam String name){
    try{
      Customer c = new Customer(id_name, name);
      customerRepository.save(c);
      logger.info("New customer added : {} - {} ", id_name, name);
      return "New customer added : " + name;
    }
    catch (Exception e)
    {
      logger.debug(e.toString());
      return "Server error";
    }
  }
}
