package com.example.messengerapi.components

import com.example.messengerapi.helpers.objects.UserListVO
import com.example.messengerapi.helpers.objects.UserVO
import com.example.messengerapi.models.User

class UserAssembler {
    fun toUserVO(user: User): UserVO {
        return UserVO(
            user.id,
            user.username,
            user.phoneNumber,
            user.status,
            user.createdAt.toString()
        )
    }

    fun toUserListVO(users: List<User>): UserListVO {
        val userVOList = users.map { toUserVO(it) }
        return UserListVO(userVOList)
    }
}