package com.example.siddhi.mvvm_saleh1.service.repository;

import com.example.siddhi.mvvm_saleh1.service.model.Project;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by siddhi on 2/22/2018.
 */

public interface GitHubService {
  String HTTPS_API_GITHUB_URL = "https://api.github.com/";

  @GET("users/{user}/repos") Call<List<Project>> getProjectList(@Path("user") String user);

  @GET("/repos/{user}/{reponame}") Call<Project> getProjectDetails(@Path("user") String user,
      @Path("reponame") String projectName);
}
