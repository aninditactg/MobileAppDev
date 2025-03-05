package com.teamneards.classtrack.ApiWork

import retrofit2.Response
import retrofit2.http.GET

interface StudyTipsApiService {
    @GET("9f5c4dcf6aeac883e4b5")  // Endpoint for JSON bin with study tips
    suspend fun getStudyTips(): Response<StudyTipsResponse>
}