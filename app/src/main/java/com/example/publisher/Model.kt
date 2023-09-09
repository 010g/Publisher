package com.example.publisher

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Calendar

class Model {
    private val db = Firebase.firestore

    fun addData(
        authorEmail: String,
        authorId: String,
        authorName: String,
        title: String,
        content: String,
        category: String,
    ) {
        val articles = FirebaseFirestore.getInstance()
            .collection("articles")
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to authorEmail,
                "id" to authorId,
                "name" to authorName
            ),
            "title" to title,
            "content" to content,
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "category" to category
        )
        document.set(data)
    }

    suspend fun getData(): QuerySnapshot {
        return db.collection("articles").get().await()
    }

}


