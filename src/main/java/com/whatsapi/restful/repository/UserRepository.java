package com.whatsapi.restful.repository;

import com.whatsapi.restful.models.User;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.users (user_id, email, username) VALUES(nextval('users_user_id_seq'::regclass), :email, :username);")
    void createUserQuery(@Param("email") String email, @Param("username") String username);


    @Query(nativeQuery = true, value = "SELECT user_id FROM public.users u WHERE u.email = :email limit 1")
    int getUserId(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT user_id FROM public.users u WHERE u.username = :username limit 1")
    int getUserIdByUsername(@Param("username") String username);

    @Query(nativeQuery = true, value = "select username from users where email = :email limit 1")
    String findUserExist(@Param("email")String email);

    @Query(nativeQuery = true, value = "select username from users where user_id = :id limit 1")
    String findUsernameFromId(@Param("id")int id);
}
