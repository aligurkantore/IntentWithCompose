package com.example.intentwithcompose.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.intentwithcompose.ui.theme.IntentWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentWithComposeTheme {
                MyApp(context = this)
            }
        }
    }
}


@Composable
fun MyApp(context: Context) {
    SimpleTextFieldAndButton(context = context)
}


@Composable
fun SimpleTextFieldAndButton(context: Context) {
    val text = remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text(text = "Enter Text") }
            )
            Button(
                onClick = {
                    if (text.value.isBlank()) {
                        Toast.makeText(context, "The field must not be empty", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val intent = Intent(context, SecondActivity::class.java)
                        intent.putExtra("data", text.value)
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
}
