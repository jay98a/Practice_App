package com.example.practice_api.ui.wallpaper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.practice_api.databinding.FragmentWallpaperBinding;

public class WallpaperFragment extends Fragment {

    private FragmentWallpaperBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WallpaperViewModel wallpaperViewModel =
                new ViewModelProvider(this).get(WallpaperViewModel.class);

        binding = FragmentWallpaperBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}