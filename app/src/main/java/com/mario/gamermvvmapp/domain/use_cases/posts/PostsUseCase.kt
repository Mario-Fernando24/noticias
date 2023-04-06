package com.mario.gamermvvmapp.domain.use_cases.posts


data class PostsUseCase (
    val create: CreatePost,
    val getPost: GetPost,
    val getPostById: GetPostById,
    val deletePost: DeletePost,
    val updatePost: UpdatePost
)


