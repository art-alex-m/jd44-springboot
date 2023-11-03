package ru.netology.l20authservice;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @NotBlank
    private String user;

    @NotBlank
    private String password;
}
