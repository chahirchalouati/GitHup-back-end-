package Coders.Service;

import Coders.DTO.ProjectDTO;
import Coders.Dao.ProjectDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectDAO {

    @Override
    public ResponseEntity<?> createProject(ProjectDTO projectDTO) {
        // got to file Zipper

        return null;
    }
}
