package com.example.roomdb_kotlin.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb_kotlin.R
import com.example.roomdb_kotlin.databinding.DataCardBinding
import com.example.roomdb_kotlin.model.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userList = emptyList<User>()

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = DataCardBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.data_card, parent ,false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.binding.idText.text = currentItem.id.toString()
        holder.binding.fnameText.text = currentItem.firstName
        holder.binding.lnameText.text = currentItem.lastName
        holder.binding.ageText.text = currentItem.age.toString()

        holder.binding.dataCard.setOnClickListener {
            val action = ListFragmentDirections.actionListFragment2ToUpdateFragment2(currentItem)
            holder.binding.dataCard.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}
