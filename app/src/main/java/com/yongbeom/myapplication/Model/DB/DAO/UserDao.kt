package com.yongbeom.myapplication.Model.DB.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongbeom.myapplication.Model.DB.Entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from user_table ORDER BY name ASC")
    fun getAlphabetizedUsers(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //onConflict = OnConflictStrategy.IGNORE: 선택된 onConflict
    // 전략은 이미 목록에 있는 단어와 정확하게 같다면 새 단어를 무시합니다.
    suspend fun insert(userEntity: UserEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}