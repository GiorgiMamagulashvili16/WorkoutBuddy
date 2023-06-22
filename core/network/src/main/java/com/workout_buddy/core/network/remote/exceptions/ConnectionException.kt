package com.workout_buddy.core.network.remote.exceptions

import java.io.IOException

class ConnectionException : IOException() {
    override val message: String
        get() = NO_INTERNET_MESSAGE

    companion object {
        private const val NO_INTERNET_MESSAGE = "no internet connection"
    }
}