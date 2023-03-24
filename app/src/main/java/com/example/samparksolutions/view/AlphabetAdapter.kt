package com.example.samparksolutions.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samparksolutions.R
import com.example.samparksolutions.data.Alphabet
import com.example.samparksolutions.databinding.ItemBinding

class AlphabetAdapter(
    private val list: MutableList<Alphabet>
) : RecyclerView.Adapter<AlphabetAdapter.AlphabetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlphabetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return AlphabetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlphabetViewHolder, position: Int) {
        val alphabet = list[position]
        holder.itemView.findViewById<TextView>(R.id.text_view_letter).text = alphabet.letter
        holder.bind(alphabet)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(newList: List<Alphabet>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class AlphabetViewHolder(
        private val binding: ItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(alphabet: Alphabet) {
            binding.textViewLetter.text = alphabet.letter
            binding.textViewScore.text = alphabet.score.toString()

            // set the background color of the item view based on the selection status
            if (alphabet.selected) {
                binding.root.setBackgroundColor(Color.LTGRAY)
            } else {
                binding.root.setBackgroundColor(Color.TRANSPARENT)
            }

            // add a click listener to the root view of the item view
            binding.root.setOnClickListener {
                alphabet.selected = !alphabet.selected // toggle the selection status
                notifyItemChanged(bindingAdapterPosition) // update the UI
            }

            binding.buttonPlus.setOnClickListener {
                alphabet.score++ // increment the score
                binding.textViewScore.text = alphabet.score.toString() // update the UI
            }

            binding.buttonMinus.setOnClickListener {
                alphabet.score-- // decrement the score
                binding.textViewScore.text = alphabet.score.toString() // update the UI
            }
            binding.executePendingBindings()
        }
    }

}
