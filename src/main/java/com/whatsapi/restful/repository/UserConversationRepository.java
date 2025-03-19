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
public interface UserConversationRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.userconversations (user_id, conversation_id) VALUES(:userID,:conversationID);")
    void createUserConversation(
        @Param("userID") Integer userID, 
        @Param("conversationID") Integer conversationID 
    );
    
}
