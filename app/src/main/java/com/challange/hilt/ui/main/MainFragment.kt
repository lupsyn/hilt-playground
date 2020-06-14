package com.challange.hilt.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challange.hilt.R
import com.challange.hilt.ui.detail.DetailFragment
import com.challange.hilt.ui.models.Challenge
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment(), ChallengeListListener {

    private val viewModel by viewModels<MainViewModel>()
    private val mainAdapter by lazy { MainAdapter(this) }
    private val navController by lazy { requireActivity().findNavController(R.id.nav_host_fragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //FIXME : auto selection for toggle, remove this line once material toggle group is used
        toggle_button_layout.setToggled(R.id.toggle_left, true)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainAdapter
        }

        viewModel.uiData.observe(viewLifecycleOwner, Observer { data ->
            Timber.i("Challenges received with size : %s", data.size)
            mainAdapter.updateList(data)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.main_fragment_menu, menu)

    override fun onClick(item: Challenge) {
        navController.navigate(R.id.detail_fragment_dest, bundleOf("item" to item))
    }
}