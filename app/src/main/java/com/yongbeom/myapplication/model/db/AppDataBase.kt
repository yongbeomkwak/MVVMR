package com.yongbeom.myapplication.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yongbeom.myapplication.model.db.dao.UserDao
import com.yongbeom.myapplication.model.db.Entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase(){
    abstract fun userDao():UserDao


    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        private const val DATABASE_NAME = "app_database"
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDataBase {
            //첫 초기화시 null이므로 synchronized 실행된 후 instance가 리턴되고
            // 그 다음 부터는 INSTANCE가 사용된다            return INSTANCE ?: synchronized(this){ //this:companion object
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(AppDatabaseCallBack(scope))
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
                /**
                 * Room이 기기의 기존 데이터베이스를 현재 버전으로 업그레이드하기 위한 이전 경로를 찾지 못하면
                 * IllegalStateException이 발생합니다.
                 * 이전 경로가 누락되었을 때 기존 데이터를 잃어도 괜찮다면 데이터베이스 생성 시 다음과 같이
                 * fallbackToDestructiveMigration() 빌더 메서드를 호출합니다.
                 * */

            }
        }
    }


    private class AppDatabaseCallBack(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            // 데이터베이스가 처음 생성되었을때 할 행동을 코딩할 수 있습니다.
            super.onCreate(db)
            INSTANCE?.let { database->
                scope.launch { //insert 작업이므로 coroutine
                    populateDatabase(database.userDao())
                }

            }
        }

        suspend fun populateDatabase(userDao: UserDao)
        {
            userDao.insert(UserEntity(name = "Lilly",gender = "여",birth = "1933-07-25"))

        }
    }
}