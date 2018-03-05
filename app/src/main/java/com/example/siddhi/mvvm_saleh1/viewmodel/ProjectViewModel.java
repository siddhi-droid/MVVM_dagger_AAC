package com.example.siddhi.mvvm_saleh1.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.siddhi.mvvm_saleh1.service.model.Project;
import com.example.siddhi.mvvm_saleh1.service.repository.ProjectRepository;
import javax.inject.Inject;

/**
 * Created by siddhi on 2/22/2018.
 */

public class ProjectViewModel extends AndroidViewModel {
  private static final String TAG = ProjectViewModel.class.getName();
  private static final MutableLiveData ABSENT = new MutableLiveData();
  {
    //noinspection unchecked
    ABSENT.setValue(null);
  }

  private final LiveData<Project> projectObservable;
  //private final String projectID;
  private final MutableLiveData<String> projectID;

  public ObservableField<Project> project = new ObservableField<>();

  /*public ProjectViewModel(@NonNull Application application, final String projectID) {
    super(application);
    this.projectID = projectID;

    projectObservable = ProjectRepository.getInstance().getProjectDetails("Google", projectID);
  }*/

  @Inject public ProjectViewModel(@NonNull ProjectRepository projectRepository, @NonNull Application application) {
    super(application);
    this.projectID = new MutableLiveData<>();

    projectObservable = Transformations.switchMap(projectID, input -> {
      if (input.isEmpty()) {
        Log.i(TAG, "ProjectViewModel projectID is absent!!!");
        return ABSENT;
      }

      Log.i(TAG, "ProjectViewModel projectID is " + projectID.getValue());
      return projectRepository.getProjectDetails("Google", projectID.getValue());
    });
  }

  public LiveData<Project> getObservableProject() {
    return projectObservable;
  }

  public void setProject(Project project) {
    this.project.set(project);
  }

  public void setProjectID(String projectID) { this.projectID.setValue(projectID); }

  /*public static class Factory extends ViewModelProvider.NewInstanceFactory {
    @NonNull
    private final Application application;
    private final String projectID;

    public Factory(@NonNull Application application, String projectID) {
      this.application = application;
      this.projectID = projectID;
    }

    @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      return (T) new ProjectViewModel(application, projectID);
    }
  }*/
}
