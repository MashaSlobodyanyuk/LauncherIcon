package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val likes:Int = 9_999,
    val share: Int = 1097
)


