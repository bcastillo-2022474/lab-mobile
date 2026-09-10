package plat.lab1.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import plat.lab1.lab6.ui.theme.Lab5Theme
import plat.lab5.Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab5Theme {
                Screen()
            }
        }
    }
}


@Preview(
    name = "Modo claro",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RestaurantScreenLightPreview() {
    Lab5Theme(
        darkTheme = false,
        dynamicColor = false
    ) {
        Screen()
    }
}

@Preview(
    name = "Modo oscuro",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RestaurantScreenDarkPreview() {
    Lab5Theme(
        darkTheme = true,
        dynamicColor = false
    ) {
        Screen()
    }
}