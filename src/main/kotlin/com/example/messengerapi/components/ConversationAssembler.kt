package com.example.messengerapi.components

import com.example.messengerapi.helpers.objects.ConversationListVO
import com.example.messengerapi.helpers.objects.ConversationVO
import com.example.messengerapi.helpers.objects.MessageVO
import com.example.messengerapi.models.Conversation
import com.example.messengerapi.services.ConversationServiceImpl
import org.springframework.stereotype.Component

@Component
class ConversationAssembler(
    val conversationService: ConversationServiceImpl,
    val messageAssembler: MessageAssembler
) {
    fun toConveresationVO(conversation: Conversation, userId: Long):
            ConversationVO {
        val conversationMessages: ArrayList<MessageVO> = arrayListOf()
        conversation.messages?.mapTo(conversationMessages) {
            messageAssembler.toMessageVO(it)
        }
        return ConversationVO(
            conversation.id,
            conversationService.nameSecondParty(conversation, userId),
            conversationMessages
        )
    }

    fun toConversationListVO(
        conversations: List<Conversation>,
        userId: Long
    ): ConversationListVO {
        val conversationListVO = conversations.map {
            toConveresationVO(it, userId)
        }
        return ConversationListVO(conversationListVO)
    }
}