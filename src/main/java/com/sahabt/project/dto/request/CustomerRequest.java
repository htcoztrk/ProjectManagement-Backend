package com.sahabt.project.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequest {

    private String fullname;
    private String mail;
    private String phone;


}
