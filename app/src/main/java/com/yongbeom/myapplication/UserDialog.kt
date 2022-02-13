package com.yongbeom.myapplication

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.yongbeom.myapplication.databinding.UserDialogBinding
import com.yongbeom.myapplication.model.db.Entity.UserEntity
import com.yongbeom.myapplication.viewModel.MainViewModel
import java.util.*

class UserDialog constructor(context: Context) : Dialog(context) {
    private val viewModel:MainViewModel = MainViewModel(context.applicationContext as Application)
    private var mBinding:UserDialogBinding? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= UserDialogBinding.inflate(layoutInflater)

        setContentView(mBinding!!.root)

        // 다이얼로그의 배경을 투명으로 만든다.
        Objects.requireNonNull(window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 버튼 리스너 설정
        mBinding!!.buttonSave.setOnClickListener {
            // '확인' 버튼 클릭시 data insert

            viewModel.insert(
                UserEntity(name = mBinding!!.userName.text.toString(),gender = mBinding!!.userGender.text.toString(),birth = mBinding!!.userBirth.text.toString())
            )
            // Custom Dialog 종료
            dismiss()
        }
        mBinding!!.buttonCancel.setOnClickListener {
            // '취소' 버튼 클릭시
            // Custom Dialog 종료
            dismiss()
        }

    }
}