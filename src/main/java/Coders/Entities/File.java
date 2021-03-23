/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Chahir Chalouati
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name can't be blank")
    @Lob
    private String name;
    @NotBlank(message = "URI can't be blank")
    @Lob
    private String URL;
    @JsonIgnore
    @NotBlank(message = "PATH can't be blank")
    @Column(name = "filePath")
    @Lob
    private String path;
    @Column(name = "fileLength")
    private Long length = Long.valueOf("0");

    public File(String name, String URL, String path) {
        this.name = name;
        this.URL = URL;
        this.path = path;
    }

    /* @ManyToOne
    private FileType fileType;
    @JsonBackReference
    @ManyToOne
    private User user;*/
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @UpdateTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updatedAt;

}
