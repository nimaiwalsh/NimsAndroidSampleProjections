package com.nims.inbox.model

data class InboxState(
    val status: InboxStatus = InboxStatus.LOADING,
    val emails: List<Email>? = null
)

data class Email(
    val id: String,
    val title: String,
    val description: String,
)

enum class InboxStatus {
    LOADING, SUCCESS, ERROR, EMPTY
}

sealed class InboxEvent {
    object RefreshContent: InboxEvent()
    data class DeleteEmail(val id: String): InboxEvent()
}