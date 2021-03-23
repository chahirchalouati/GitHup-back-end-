/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Service;

import Coders.Repository.RepositoryOfRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chahir Chalouati
 */
@Service
@AllArgsConstructor
public class RepositoryService {

    private final RepositoryOfRepository repository;
    
    public ResponseEntity<?> getAll(){ 
    return new ResponseEntity<>(repository.findAll() , HttpStatus.OK); 
    }
}
