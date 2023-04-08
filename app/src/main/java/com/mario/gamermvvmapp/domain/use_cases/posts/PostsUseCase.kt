package com.mario.gamermvvmapp.domain.use_cases.posts

import com.mario.gamermvvmapp.domain.use_cases.users.SaveImage


data class PostsUseCase (
    val create: CreatePost,
    val getPost: GetPost,
    val getPostById: GetPostById,
    val deletePost: DeletePost,
    val updatePost: UpdatePost,
    val updatePostImage: UpdatePostImage,
    val addLike: AddLike,
    val deleteLike: DeleteLike
)


