package com.workout_buddy.core.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel

abstract class BaseVm: ViewModel() {
    val screenAlertChannel = Channel<BaseChannelState>(Channel.UNLIMITED)
}