package com.example.siddhi.mvvm_saleh1.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import com.example.siddhi.mvvm_saleh1.di.ViewModelSubComponent;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by siddhi on 3/5/2018.
 */

@Singleton public class ProjectViewModelFactory implements ViewModelProvider.Factory {

  private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

  @Inject public ProjectViewModelFactory(/*final */ViewModelSubComponent viewModelSubComponent) {
    creators = new ArrayMap<>();

    creators.put(ProjectViewModel.class, () -> viewModelSubComponent.projectViewModel());
    creators.put(ProjectListViewModel.class, () -> viewModelSubComponent.projectListViewModel());
  }

  @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    Callable<? extends ViewModel> creator = creators.get(modelClass);
    if (creator == null) {
      for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
        if (modelClass.isAssignableFrom(entry.getKey())) {
          creator = entry.getValue();
          break;
        }
      }
    }
    if (creator == null) {
      throw new IllegalArgumentException("Unknown model class " + modelClass);
    }
    try {
      return (T) creator.call();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
