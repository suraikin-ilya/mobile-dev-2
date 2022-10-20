package com.example.currencyconv.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyModel(
    @PrimaryKey val name: String,
    val value: Float,
    var chosen: Boolean
)