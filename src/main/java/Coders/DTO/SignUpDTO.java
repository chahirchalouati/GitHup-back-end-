/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @NotBlank(message = "username can't be blank")
    private String username;
    @Email(message = "invalid email address")
    private String email;
    @NotBlank(message = "password can't be blank")
    private String password;
}
