package com.sahabt.project.service.business;

import com.sahabt.project.dto.request.CustomerRequest;
import com.sahabt.project.dto.response.CustomerResponse;
import com.sahabt.project.dto.response.ProjectResponse;
import com.sahabt.project.entity.Customer;
import com.sahabt.project.exception.CustomerNotFoundException;
import com.sahabt.project.repository.CustomerRepository;
import com.sahabt.project.service.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    public void addCustomer(CustomerRequest request) {
        var customerEntity = modelMapper.map(request, Customer.class);
        customerRepository.save(customerEntity);
    }

    @Override
    public List<CustomerResponse> getAll() {
            return customerRepository.findAll().stream()
                    .map(cus->modelMapper.map(cus, CustomerResponse.class))
                    .toList();

    }

    public CustomerResponse findCustomerById(long id) throws CustomerNotFoundException {
        var customer = customerRepository.findById(id);
        if(!customer.isPresent())
            throw new CustomerNotFoundException();
        return modelMapper.map(customer.get(), CustomerResponse.class);
    }

    public Customer isExist(long id) throws CustomerNotFoundException {
        var customer = customerRepository.findById(id);
        if(!customer.isPresent())
            throw new CustomerNotFoundException();
        return customer.get();
    }
}
