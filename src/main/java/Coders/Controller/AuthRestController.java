/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Coders.Controller;

import Coders.DTO.SignInDTO;
import Coders.DTO.SignUpDTO;
import Coders.Service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
public class AuthRestController {

    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDTO request) {
        return authenticationServiceImpl.signIn(request);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO request) {
        return authenticationServiceImpl.signUp(request);
    }
}
