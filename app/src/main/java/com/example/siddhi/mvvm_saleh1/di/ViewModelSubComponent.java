package com.example.siddhi.mvvm_saleh1.di;

import com.example.siddhi.mvvm_saleh1.viewmodel.ProjectListViewModel;
import com.example.siddhi.mvvm_saleh1.viewmodel.ProjectViewModel;
import dagger.Subcomponent;

/**
 * Created by siddhi on 3/5/2018.
 */
@Subcomponent public interface ViewModelSubComponent {
  @Subcomponent.Builder interface Builder {
    ViewModelSubComponent build();
  }

  ProjectListViewModel projectListViewModel();

  ProjectViewModel projectViewModel();
}