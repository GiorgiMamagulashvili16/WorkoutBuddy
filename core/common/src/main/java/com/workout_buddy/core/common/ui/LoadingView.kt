package com.workout_buddy.core.common.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.workout_buddy.core.common.R

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    val anim = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loader_anim))

    LottieAnimation(composition = anim.value, modifier = modifier.fillMaxSize())
}