package com.todos.app.domain.di

import com.todos.app.domain.repository.TodoRepository
import com.todos.app.domain.usecase.GetTodoByIdUseCase
import com.todos.app.domain.usecase.GetTodosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetTodosUseCase(repo: TodoRepository): GetTodosUseCase = GetTodosUseCase(repo)

    @Provides
    @Singleton
    fun provideGetTodoByIdUseCase(repo: TodoRepository): GetTodoByIdUseCase = GetTodoByIdUseCase(repo)
}

