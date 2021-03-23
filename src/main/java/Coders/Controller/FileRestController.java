/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Controller;

import Coders.DTO.ResponseCodeDTO;
import Coders.Entities.File;
import Coders.Repository.FileRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chahir Chalouati
 */
@RestController
@RequestMapping("/files")
public class FileRestController {

    FileRepository fileRepository;

    public FileRestController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/{name}")
    public ResponseEntity<ResponseCodeDTO> get(@PathVariable String name) throws IOException {

        File findByURL = fileRepository.findByURL("/files/" + name);
        Path path = Paths.get(findByURL.getPath()).normalize().toAbsolutePath();

        ResponseCodeDTO codeDTO = ResponseCodeDTO
                .builder()
                .content(Files.readString(path))
                .fileName(path.getFileName().toString())
                .path(path.toUri().toString())
                .lines(Files.readAllLines(path).size())
                .length(FileUtils.byteCountToDisplaySize(path.toFile().length()))
                .type(Files.probeContentType(path))
                .build();

        return new ResponseEntity<>(codeDTO, HttpStatus.OK);
    }

}
