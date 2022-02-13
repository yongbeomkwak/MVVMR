package com.yongbeom.myapplication.model.repository

import androidx.lifecycle.LiveData
import com.yongbeom.myapplication.model.db.AppDataBase
import com.yongbeom.myapplication.model.db.Entity.UserEntity
import com.yongbeom.myapplication.model.db.dao.UserDao

class Repository(mDatabase:AppDataBase) {
    private val userDao:UserDao=mDatabase.userDao()
    val allUsers:LiveData<List<UserEntity>> =userDao.getAlphabetizedUsers()


    companion object{
        private var sInstance:Repository? =null
        fun getRepo(database:AppDataBase):Repository{
            return sInstance
                ?: synchronized(this){
                    val instance=Repository(database)
                    sInstance=instance
                    instance
                }

        }
    }

    suspend fun insert(userEntity: UserEntity){
        userDao.insert(userEntity)
    }
}