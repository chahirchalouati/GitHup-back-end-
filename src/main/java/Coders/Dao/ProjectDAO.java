package Coders.Dao;

import Coders.DTO.ProjectDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectDAO  {
   ResponseEntity<?> createProject (ProjectDTO projectDTO);

}
