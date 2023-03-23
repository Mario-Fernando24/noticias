package com.mario.gamermvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mario.gamermvvmapp.core.Constant.USERS_COLECTION
import com.mario.gamermvvmapp.data.repository.AuthRepositoryImp
import com.mario.gamermvvmapp.data.repository.UsersRepositoryImp
import com.mario.gamermvvmapp.domain.repository.AuthRepository
import com.mario.gamermvvmapp.domain.repository.UsersRepository
import com.mario.gamermvvmapp.domain.use_cases.auth.*
import com.mario.gamermvvmapp.domain.use_cases.users.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.Context.Storage

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
    @Provides
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference= storage.reference.child(USERS_COLECTION)

    @Provides
    fun provideUserRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS_COLECTION)
    //siempre se empieza por provider de proveer
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth =FirebaseAuth.getInstance()

    @Provides
    fun providerAuthRepository(imp:  AuthRepositoryImp): AuthRepository = imp

    @Provides
    fun providerUsersRepository(imp:  UsersRepositoryImp): UsersRepository = imp

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
}