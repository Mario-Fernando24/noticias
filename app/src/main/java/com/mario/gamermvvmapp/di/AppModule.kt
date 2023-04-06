package com.mario.gamermvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mario.gamermvvmapp.core.Constant.POSTS_COLECTION
import com.mario.gamermvvmapp.core.Constant.USERS_COLECTION
import com.mario.gamermvvmapp.data.repository.AuthRepositoryImp
import com.mario.gamermvvmapp.data.repository.PostsRepositoryImp
import com.mario.gamermvvmapp.data.repository.UsersRepositoryImp
import com.mario.gamermvvmapp.domain.repository.AuthRepository
import com.mario.gamermvvmapp.domain.repository.PostsRepository
import com.mario.gamermvvmapp.domain.repository.UsersRepository
import com.mario.gamermvvmapp.domain.use_cases.auth.*
import com.mario.gamermvvmapp.domain.use_cases.posts.*
import com.mario.gamermvvmapp.domain.use_cases.users.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.Context.Storage
import javax.inject.Named

//proveer la instancia de firebase con la inyeccion de dependencia
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    //inyectar firestorage
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore =Firebase.firestore


    //agregar la inyeccion para el almacenamiento de las imagenes
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()
   //referenciamos la carpeta donde se van a guardar las imagenes

    //==========================================================================================
    //               COLECCION USERS AGREGAR IMAGEN AND COLECTION
    //==========================================================================================
    @Provides
    @Named(USERS_COLECTION)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference= storage.reference.child(USERS_COLECTION)

    @Provides
    @Named(USERS_COLECTION)
    fun provideUserRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS_COLECTION)


    @Provides
    @Named(POSTS_COLECTION)
    fun provideStoragePostsRef(storage: FirebaseStorage): StorageReference= storage.reference.child(POSTS_COLECTION)

    @Provides
    @Named(POSTS_COLECTION)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS_COLECTION)
    //==========================================================================================
    //                        END
    //==========================================================================================


    //siempre se empieza por provider de proveer
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth =FirebaseAuth.getInstance()

    @Provides
    fun providerAuthRepository(imp:  AuthRepositoryImp): AuthRepository = imp

    @Provides
    fun providerUsersRepository(imp:  UsersRepositoryImp): UsersRepository = imp
    @Provides
    fun providerPostRepository(imp:  PostsRepositoryImp): PostsRepository = imp

    @Provides
    fun providerAuthUseCase(repository: AuthRepository) = AuthUseCases(
        getCurrentUserg = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        register = Register(repository)
    )

    @Provides
    fun providerUsesUseCase(repository: UsersRepository) = UsersUseCase(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
        )

    @Provides
    fun providerPostsUseCase(repository: PostsRepository) = PostsUseCase(
        create = com.mario.gamermvvmapp.domain.use_cases.posts.CreatePost(repository),
        getPost = GetPost(repository),
        getPostById = GetPostById(repository),
        deletePost = DeletePost(repository),
        updatePost = UpdatePost(repository),
        updatePostImage = UpdatePostImage(repository)
    )
}