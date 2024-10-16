@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.teachmintassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.teachmintassignment.ui.theme.TeachMintAssignmentTheme

class MainActivity : ComponentActivity() {
    private val repositoryViewModel: RepositoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeachMintAssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = repositoryViewModel
                    )
                }
            }
        }
    }
}
