package com.sahabt.project.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerResponse {

    private String fullname;
    private String mail;
    private String phone;


}
