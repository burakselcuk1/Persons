package com.example.persons.ui.userFragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.persons.common.tryOrLog
import com.example.persons.R
import com.example.persons.adapter.UserAdapter
import com.example.persons.base.BaseFragment
import com.example.persons.common.enums.Status
import com.example.persons.common.extensions.observe
import com.example.persons.common.extensions.observeEvent
import com.example.persons.common.utils.ProgressDialogUtil
import com.example.persons.databinding.FragmentUserBinding


class UserFragment : BaseFragment<FragmentUserBinding, UserFragmentViewModel>(
    layoutId = R.layout.fragment_user,
    viewModelClass = UserFragmentViewModel::class.java
) {

    private lateinit var adapter: UserAdapter

    override fun onInitDataBinding() {
        getUserse()
    }

    fun getUserse(){
        observeEvent(viewModel.statusData) {
            tryOrLog {
                when (it) {
                    Status.LOADING -> {
                        ProgressDialogUtil.showLoadingProgress(context = requireContext())
                        ProgressDialogUtil.start()
                    }
                    Status.SUCCESS -> {

                        binding.progressBar.visibility = View.GONE

                        observe(viewModel.users){

                            adapter = UserAdapter(it)
                            binding.userRecyclerview.layoutManager = LinearLayoutManager(context)
                            binding.userRecyclerview.adapter = adapter

                        }

                        ProgressDialogUtil.stop()
                    }
                    Status.ERROR -> {
                        ProgressDialogUtil.stop()
                    }
                }
            }
        }
    }
}