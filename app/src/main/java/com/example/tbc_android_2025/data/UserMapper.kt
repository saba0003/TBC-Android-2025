package com.example.tbc_android_2025.data

object UserMapper {

    fun mapToDomain(dto: UserResponseDto): User =
        User(
            id = dto.id,
            image = dto.image,
            owner = dto.owner,
            lastMessage = dto.lastMessage,
            lastActive = dto.lastActive,
            unreadMessages = dto.unreadMessages,
            isTyping = dto.isTyping,
            lastMessageType = MessageType.fromString(value = dto.lastMessageType)
        )

    fun mapListToDomain(dtos: List<UserResponseDto>): List<User> =
        dtos.map { mapToDomain(dto = it) }

}
