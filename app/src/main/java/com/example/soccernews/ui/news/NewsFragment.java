package com.example.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.soccernews.MainActivity;
import com.example.soccernews.databinding.FragmentNewsBinding;
import com.example.soccernews.ui.adapter.NewsAdapter;
import com.google.android.material.snackbar.Snackbar;

public class NewsFragment extends Fragment {

    private NewsViewModel NewsViewModel;
    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        observeNews();

        NewsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
                switch (state){
                    case DOING:
                        binding.srlNews.setRefreshing(true);
                        break;
                    case DONE:
                        binding.srlNews.setRefreshing(false);
                        break;
                    case ERROR:
                        binding.srlNews.setRefreshing(false);
                        Snackbar.make(binding.srlNews,"Network Error", Snackbar.LENGTH_SHORT).show();
                }
            });

            binding.srlNews.setOnRefreshListener(NewsViewModel::findNews);

            return root;
    }

    private void observeNews() {
        NewsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.getDb().newsDao().save(updatedNews);
                }
            }));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}