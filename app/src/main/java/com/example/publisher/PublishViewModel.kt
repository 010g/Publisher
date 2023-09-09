package com.example.publisher

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PublishViewModel : ViewModel(), Observable {

    private val model = Model()

    private val _title = MutableLiveData<String>()
    private val _category = MutableLiveData<String>()
    private val _content = MutableLiveData<String>()
    private val _articleItems = MutableLiveData<List<ArticleItem>>()

    val title: MutableLiveData<String>
        get() = _title

    val category: MutableLiveData<String>
        get() = _category

    val content: MutableLiveData<String>
        get() = _content

    val articleItems: MutableLiveData<List<ArticleItem>>
        get() = _articleItems

    // Function to handle article publishing
    fun publishArticle(authorEmail: String, authorName: String, authorId: String) {
        val articleTitle = _title.value ?: ""
        val articleCategory = _category.value ?: ""
        val articleContent = _content.value ?: ""

        model.addData(
            authorEmail,
            authorId,
            authorName,
            articleTitle,
            articleContent,
            articleCategory
        )

        // Clear input fields after publishing
        _title.value = ""
        _category.value = ""
        _content.value = ""

    }


    fun fetchArticles() {
        viewModelScope.launch {
            try {
                val result = model.getData()
                val articleItemList = mutableListOf<ArticleItem>()

                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    val title = document.getString("title") as String
                    val author = document.get("author") as Map<String, Any>
                    val name = author["name"] as String
                    val category = document.getString("category") as String
                    val createdTime = document.getLong("createdTime")
                    val content = document.getString("content") as String
                    var postTime = ""
                    if(createdTime != null){
                        postTime = formatTimestamp(createdTime)
                    }else{
                        postTime="yyyy-MM-dd HH:mm"
                    }
                    articleItemList.add(ArticleItem(title, name, category, postTime, content))
                }

                // Update LiveData with the retrieved data
                withContext(Dispatchers.Main) {
                    _articleItems.value = articleItemList
                }
            } catch (exception: Exception) {
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
        }
    }

    // Implement data binding property change notification
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    fun formatTimestamp(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val date = Date(timestamp)
        return dateFormat.format(date)
    }
}
