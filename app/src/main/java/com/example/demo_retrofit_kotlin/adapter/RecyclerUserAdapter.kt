package com.example.demo_retrofit_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.demo_retrofit_kotlin.R
import com.example.demo_retrofit_kotlin.`interface`.ItemRecyclerUserInterface
import com.example.demo_retrofit_kotlin.item.ItemRecyclerUser
import java.util.*

class RecyclerUserAdapter(private val mData: ArrayList<ItemRecyclerUser>?) :
    RecyclerView.Adapter<RecyclerUserAdapter.NewViewHolder>() {

    private var mItemRecyclerUserInterface: ItemRecyclerUserInterface? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)
        return NewViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val item: ItemRecyclerUser = mData!![position]
        holder.binData(item)
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    inner class NewViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDetail: TextView = itemView.findViewById(R.id.tvDetail)
        fun binData(item: ItemRecyclerUser) {
            tvTitle.text = item.getTvTitle()
            tvDetail.text = item.getTvDetail()
        }

        init {
            itemView.setOnClickListener {
                mItemRecyclerUserInterface?.itemClicked(
                    adapterPosition
                )
            }
        }
    }

    fun setItemRecyclerUserInterface(mItemRecyclerUserInterface: ItemRecyclerUserInterface?) {
        this.mItemRecyclerUserInterface = mItemRecyclerUserInterface
    }

    val data: ArrayList<ItemRecyclerUser>?
        get() = mData

}
