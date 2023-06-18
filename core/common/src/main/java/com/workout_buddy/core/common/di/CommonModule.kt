package com.workout_buddy.core.common.di

import com.workout_buddy.core.common.domain.useCase.date.DateHandlerUseCase
import com.workout_buddy.core.common.domain.useCase.date.DateHandlerUseCaseImpl
import org.koin.dsl.module

val commonModule = module {
    single<DateHandlerUseCase> { DateHandlerUseCaseImpl() }
}