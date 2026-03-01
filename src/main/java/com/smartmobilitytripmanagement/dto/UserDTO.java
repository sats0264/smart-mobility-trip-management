package com.smartmobilitytripmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String keycloakId;
    private String email;
    private String firstName;
    private String lastName;
}
