package com.challange.hilt.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.challange.hilt.R;
import com.challange.hilt.ui.models.Challenge;
import com.savvyapps.togglebuttonlayout.ToggleButtonLayout;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Pair;

import static androidx.core.os.BundleKt.bundleOf;

@AndroidEntryPoint
public class MainFragment extends Fragment implements ChallengeListListener {
    private MainAdapter adapter;
    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToggleButtonLayout toggleButton = view.findViewById(R.id.toggle_button_layout);
        toggleButton.setToggled(R.id.toggle_left, true);
        if (adapter == null) {
            adapter = new MainAdapter(this);
        }
        adapter.setListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.getUiData().observe(getViewLifecycleOwner(), challenges -> adapter.updateList(challenges));

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_fragment_menu, menu);
    }

    @Override
    public void onClick(@NotNull Challenge item) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.detail_fragment_dest, bundleOf(new Pair<>("item", item)));
    }
}
