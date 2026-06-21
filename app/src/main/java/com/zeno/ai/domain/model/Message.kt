package com.zeno.ai.domain.model
enum class MessageRole { USER, ASSISTANT, SYSTEM }
enum class MessageStatus { PENDING, STREAMING, COMPLETED, ERROR }
data class Message(val id: String, val conversationId: String, val role: MessageRole, val content: String, val status: MessageStatus = MessageStatus.COMPLETED, val model: String? = null)
