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
public interface ConversationRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.conversations (ConversationName,CreatedAt) VALUES(:conversationName,:createdAt);")

    void createConversation(
        @Param("conversationName") String conversationName, // This will be iterated in the service layer
        @Param("createdAt") LocalDateTime createdAt
    );
}
