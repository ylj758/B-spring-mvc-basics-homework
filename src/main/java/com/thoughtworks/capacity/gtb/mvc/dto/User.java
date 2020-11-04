package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    @NotEmpty
    @Size(max=10,min=3)
    @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$")
    private String username;
    @NotEmpty
    @Size(max=12,min=5)
    private String password;
    @Email
    private String email;
}
