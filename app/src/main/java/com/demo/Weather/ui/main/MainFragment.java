package com.demo.Weather.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.demo.Weather.R;
import com.demo.Weather.databinding.MainFragmentBinding;
import com.demo.Weather.models.Root;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    MainFragmentBinding fragmentBinding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentBinding = MainFragmentBinding.inflate(inflater, container, false);
        View view = fragmentBinding.getRoot();
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init(requireContext());
        mViewModel.setForecastData();
        mViewModel.getForecastLiveData().observe(getViewLifecycleOwner(), new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                fragmentBinding.textViewPlaceName.setText(root.location.region);
                fragmentBinding.textViewWeatherDegree.setText(String.valueOf(root.current.temp_c));
                fragmentBinding.textViewDate.setText(root.location.localtime);
                fragmentBinding.textViewWeatherType.setText(root.current.condition.text);
                String x = root.current.condition.icon;

                Glide.with(requireActivity()).load("https:"+x).into(fragmentBinding.imageView);
            }
        });
        return view;
    }



}