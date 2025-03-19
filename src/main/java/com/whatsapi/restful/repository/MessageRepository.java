package com.whatsapi.restful.repository;

import com.whatsapi.restful.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;


@Repository
public interface MessageRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.messages (sender_id, receiver_id, message_text, sent_at, read_bool) "
            + "VALUES (:user, :receiver, :message, :sentat, :read) "
            + "ON CONFLICT DO NOTHING") // Optional: Handle duplicates if any
    void batchSendMessage(
        @Param("user") Integer user,
        @Param("receiver") Integer receiver, // This will be iterated in the service layer
        @Param("message") String message,
        @Param("sentat") LocalDateTime sentat,
        @Param("read") boolean read);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.messages (sender_id, receiver_id, message_text, sent_at, read_bool) VALUES(:user,:receiver, :message, :sentat, :read);")
    void sendMessage(
        @Param("user") Integer user, 
        @Param("receiver") Integer receiver, 
        @Param("message") String text, 
        @Param("sentat") LocalDateTime date, 
        @Param("read") boolean read);
    
}
