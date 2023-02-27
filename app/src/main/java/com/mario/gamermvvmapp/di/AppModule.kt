package com.mario.gamermvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.mario.gamermvvmapp.data.repository.AuthRepositoryImp
import com.mario.gamermvvmapp.domain.repository.AuthRepository
import com.mario.gamermvvmapp.domain.use_cases.auth.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//proveer la instancia de firebase con la inyeccion de dependencia
@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    //siempre se empieza por provider de proveer
    @Provides
    fun providerFirebaseAuth(): FirebaseAuth =FirebaseAuth.getInstance()

    @Provides
    fun providerAuthRepository(imp:  AuthRepositoryImp): AuthRepository = imp

    @Provides
    fun providerAuthUseCase(repository: AuthRepository) = AuthUseCases(
        getCurrentUserg = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        register = Register(repository)
    )
}