package com.whatsapi.restful.repository;

import com.whatsapi.restful.models.Conversation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    // @Modifying
    // @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.conversations (conversation_name ,created_at) VALUES(:conversationName, NOW()) returning conversation_id;")
    int createConversation(@Param("conversationName") String conversationName);
}
