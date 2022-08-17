package com.example.fcmbassessment.request;

import com.example.fcmbassessment.enums.CustomerType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    private String email;

    @NotNull
    private Integer age;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private CustomerType customerType;
}
