package com.teamneards.classtrack.ApiWork

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudyTipRepository {
    private val studyTipsApi: StudyTipsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.npoint.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(StudyTipsApiService::class.java)
    }

    // Fallback list of study tips in case the API fails
    private val fallbackStudyTips = listOf(
        "Break your study sessions into 25-minute intervals with 5-minute breaks (Pomodoro technique).",
        "Review your notes within 24 hours of taking them to improve retention.",
        "Teach what you've learned to someone else to solidify your understanding.",
        "Use active recall instead of passive re-reading to better remember information.",
        "Create mind maps to visualize connections between concepts.",
        "Get enough sleep - memory consolidation happens during deep sleep.",
        "Stay hydrated and eat brain-healthy foods like nuts, berries, and fish.",
        "Change study locations occasionally to improve memory through environmental cues.",
        "Test yourself regularly with practice questions instead of just reviewing notes.",
        "Break complex topics into smaller, manageable chunks to avoid feeling overwhelmed."
    )

    suspend fun getRandomStudyTip(): String {
        return try {
            withContext(Dispatchers.IO) {
                val response = studyTipsApi.getStudyTips()
                // If we get a successful response, select a random tip
                if (response.isSuccessful && response.body() != null) {
                    val tips = response.body()!!.tips
                    tips.random()
                } else {
                    // Fallback to local tips if API fails
                    fallbackStudyTips.random()
                }
            }
        } catch (e: Exception) {
            // If an exception occurs, use a fallback tip
            fallbackStudyTips.random()
        }
    }
}