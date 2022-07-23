package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover Mock de Notícias
        List<News> news = new ArrayList<>();
        news.add(new News("Criciúma com desfalque importante", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sodales sapien non turpis gravida, pellentesque pulvinar justo aliquet. Maecenas eleifend ligula id nulla fermentum mollis."));
        news.add(new News("Tigre joga Segunda contra o CSA", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sodales sapien non turpis gravida, pellentesque pulvinar justo aliquet. Maecenas eleifend ligula id nulla fermentum mollis."));
        news.add(new News("Copa do Mundo Feminina está finalizando", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sodales sapien non turpis gravida, pellentesque pulvinar justo aliquet. Maecenas eleifend ligula id nulla fermentum mollis."));

        this.news.setValue(news);

    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}