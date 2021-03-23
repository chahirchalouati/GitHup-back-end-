/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Chahir Chalouati
 */
@Data
@Builder
public class ResponseCodeDTO implements Serializable {

    @JsonProperty(value = "name")
    private String fileName;
    private String content;
    private String path;
    private String type;
    private String length;
    private int lines;
}
