package com.example.teachmintassignment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.teachmintassignment.ui.theme.TeachMintAssignmentTheme
import com.google.gson.Gson

class RepoDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repositoryJson = intent.getStringExtra("REPOSITORY")
        val repository = repositoryJson?.let {
            Gson().fromJson(it, Repository::class.java)
        }

        if (repository != null) {
            setContent {
                TeachMintAssignmentTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RepoDetailsScreen(repository = repository, onLinkClick = { openLink(repository.html_url) })
                    }
                }
            }
        } else {
            // Handle the case when repository data is null
            finish()
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

@Composable
fun RepoDetailsScreen(repository: Repository, onLinkClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = repository.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = repository.description ?: "No description available.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Owner: ${repository.owner.login}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Repository Link: ${repository.html_url}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { onLinkClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoDetailsPreview() {
    TeachMintAssignmentTheme{
        RepoDetailsScreen(
            repository = Repository(
                id = 1,
                name = "Sample Repository",
                full_name = "sampleOwner/sampleRepo",
                html_url = "https://github.com/sampleOwner/sampleRepo",
                description = "This is a sample description for the repository.",
                owner = Owner(login = "sampleOwner", avatar_url = "https://github.com/sampleOwner.png")
            ),
            onLinkClick = {}
        )
    }
}
