package com.example.mytransactoins.ui.feature.transaction.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytransactoins.databinding.ItemTransactionBinding
import com.example.mytransactoins.ui.utils.viewBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var items = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemBinding = parent.viewBinding { layoutInflater, viewGroup, isAttachToParent ->
            ItemTransactionBinding.inflate(
                layoutInflater,
                viewGroup,
                isAttachToParent
            )
        }
        return TransactionViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<String>) {
        this.items = items
        // We are not appending data, we are providing a new one.
        // Hence notifyDataSetChanged is ok to do now
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.binding.textViewTransactionLabel.text = items[position]
    }

    class TransactionViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root)
}
