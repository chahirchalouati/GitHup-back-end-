/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders.Repository;

import Coders.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Chahir Chalouati
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserNameOrEmail(String param, String param0);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u "
            + "WHERE u.email = :email")
    Boolean existByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u "
            + "WHERE u.userName = :username")
    Boolean existByUserName(@Param("username") String username);

    public User findByUserName(String username);
}
