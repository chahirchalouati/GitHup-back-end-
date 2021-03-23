/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Repository;

import Coders.Entities.Authoritie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface AuthoritieRepository extends JpaRepository<Authoritie, Integer> {

    public Authoritie findByAuthoritie(String authoritie);

}
