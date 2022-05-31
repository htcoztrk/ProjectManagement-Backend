package com.sahabt.project.service;

import com.sahabt.project.dto.request.CustomerRequest;
import com.sahabt.project.dto.response.CustomerResponse;
import com.sahabt.project.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {


    CustomerResponse findCustomerById(long id) throws CustomerNotFoundException;

    void addCustomer(CustomerRequest request);

    List<CustomerResponse> getAll();
}
