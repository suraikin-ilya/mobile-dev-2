package com.example.currencyconv.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val currency1: String,
    val value1: Float,
    val currency2: String,
    val value2: Float,
    val date: Long = System.currentTimeMillis(),
)