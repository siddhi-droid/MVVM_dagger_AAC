package com.example.siddhi.mvvm_saleh1.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.example.siddhi.mvvm_saleh1.service.model.Project;
import com.example.siddhi.mvvm_saleh1.service.repository.ProjectRepository;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by siddhi on 2/22/2018.
 */

public class ProjectListViewModel extends AndroidViewModel {
  private final LiveData<List<Project>> projectListObservable;

  @Inject public ProjectListViewModel(@NonNull ProjectRepository projectRepository,
      @NonNull Application application) {
    super(application);
    //projectListObservable = ProjectRepository.getProjectList("Google");
    projectListObservable = projectRepository.getProjectList("Google");
  }

  /*public ProjectListViewModel(Application application) {
    super(application);
    projectListObservable = ProjectRepository.getInstance().getProjectList("Google");
  }*/

  public LiveData<List<Project>> getProjectListObservable() {
    return projectListObservable;
  }
}