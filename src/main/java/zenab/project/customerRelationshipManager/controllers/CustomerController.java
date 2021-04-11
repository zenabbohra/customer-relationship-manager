package zenab.project.customerRelationshipManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import zenab.project.customerRelationshipManager.entities.Customer;
import zenab.project.customerRelationshipManager.service.CustomerAuthenticationService;
import zenab.project.customerRelationshipManager.service.CustomerService;

import java.util.UUID;

@Controller
public class CustomerController {
    @GetMapping("/addCustomer.html")
    public String firstPage() {
        return "addCustomer";
    }

    @Autowired
    private CustomerAuthenticationService customerAuthenticationService;
    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewCustomer(@RequestBody MultiValueMap<String, String> formData) {

        var firstName = formData.getFirst("firstName");
        var lastname = formData.getFirst("lastName");
        var email = formData.getFirst("email");

        // TODO - addCustomer shouldn't have to return Boolean
        if (customerService.addCustomer(firstName, lastname, email)) {
            return "redirect:/home.html";
        } else {
            // TODO - redirect it to same page with an error message on top
            return "The customer email is not valid";
        }

    }

    @GetMapping(path = "/update/{customerId}")
    public String updateCustomer(@PathVariable UUID customerId, Model model) {

        Customer customer = customerService.getCustomer(customerId);
        model.addAttribute("customer", customer);

        return "update"; // this is referring to update.html page under resources/templates
    }

    @PostMapping(path = "/update/{customerId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateCustomer(@RequestBody MultiValueMap<String, String> formData,
                          @PathVariable UUID customerId) {

        var firstName = formData.getFirst("firstName");
        var lastname = formData.getFirst("lastName");
        var email = formData.getFirst("email");

        // TODO - updateCustomer shouldn't have to return Boolean
        if (customerService.updateCustomer(customerId, firstName, lastname, email)) {
            return "redirect:/home.html";
        } else {
            // TODO - redirect it to same page with an error message on top
            return "Customer entity  not found";
        }
    }

    @GetMapping(path="/delete/{customerId}")
    public String deleteCustomer(@PathVariable UUID customerId) {

        customerService.deleteCustomer(customerId);
        return "redirect:/home.html";
    }

    // TODO : use jackson library to render json object here
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Customer> getAllCustomers() {
        // This returns a JSON or XML with the users
        return customerService.getAllCustomers();
    }
}
