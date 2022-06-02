package com.sahabt.project.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerRequest {

    private String fullname;
    private String mail;
    private String phone;

}
