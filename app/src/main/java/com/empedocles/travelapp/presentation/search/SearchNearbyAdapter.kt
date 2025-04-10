package com.empedocles.travelapp.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.empedocles.travelapp.R
import com.empedocles.travelapp.databinding.FragmentSearchNearbyrecyclerItemBinding
import com.empedocles.travelapp.domain.model.TravelModel
import com.empedocles.travelapp.util.circularProgressFactory
import com.empedocles.travelapp.util.downloadFromUrl

class SearchNearbyAdapter : ListAdapter<TravelModel, SearchNearbyAdapter.NearbyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyViewHolder {
        val itemBinding = FragmentSearchNearbyrecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NearbyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NearbyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NearbyViewHolder(private val binding: FragmentSearchNearbyrecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(travelModel: TravelModel) {
            val bookMarkDrawable = if (travelModel.isBookmark) R.drawable.ic_bookmark_selected else R.drawable.ic_bookmark
            binding.bookmarkButton.setImageResource(bookMarkDrawable)
            binding.imageView.setOnClickListener {
                val bundle = bundleOf("id" to travelModel.id)
                it.findNavController().navigate(R.id.action_global_detailFragment, bundle)
            }
            binding.root.context as? LifecycleOwner
            binding.travelModel = travelModel
            binding.imageView.downloadFromUrl(
                travelModel.images.get(0).url, circularProgressFactory(binding.root.context)
            )
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<TravelModel>() {
        override fun areItemsTheSame(oldItem: TravelModel, newItem: TravelModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TravelModel, newItem: TravelModel): Boolean {
            return oldItem == newItem
        }
    }
}