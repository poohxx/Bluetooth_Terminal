package com.poohxx.bluetooth_terminal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.poohxx.bluetooth_terminal.databinding.ListItemBinding

class RcViewAdapter: ListAdapter<ListItem, RcViewAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(view:View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)

        fun setData(item: ListItem) = with(binding) {
            tvName.text = item.name
            tvMac.text = item.mac

        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
                return ItemHolder(view)
            }
        }
    }

        class ItemComparator : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem.mac == newItem.mac
            }

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
                return oldItem == newItem
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
            return ItemHolder.create(parent)
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            holder.setData(getItem(position))
        }
    }


