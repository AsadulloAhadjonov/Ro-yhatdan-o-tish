package com.example.royxatdanotish.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.royxatdanotish.Mudels.User
import com.example.royxatdanotish.R
import com.example.royxatdanotish.databinding.ItemRvBinding

class AdapterRv(var list: ArrayList<User> = ArrayList(), val rv:Rv) : RecyclerView.Adapter<AdapterRv.Vh>() {
    inner class Vh(var item: ItemRvBinding) : RecyclerView.ViewHolder(item.root) {
        fun onBind(user: User) {

                item.itemImage.setImageURI(Uri.parse(user.image))
                item.name.text = user.nameSourname
                item.number.text = user.phone

            item.root.setOnClickListener {
                rv.click(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    interface Rv{
        fun click(user: User)
    }

}