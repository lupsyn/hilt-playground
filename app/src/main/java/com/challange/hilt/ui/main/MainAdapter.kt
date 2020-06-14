package com.challange.hilt.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.challange.hilt.R
import com.challange.hilt.ui.models.Challenge
import com.challange.hilt.ui.models.ChallengeType
import com.challange.hilt.ui.models.ChallengeType.Location
import com.challange.hilt.ui.models.ChallengeType.Photo


class MainAdapter(private val listener: ChallengeListListener) :
    RecyclerView.Adapter<ChallengeViewHolder>() {
    var collection = listOf<Challenge>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChallengeViewHolder(inflater, parent, listener)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val movie: Challenge = collection[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = collection.size

    fun updateList(newList: List<Challenge>) {
        val diffResult = DiffUtil.calculateDiff(getDiff(newList))
        diffResult.dispatchUpdatesTo(this)
        collection = newList
    }

    private fun getDiff(newList: List<Challenge>) = ChallengeListsDiffCallback(collection, newList)

}

class ChallengeViewHolder(
    inflater: LayoutInflater, parent: ViewGroup,
    private val listener: ChallengeListListener
) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {

    private var imageView: ImageView? = null
    private var title: TextView? = null
    private var desc: TextView? = null
    private var points: TextView? = null
    private var challenge: Challenge? = null
    private var itemContainer: RelativeLayout

    init {
        imageView = itemView.findViewById(R.id.imageView)
        title = itemView.findViewById(R.id.title)
        desc = itemView.findViewById(R.id.description)
        points = itemView.findViewById(R.id.points)
        itemContainer = itemView.findViewById(R.id.itemContainer)
    }

    fun bind(challenge: Challenge) {
        when (challenge.type) {
            Location -> imageView?.setImageResource(R.drawable.ic_mission_gps)
            ChallengeType.List -> imageView?.setImageResource(R.drawable.ic_mission_text)
            Photo -> imageView?.setImageResource(R.drawable.ic_mission_picvid)
        }
        title?.text = challenge.title
        desc?.text = challenge.desc
        points?.text = """${challenge.points}${imageView?.resources?.getString(R.string.pts)}"""
        itemContainer.setOnClickListener {
            challenge?.apply { listener.onClick(challenge) }
        }
    }
}

interface ChallengeListListener {
    fun onClick(item: Challenge)
}

class ChallengeListsDiffCallback(
    private val oldList: List<Challenge>,
    private val newList: List<Challenge>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition] as Challenge
        val newItem = newList[newItemPosition] as Challenge

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition] as Challenge
        val newItem = newList[newItemPosition] as Challenge

        return oldItem == newItem
    }
}