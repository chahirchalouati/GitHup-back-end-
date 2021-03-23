/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Email;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "USER_NAME can't be blank")
    @Column(unique = true)
    private String userName;
    @NotBlank(message = "FULL_NAME can't be blank")

    @Email(message = "INVALID E-MAIL")
    @Column(unique = true)
    private String email;

    @Lob
    // @Min(value = 7, message = "PASSWORD can't be less then 8 caracter ")
    // @Max(value = 32, message = "PASSWORD can't be more then 32 caracter ")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonIgnore
    @OneToOne()
    private Authoritie authoritie;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @UpdateTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updatedAt;

}
