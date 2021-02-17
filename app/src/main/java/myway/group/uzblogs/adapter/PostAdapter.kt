package myway.group.uzblogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item_layout.view.*
import kotlinx.android.synthetic.main.user_item_layout.view.*
import myway.group.uzblogs.R
import myway.group.uzblogs.model.PostModel
import myway.group.uzblogs.model.UserModel

class PostAdapter(val items: List<PostModel>) : RecyclerView.Adapter<PostAdapter.ItemHolder>() {

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder { //qaysi shablonga joylashishi

        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.post_item_layout, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]

        holder.itemView.title.text = item.title
        holder.itemView.created_at.text = item.created_at
        holder.itemView.body.text = item.body
    }
    override fun getItemCount(): Int {  // nechtaligi
        return items.count()
    }
}