/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Dao;

import Coders.DTO.SignInDTO;
import Coders.DTO.SignUpDTO;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Chahir Chalouati
 */
public interface AuthenticationDAO {

    ResponseEntity<?> signUp(SignUpDTO request);

    ResponseEntity<?> signIn(SignInDTO request);
}
