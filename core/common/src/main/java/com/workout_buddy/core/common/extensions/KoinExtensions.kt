package com.workout_buddy.core.common.extensions

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

fun Module.loadUnloadKoinModule(shouldLoad: Boolean) {
    if (shouldLoad) loadKoinModules(this) else unloadKoinModules(this)
}