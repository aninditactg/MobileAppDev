package com.teamneards.classtrack

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.teamneards.classtrack.ui.theme.ClassTrackTheme
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClassTrackTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost()
                }
            }
        }

        // ⚠️ Run this function **ONLY ONCE** to upload JSON data
        // After successful upload, remove or comment it out
        //uploadRoutineJsonToFirestore()
    }

    private fun uploadRoutineJsonToFirestore() {
        val db = FirebaseFirestore.getInstance()
        try {
            // Read JSON file from assets
            val inputStream = assets.open("1 2 3 4 5 6 7 8 ALL.txt")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = bufferedReader.use { it.readText() }

            // Convert JSON string to JSONObject
            val jsonObject = JSONObject(jsonString)

            // Convert JSONObject to a Map that Firestore can store
            val dataMap = jsonObject.toMap()

            // Upload JSON to Firestore under "processed_schedules"
            db.collection("processed_schedules")
                .document("all_routines")  // The document where the routine is stored
                .set(dataMap)
                .addOnSuccessListener {
                    Log.d("Firestore", "✅ JSON uploaded successfully!")
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "❌ Error uploading JSON", e)
                }

        } catch (e: Exception) {
            Log.e("Firestore", "❌ Exception: ${e.message}")
        }
    }

    // Extension function to convert JSONObject to Map<String, Any>
    private fun JSONObject.toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        val keys = keys()
        while (keys.hasNext()) {
            val key = keys.next()
            val value = get(key)

            // Convert nested JSONObjects to Maps
            map[key] = when (value) {
                is JSONObject -> value.toMap()
                is JSONArray -> value.toList()
                else -> value
            }
        }
        return map
    }

    // Extension function to convert JSONArray to List<Any>
    private fun JSONArray.toList(): List<Any> {
        val list = mutableListOf<Any>()
        for (i in 0 until length()) {
            val value = get(i)
            list.add(
                when (value) {
                    is JSONObject -> value.toMap()
                    is JSONArray -> value.toList()
                    else -> value
                }
            )
        }
        return list
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClassTrackTheme {
        AppNavHost()
    }
}
