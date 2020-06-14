package com.challange.hilt.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.challange.hilt.R
import com.challange.hilt.ui.main.ChallengeListListener
import com.challange.hilt.ui.main.MainAdapter
import com.challange.hilt.ui.models.Challenge
import kotlinx.android.synthetic.main.detail_fragment.*


class DetailFragment : Fragment(), ChallengeListListener {

    private val challenge: Challenge by lazy { arguments?.getParcelable("item") as Challenge }
    private val mainAdapter by lazy { MainAdapter(this) }
    private val recyclerViewLayoutManager by lazy { LinearLayoutManager(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = challenge.title

        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = recyclerViewLayoutManager
            adapter = mainAdapter
        }

        mainAdapter.updateList(listOf(challenge))
    }

    override fun onClick(item: Challenge) {
        //Doing nothing
    }

}