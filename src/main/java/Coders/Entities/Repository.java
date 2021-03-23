/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Repository implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can't be blank")
    private String name;

    private Boolean isPrivate = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Directory> directorie = new HashSet<>();

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @UpdateTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updatedAt;
}
