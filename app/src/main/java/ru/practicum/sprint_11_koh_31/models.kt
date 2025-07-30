package ru.practicum.sprint_11_koh_31

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.jvm.java

data class NewsResponse(
    val result: String,
    val data: Data
)

data class Data(
    val title: String,
    val items: List<NewsItem>
)

data class NewsItem(
    val id: String,
    val title: String,
    val type: String,
    val created: Date,

)



class CustomDateTypeAdapter : TypeAdapter<Date>() {

    // https://ru.wikipedia.org/wiki/ISO_8601
    companion object {
        const val FORMAT_PATTERN = "yyyy-MM-DD'T'hh:mm:ss:SSS"
    }

    private val formatter = SimpleDateFormat(FORMAT_PATTERN, Locale.getDefault())
    override fun write(out: JsonWriter, value: Date) {
        out.value(formatter.format(value))
    }

    override fun read(`in`: JsonReader): Date {
        return formatter.parse(`in`.nextString())
    }
    
}
