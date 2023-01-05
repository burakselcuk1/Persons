package com.example.persons.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.persons.R
import com.example.persons.base.BaseActivity
import com.example.persons.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    layoutId = R.layout.activity_main,
    viewModelClass = MainViewModel::class.java
) {
    override fun onInitDataBinding() {
    }
}