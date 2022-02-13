package com.yongbeom.myapplication.viewModel

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.yongbeom.myapplication.model.db.AppDataBase
import com.yongbeom.myapplication.model.db.Entity.UserEntity
import com.yongbeom.myapplication.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    var main_text:ObservableField<String> = ObservableField("Main")
    val mApplication=application
    val repository: Repository =  Repository(AppDataBase.getDatabase(application,viewModelScope))
    //repository 인스턴스 얻기
    var allUsers: LiveData<List<UserEntity>> = repository.allUsers

    /**
    * 버튼 텍스트와 연결될 main_text 변수는 Observable 변수로 선언해 안의 value가 바뀌면 버튼 텍스트가 동적으로 바뀌게 됩니다.
    * */

    fun insert(userEntity: UserEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(userEntity)
    }

    fun onClickButton(){
        // TODO: Click 시 Room에 데이터를 추가해야 함.
        Toast.makeText(mApplication,"Click!", Toast.LENGTH_SHORT).show()
    }

}