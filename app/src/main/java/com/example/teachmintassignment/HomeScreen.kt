package com.example.teachmintassignment

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryViewModel = viewModel() // Use ViewModel instance
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val repositories by viewModel.repositories.observeAsState(emptyList())
    val errorMessage by viewModel.errorMessage.observeAsState(null)
    val context = LocalContext.current // move LocalContext here

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TextField for user input
        TextField(
            value = query,
            onValueChange = { query = it },
            placeholder = { Text("Search Repositories") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Search button
        Button(
            onClick = { viewModel.searchRepositories(query.text) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Display error message if available
        if (errorMessage != null) {
            Text(text = "Error: $errorMessage", color = MaterialTheme.colorScheme.error)
        }
        // LazyColumn to display repositories
        LazyColumn {
            items(repositories) { repository ->
                RepositoryItem(repository) {
                    val intent = Intent(context, RepoDetailsActivity::class.java)
                    intent.putExtra("REPOSITORY", Gson().toJson(repository)) // Serialize repository object to JSON string
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun RepositoryItem(repository: Repository, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = repository.name, style = MaterialTheme.typography.titleMedium)
            Text(text = repository.description ?: "No description", style = MaterialTheme.typography.bodyMedium)
            Text(text = "by ${repository.owner.login}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    // Mock data for preview
    val mockRepositories = listOf(
        Repository(1, "Repo1", "Repo1_Full_Name", "http://example.com/repo1", "Description of Repo1", Owner("user1", "http://example.com/avatar1")),
        Repository(2, "Repo2", "Repo2_Full_Name", "http://example.com/repo2", "Description of Repo2", Owner("user2", "http://example.com/avatar2"))
    )

    // Create an instance of the ViewModel and set mock data
    val viewModel = RepositoryViewModel().apply {
        (repositories as MutableLiveData).value = mockRepositories // Cast to MutableLiveData
    }

    HomeScreen(viewModel = viewModel) // Call HomeScreen with ViewModel instance
}
