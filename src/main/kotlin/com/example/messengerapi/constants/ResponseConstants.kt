package com.example.messengerapi.constants

enum class ResponseConstants(val value: String) {
    SUCCESS("success"),
    ERROR("error"),
    USERNAME_UNAVAILABLE("USR_0001"),
    INVALID_USER_ID("USR_0002"),
    EMPTY_STATUS("USR_0003"),
    MESSAGE_EMPTY("MES_001"),
    MESSAGE_RECIPIENT_INVALID("MES_002"),
    ACCOUNT_DEACTIVATED("GLO_001")
}
