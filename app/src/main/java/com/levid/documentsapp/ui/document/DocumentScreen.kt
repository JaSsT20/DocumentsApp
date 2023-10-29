@file:OptIn(ExperimentalMaterial3Api::class)

package com.levid.documentsapp.ui.document

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.levid.documentsapp.data.remote.dtos.DocumentDto

@Composable
fun DocumentScreen(
    viewModel: DocumentViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Listado de documentos",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
        )
        DocumentsList(documents = state.documentsList)
    }
}
@Composable
fun DocumentsList(documents: List<DocumentDto>){
    LazyColumn{
        items(documents){ document ->
            DocumentItem(document = document)
        }
    }
}
@Composable
fun DocumentItem(document: DocumentDto){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onSurface),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = document.clientName,
                color = MaterialTheme.colorScheme.primary,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onSurface),
        ) {
            Column(
                modifier = Modifier.weight(1f).border(1.dp, MaterialTheme.colorScheme.onSurface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("RNC: ${document.rnc}")
            }
            Column(
                modifier = Modifier.weight(1f).border(1.dp, MaterialTheme.colorScheme.onSurface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Número: ${document.documentId}")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.onSurface),
        ){
            Column(
                modifier = Modifier.weight(1f).border(1.dp, MaterialTheme.colorScheme.onSurface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Cantidad: ${document.quantity}")
            }
            Column(
                modifier = Modifier.weight(1f).border(1.dp, MaterialTheme.colorScheme.onSurface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Monto: ${document.total}")
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun ItemPreview(){
    DocumentsList(documents = listOf(
            DocumentDto(1, 123141, 100.00, 200.00, "Soluciones Almonte Gil"),
            DocumentDto(1, 123141, 100.00, 200.00, "Soluciones Tejada"),
            DocumentDto(1, 123141, 100.00, 200.00, "Soluciones Martínez")
        )
    )
}