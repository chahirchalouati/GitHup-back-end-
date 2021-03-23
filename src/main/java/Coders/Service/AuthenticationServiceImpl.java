/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Service;

import Coders.DTO.JWT_Response;
import Coders.DTO.SignInDTO;
import Coders.DTO.SignUpDTO;
import Coders.Entities.User;
import Coders.Exceptions.UserExistException;
import Coders.Repository.AuthoritieRepository;
import Coders.Repository.UserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
public class AuthenticationServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthoritieRepository authoritieRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> signUp(SignUpDTO request) {
        if (userRepository.existByUserName(request.getUsername())) {
            throw new UserExistException("User name already exist , choose another one");
        }
        if (userRepository.existByEmail(request.getEmail())) {
            throw new UserExistException("email already exist , choose another one");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUserName(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setAuthoritie(authoritieRepository.findByAuthoritie("USER"));

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<?> signIn(SignInDTO request) {

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(request.getParam(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = userRepository.findByUserNameOrEmail(request.getParam(), request.getParam());

        return ResponseEntity
                .ok(new JWT_Response(user, jwt, new Date()));
    }

}
