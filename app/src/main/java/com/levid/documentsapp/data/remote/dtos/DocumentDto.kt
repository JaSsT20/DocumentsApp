package com.levid.documentsapp.data.remote.dtos

import com.squareup.moshi.Json

data class DocumentDto(
    @Json(name = "Numero")
    val documentId: Int,
    @Json(name = "Rnc")
    val rnc: Int,
    @Json(name = "Cantidad")
    val quantity: Double,
    @Json(name = "Monto")
    val total: Double,
    @Json(name = "NombreCliente")
    val clientName: String,
)
