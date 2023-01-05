package com.example.persons.ui.postFragment

import com.bumptech.glide.Glide
import com.example.persons.common.tryOrLog
import com.example.persons.R
import com.example.persons.base.BaseFragment
import com.example.persons.common.enums.Status
import com.example.persons.common.extensions.observe
import com.example.persons.common.extensions.observeEvent
import com.example.persons.common.utils.ProgressDialogUtil
import com.example.persons.databinding.FragmentPostBinding

class PostFragment : BaseFragment<FragmentPostBinding, PostFragmentViewModel>(
    layoutId = R.layout.fragment_post,
    viewModelClass = PostFragmentViewModel::class.java
) {

    override fun onInitDataBinding() {

        val args = this.arguments
        val userId: String? = args?.getString("userId","databoss")

        if (userId != null) {
            viewModel.getPost(userId.toInt())
        }

        getDetailInformations()

    }

    private fun getDetailInformations() {
        observeEvent(viewModel.statusData) {
            tryOrLog {
                when (it) {
                    Status.LOADING -> {
                        ProgressDialogUtil.showLoadingProgress(context = requireContext())
                        ProgressDialogUtil.start()
                    }
                    Status.SUCCESS -> {

                        observe(viewModel.post){

                            binding.postsInformation = it
                            val args = this.arguments
                            val userUrl: String? = args?.getString("userUrl","databoss")
                            Glide.with(this).load(userUrl).into(binding.postBackgroundImage)
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