package com.sahabt.project.controller;

import com.sahabt.project.dto.request.CustomerRequest;
import com.sahabt.project.dto.response.CustomerResponse;
import com.sahabt.project.exception.CustomerNotFoundException;
import com.sahabt.project.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@CrossOrigin
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    void addCustomer(@RequestBody CustomerRequest request){
        customerService.addCustomer(request);
    }

    @GetMapping(value="/getAll")
    public List<CustomerResponse> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    CustomerResponse findCustomerById(@RequestParam long id) throws CustomerNotFoundException {
        return customerService.findCustomerById(id);
    }
}
