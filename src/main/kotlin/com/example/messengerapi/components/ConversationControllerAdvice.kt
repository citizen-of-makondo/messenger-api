package com.example.messengerapi.components

import com.example.messengerapi.constants.ErrorResponse
import com.example.messengerapi.exceptions.ConversationInvalidException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ConversationControllerAdvice {
    @ExceptionHandler
    fun conversationInvalidException(conversationInvalidException: ConversationInvalidException):
            ResponseEntity<ErrorResponse> {
        val res = ErrorResponse("", conversationInvalidException.message)
        return ResponseEntity.unprocessableEntity().body(res)
    }
}