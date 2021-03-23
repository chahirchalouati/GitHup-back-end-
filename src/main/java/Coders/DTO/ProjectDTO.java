/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.DTO;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDTO {

    @NotBlank(message = "name of project can'tbe blank")
    private String name;
    @NotBlank(message = "description of project can'tbe blank")
    private String description;

    private MultipartFile file;
}
