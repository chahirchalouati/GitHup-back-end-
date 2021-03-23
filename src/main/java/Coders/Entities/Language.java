/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
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
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Language(String name) {
        this.name = name;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @UpdateTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updatedAt;
}
