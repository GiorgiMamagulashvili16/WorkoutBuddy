package com.workout_buddy.core.database.data.repository

import com.workout_buddy.core.common.domain.useCase.date.DateHandlerUseCase
import com.workout_buddy.core.database.data.local.dao.SelectedWorkoutsDao
import com.workout_buddy.core.database.domain.repository.SelectedWorkoutRepository
import com.workout_buddy.core.database.data.local.entity.SelectedWorkoutEntity

internal class SelectedWorkoutRepositoryImpl(
    private val selectedWorkoutsDao: SelectedWorkoutsDao,
    private val dateHandlerUseCase: DateHandlerUseCase
) : SelectedWorkoutRepository {

    override suspend fun insertSelectedWorkout(selectedWorkoutEntity: SelectedWorkoutEntity) {
        val data = selectedWorkoutEntity.copy(
            date = dateHandlerUseCase.getCurrentTimeString()
        )
        selectedWorkoutsDao.insertWorkout(data)
    }

    override suspend fun getSelectedWorkoutsByDate(date: Long?): List<SelectedWorkoutEntity> {
        val dateString = date?.let {
            dateHandlerUseCase.getTimeStringFromMs(it)
        } ?: dateHandlerUseCase.getCurrentTimeString()

        return selectedWorkoutsDao.getSelectedWorkoutsByDate(dateString)
    }
}