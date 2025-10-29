package com.todos.app.data.di

import com.todos.app.data.repository.NetworkTodoRepository
import com.todos.app.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindTodoRepository(impl: NetworkTodoRepository): TodoRepository
}
