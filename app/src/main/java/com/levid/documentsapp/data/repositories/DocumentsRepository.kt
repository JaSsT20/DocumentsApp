package com.levid.documentsapp.data.repositories

import com.levid.documentsapp.data.remote.DocumentsApi
import com.levid.documentsapp.data.remote.dtos.DocumentDto
import com.levid.documentsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class DocumentsRepository @Inject constructor(
    private val documentsApi: DocumentsApi
) {
    fun getCoins(): Flow<Resource<List<DocumentDto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val coins = documentsApi.getDocuments() //descarga las monedas de internet, se supone quedemora algo

            emit(Resource.Success(coins)) //indicar que se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}