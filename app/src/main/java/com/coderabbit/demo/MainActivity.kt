package com.coderabbit.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.coderabbit.demo.ui.theme.CodeRabbitDemoTheme

class MainActivity : ComponentActivity() {
    /**
     * Initializes the activity, enables edge-to-edge rendering, and sets the Compose UI to display the
     * app theme with a full-screen Scaffold that hosts the TodoApp.
     *
     * The Scaffold's content padding is forwarded to TodoApp via Modifier.padding(innerPadding).
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     * shut down, this contains the data it most recently supplied; otherwise `null`.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeRabbitDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
/**
 * Displays the app's Todo screen within CodeRabbitDemoTheme for Android Studio's composable preview.
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodeRabbitDemoTheme {
        TodoApp()
    }
}