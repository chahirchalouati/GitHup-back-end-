/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Service;

import Coders.Entities.User;
import Coders.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Chahir Chalouati
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        User u = UserRepository.findByUserNameOrEmail(param, param);
        if (u == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new MyUserDetailsImpl(u);
    }

}
