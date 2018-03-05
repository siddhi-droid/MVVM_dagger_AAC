package com.example.siddhi.mvvm_saleh1.di;

import android.arch.lifecycle.ViewModelProvider;
import com.example.siddhi.mvvm_saleh1.service.repository.GitHubService;
import com.example.siddhi.mvvm_saleh1.viewmodel.ProjectViewModelFactory;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siddhi on 3/5/2018.
 */

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
  @Singleton @Provides GitHubService provideGitHubService() {
    return new Retrofit.Builder()
        .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubService.class);
  }

  @Singleton @Provides ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
    return new ProjectViewModelFactory(viewModelSubComponent.build());
  }
}
