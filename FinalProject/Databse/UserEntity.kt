package com.teamneards.classtrack.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserEntity(
    @PrimaryKey val userId: String,
    val name: String,
    val email: String,
    val studentId: String,
    val semester: String,
    val section: String,
    val batch: String
)
