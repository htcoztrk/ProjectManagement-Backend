package com.sahabt.project.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {

    private String fullname;
    private String mail;
    private String phone;

}
