package kz.nfactorial.nfactorialapp.home.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kz.nfactorial.nfactorialapp.databinding.ItemFilterChipBinding
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.recycler.HomeSimpleSimpleAdapter.ViewHolder

class HomeSimpleSimpleAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val _list: MutableList<ChipItem> = mutableListOf()

    fun submitList(list: List<ChipItem>) {
        _list.clear()
        _list.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterChipBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = _list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _list.get(position)
        holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: ItemFilterChipBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChipItem) {
            binding.name.text = item.name
            binding.name.isEnabled = false
        }
    }
}

class HomeSimpleListAdapter(
    private val onClickItem: (ChipItem) -> Unit,
): ListAdapter<ChipItem, HomeSimpleListAdapter.ViewHolder>(ChipItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterChipBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onClickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: ItemFilterChipBinding,
        private val onClickItem: (ChipItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChipItem) {
            binding.name.text = item.name
            binding.root.setOnClickListener { onClickItem(item) }
        }
    }

    class ChipItemDiffUtil: DiffUtil.ItemCallback<ChipItem>() {

        override fun areItemsTheSame(oldItem: ChipItem, newItem: ChipItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChipItem, newItem: ChipItem): Boolean {
            return oldItem == newItem
        }
    }
}