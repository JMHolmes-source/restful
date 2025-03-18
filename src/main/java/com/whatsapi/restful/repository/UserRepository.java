package com.whatsapi.restful.repository;

import com.whatsapi.restful.models.User;

import jakarta.transaction.Transactional;

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
}
