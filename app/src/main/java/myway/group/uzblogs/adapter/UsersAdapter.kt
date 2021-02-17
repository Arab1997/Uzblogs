package myway.group.uzblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.post_item_layout.view.*
import kotlinx.android.synthetic.main.posts_item_layout.view.*
import kotlinx.android.synthetic.main.user_item_layout.view.*
import myway.group.uzblogs.R
import myway.group.uzblogs.model.UsersModel

class UsersAdapter(val items: List<UsersModel>) : RecyclerView.Adapter<UsersAdapter.ItemHolder>() {

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder { //qaysi shablonga joylashishi

        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.posts_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.itemView.first_name.text = item.first_name
        holder.itemView.last_name.text = item.last_name
        holder.itemView.email.text = item.email

        Glide.with(holder.itemView.context).load(item.avatar).into(holder.itemView.avatar)
    }

    override fun getItemCount(): Int {  // nechtaligi
        return items.count()

    }
}