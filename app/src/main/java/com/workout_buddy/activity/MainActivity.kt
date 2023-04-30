package com.workout_buddy.activity

import android.app.Activity
import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.workout_buddy.navigation.NavigationView
import com.workout_buddy.onboarding.impl.data.datastore.OnBoardingDatastoreImpl
import com.workout_buddy.onboarding.impl.domain.datastore.OnBoardingDatastore
import com.workout_buddy.ui.theme.DarkColors
import com.workout_buddy.ui.theme.LightColors
import com.workout_buddy.ui.theme.WorkoutBuddyTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val activityViewModel: ActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutBuddyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationView(navController)
                }
            }
        }
    }
}

@Composable
private fun SetUpTheme(isDarkMode: Boolean) {
    val view = LocalView.current
    val statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
    if (view.isInEditMode.not()) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = statusBarColor
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = isDarkMode
        }
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    com.workout_buddy.ui.theme.WorkoutBuddyTheme {
        Greeting("Android")
    }
}

//FEATURES
//პირველი სქრინი - თემის არჩევა, ინფოს არჩევა მომხმარებელზე(წონა,ასაკი აშ), პირველი ჩართვის დროს ამოვა მხოლოდ
//ჰოუმ სქრინი - ვორკოუტების დამატება, ტაიმერის ჩართვა, კალენდარის გახსნა, ვორკოუტების ლისტინგი
// დამატების სქრინი - ძველი ჩაწერილი ვორკოუტების ამოღება ან/და ახლის დამატება, როცა ახალს დაამატებს თუ იგივე სახელით არ არის დამატებული დბ ში ჩაემატოს
// კალენდრის დიალოგი - აირჩევს რიცხვს და ჰოუმზე წამოვა მაგ რიცხვის ვორკოუტების ინფო