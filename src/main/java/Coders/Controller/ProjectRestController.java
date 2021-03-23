/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Controller;

import Coders.DTO.ProjectDTO;
import Coders.Entities.Repository;
import Coders.Service.ProjectStorageImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/projects")
@Slf4j
@AllArgsConstructor
public class ProjectRestController {

    private final ProjectStorageImpl projectStorage;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Repository> post(ProjectDTO projectDTO) {
        log.info("REQUEST-PROJECTS-UPLOAD {} ", projectDTO.getFile().getOriginalFilename());
        Repository storeFile = projectStorage.storeFile(projectDTO.getFile());
        return new ResponseEntity<>(storeFile, HttpStatus.OK);
    }
  
   
    
    
    
    
}
