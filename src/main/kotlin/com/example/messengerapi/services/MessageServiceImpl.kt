package com.example.messengerapi.services

import com.example.messengerapi.exceptions.MessageEmptyException
import com.example.messengerapi.exceptions.MessageRecipientInvalidException
import com.example.messengerapi.models.Conversation
import com.example.messengerapi.models.Message
import com.example.messengerapi.models.User
import com.example.messengerapi.repositories.ConversationRepository
import com.example.messengerapi.repositories.MessageRepository
import com.example.messengerapi.repositories.UserRepository

class MessageServiceImpl(
    val repository: MessageRepository,
    val conversationRepository: ConversationRepository,
    val conversationService: ConversationService,
    val userRepository: UserRepository
) : MessageService {

    @Throws(
        MessageEmptyException::class,
        MessageRecipientInvalidException::class
    )
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)

        if (optional.isPresent) {
            val recipient = optional.get()
            if (messageText.isNotEmpty()) {
                val conversation: Conversation =
                    if (conversationService.conversationExists(sender, recipient)) {
                        conversationService.getConversation(sender, recipient) as Conversation
                    } else {
                        conversationService.createConversation(sender, recipient)
                    }
                conversationRepository.save(conversation)

                val message = Message(sender, recipient, messageText, conversation)
                repository.save(message)
                return message
            } else {
                throw MessageRecipientInvalidException("The recipient id '$recipientId' is invalid.")
            }
        }
        throw MessageEmptyException()
    }
}