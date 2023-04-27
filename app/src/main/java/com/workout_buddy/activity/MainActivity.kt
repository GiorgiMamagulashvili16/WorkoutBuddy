package com.workout_buddy.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.workout_buddy.navigation.NavigationView
import com.workout_buddy.ui.theme.WorkoutBuddyTheme

class MainActivity : ComponentActivity() {
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