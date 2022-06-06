package com.sahabt.project.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahabt.project.ProjectManagementApplication;
import com.sahabt.project.service.CustomerService;
import com.sahabt.project.dto.response.CustomerResponse;
import com.sahabt.project.dto.request.CustomerRequest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(
        classes = ProjectManagementApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerService customerService;

    @ParameterizedTest
    @CsvFileSource(resources = "/customer.csv")
    void addCustomer_ShoudlReturnOk(
            String fullname,
            String mail,
            String phone) throws Throwable {
        // 1. Test Setup
        var request = new CustomerRequest();
        request.setFullname(fullname);
        request.setMail(mail);
        request.setPhone(phone);
        var response = modelMapper.map(request,
                CustomerResponse.class);
        Mockito.when(customerService.addCustomer(request))
                .thenReturn(response);
        // 2. Call exercise method
        mockMvc.perform(
                        post("/customer")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                // 3. Verification
                .andExpect(status().isOk());
        // 4. Tear-down
    }
}