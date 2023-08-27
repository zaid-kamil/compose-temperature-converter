package com.example.converter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterApp(
    vm: MyViewModel = viewModel()
) {
    val uiState = vm.uiState.collectAsState()
    Scaffold { contentPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Text(
                "My Converter",
                style = MaterialTheme.typography.displayLarge
            )
            OutlinedTextField(
                value = uiState.value.userData,
                onValueChange = { vm.onTextChange(it) },
                placeholder = { Text("Enter a number") },
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = if (uiState.value.isFahrenheit) "Fahrenheit" else "Celsius")
                Switch(
                    checked = uiState.value.isFahrenheit,
                    onCheckedChange = { vm.updateSwitchState(it) },
                )
            }
            Button(onClick = { vm.onUserSubmit() }) {
                Text("Convert")
            }
            Text(
                text = uiState.value.result,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }
}