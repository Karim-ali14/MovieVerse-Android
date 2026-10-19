package com.karimali.movieverse.features.onboarding.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karimali.movieverse.databinding.OnboardingItemLayoutBinding
import com.karimali.movieverse.features.onboarding.data.models.OnboardingModel

class OnBoardingAdapter() : ListAdapter<OnboardingModel,OnBoardingAdapter.OnboardingViewHolder>(DiffUtilCallback) {

    companion object DiffUtilCallback :  DiffUtil.ItemCallback<OnboardingModel>() {
        override fun areItemsTheSame(
            oldItem: OnboardingModel,
            newItem: OnboardingModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OnboardingModel,
            newItem: OnboardingModel
        ): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): OnboardingViewHolder {

        val binding = OnboardingItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OnboardingViewHolder(binding = binding)
    }

    override fun onBindViewHolder(
        holder: OnboardingViewHolder, position: Int
    ) {
        holder.bind(getItem(position))
    }
    class OnboardingViewHolder(private val binding: OnboardingItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OnboardingModel) {
            binding.modelItem = item
            binding.executePendingBindings()
        }
    }
}