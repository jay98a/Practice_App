package com.example.practice_api.ui.meme_frag;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.practice_api.databinding.FragmentMemeBinding;

public class MemeFragment extends Fragment {

    private FragmentMemeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MemeViewModel memeViewModel =
                new ViewModelProvider(this).get(MemeViewModel.class);

        binding = FragmentMemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}