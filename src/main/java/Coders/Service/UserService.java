/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Coders.Service;

import Coders.Entities.User;
import Coders.Exceptions.UserExistException;
import Coders.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> create(User user) {

        if (userRepository.existByEmail(user.getEmail())) {
            throw new UserExistException("email  alreaddy exist ");

        }
        if (userRepository.existByUserName(user.getUserName())) {
            throw new UserExistException("user Name  alreaddy exist ");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User not Found ");
        }

        return new ResponseEntity<>(userRepository.getOne(id), HttpStatus.OK);
    }

    public ResponseEntity<?> update(User user, Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User not Found ");
        }
        user.setId(id);
        return new ResponseEntity<>(userRepository.getOne(id), HttpStatus.OK);
    }

    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

}
