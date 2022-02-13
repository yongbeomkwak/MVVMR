package com.yongbeom.myapplication.model.db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "gender")
    val gender:String?,

    @ColumnInfo(name = "birth")
    val birth:String?
)

