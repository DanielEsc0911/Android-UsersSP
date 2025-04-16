package com.pam.android.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pam.android.userssp.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pam.android.userssp.R
import com.pam.android.userssp.User
import com.pam.android.userssp.databinding.ItemUserAltBinding

class UserAdapter(private val users: MutableList<User>, private val listerner: onClickListener):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_user_alt, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        val truePosition = position + 1
        with(holder) {
            setListener(user, truePosition)
            binding.tvOrder.text = (truePosition).toString() //holder.binding.tvOrder.text
            binding.tvName.text = user.getFullName()
            Glide.with(context)
                .load(user.url)
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = users.size
    fun remove(position: Int) {
        users.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUserAltBinding.bind(view)

        fun setListener(user: User, position: Int) {
            binding.root.setOnClickListener { listerner.onClick(user, position) }
        }
    }
}