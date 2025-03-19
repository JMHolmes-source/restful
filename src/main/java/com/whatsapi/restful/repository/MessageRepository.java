package com.whatsapi.restful.repository;

import com.whatsapi.restful.models.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
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

        @Query(nativeQuery = true, value = "select * from messages m where conversation_id = :convo_id order by m.sent_at desc limit 1")
        Message getLastMessage(@Param("convo_id") int convo_id);
    // @Modifying
    // @Transactional
    // @Query(nativeQuery = true, value = "INSERT INTO public.messages (sender_id,
    // receiver_id, message_text, sent_at, read_bool) VALUES(:user,:receiver,
    // :message, :sentat, :read);")
    // void sendMessage(
    // @Param("user") Integer user,
    // @Param("receiver") Integer receiver,
    // @Param("message") String text,
    // @Param("sentat") LocalDateTime date,
    // @Param("read") boolean read);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.messages (message_id, sender_id, conversation_id, message_text, sent_at, read_bool) VALUES(nextval('messages_message_id_seq'::regclass), :user_id, :convo_id, :text, CURRENT_TIMESTAMP, false);")
    void sendMessage(
            @Param("text") String text,
            @Param("convo_id") int convo_id,
            @Param("user_id") int user_id);

    @Query(nativeQuery = true, value = "select * from messages where conversation_id = :id")
    List<Message> getMessages(
            @Param("id") int conversation_id);

}
