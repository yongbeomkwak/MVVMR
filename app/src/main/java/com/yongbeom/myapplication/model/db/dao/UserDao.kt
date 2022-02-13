package com.yongbeom.myapplication.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yongbeom.myapplication.model.db.Entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from user_table ORDER BY name ASC")
    fun getAlphabetizedUsers(): LiveData<List<UserEntity>>
    //LiveData는 스스로 백그라운드 쓰레드로 동작합니다.
    // LiveData가 해당 데이터를 main스레드로 전달하기 때문에 UI를 변경할 수 있습니다.


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //onConflict = OnConflictStrategy.IGNORE: 선택된 onConflict
    // 전략은 이미 목록에 있는 단어와 정확하게 같다면 새 단어를 무시합니다.
    suspend fun insert(userEntity: UserEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}