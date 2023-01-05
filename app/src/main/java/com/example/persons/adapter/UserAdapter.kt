package com.example.persons.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.persons.R
import com.example.persons.databinding.UsersItemBinding
import com.example.persons.model.UsersItemResponse
import com.example.persons.model.UsersResponse


class UserAdapter(private val dataSet: UsersResponse) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var binding: UsersItemBinding

    class ViewHolder(view: UsersItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = UsersItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val usersResponse: UsersItemResponse = dataSet.get(position)
        binding.usersInformation = usersResponse

        viewHolder.itemView.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("userId",""+dataSet.get(position).userId)
            bundle.putSerializable("userUrl",""+dataSet.get(position).url)

            val navigationController = Navigation.findNavController(viewHolder.itemView)
           // navigationController.navigate(R.id.action_userFragment_to_postFragment,bundle!!)

        }
    }
    override fun getItemCount() = dataSet.size

}