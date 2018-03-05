package com.example.siddhi.mvvm_saleh1.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.example.siddhi.mvvm_saleh1.service.model.Project;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by siddhi on 2/22/2018.
 */

@Singleton public class ProjectRepository {
  private GitHubService gitHubService;
  //private static ProjectRepository projectRepository;

  @Inject public ProjectRepository(GitHubService gitHubService) {
    //TODO this gitHubService instance will be injected using Dagger in part #2 ...
    /*Retrofit retrofit = new Retrofit.Builder().baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    gitHubService = retrofit.create(GitHubService.class);*/

    //After Dagger
    this.gitHubService = gitHubService;
  }

  /*public synchronized static ProjectRepository getInstance() {
    //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
    if (projectRepository == null) {
      if (projectRepository == null) {
        projectRepository = new ProjectRepository();
      }
    }
    return projectRepository;
  }*/

  public LiveData<List<Project>> getProjectList(String userId) {
    final MutableLiveData<List<Project>> data = new MutableLiveData<>();

    gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
      @Override public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
        data.setValue(response.body());
      }

      @Override public void onFailure(Call<List<Project>> call, Throwable t) {
        // TODO better error handling in part #2 ...
        data.setValue(null);
      }
    });

    return data;
  }

  public LiveData<Project> getProjectDetails(String userID, String projectName) {
    final MutableLiveData<Project> data = new MutableLiveData<>();

    gitHubService.getProjectDetails(userID, projectName).enqueue(new Callback<Project>() {
      @Override public void onResponse(Call<Project> call, Response<Project> response) {
        simulateDelay();
        data.setValue(response.body());
      }

      @Override public void onFailure(Call<Project> call, Throwable t) {
        // TODO better error handling in part #2 ...
        data.setValue(null);
      }
    });

    return data;
  }

  private void simulateDelay() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}