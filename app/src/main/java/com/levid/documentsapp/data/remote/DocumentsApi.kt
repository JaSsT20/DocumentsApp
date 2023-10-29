package com.levid.documentsapp.data.remote

import com.levid.documentsapp.data.remote.dtos.DocumentDto
import retrofit2.http.GET
import retrofit2.http.POST

interface DocumentsApi {
    @GET("documentos")
    suspend fun getDocuments(): List<DocumentDto>
}