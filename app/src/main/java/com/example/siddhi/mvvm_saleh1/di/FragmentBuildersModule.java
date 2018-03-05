package com.example.siddhi.mvvm_saleh1.di;

import com.example.siddhi.mvvm_saleh1.view.ui.ProjectFragment;
import com.example.siddhi.mvvm_saleh1.view.ui.ProjectListFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by siddhi on 3/5/2018.
 */

@Module public abstract class FragmentBuildersModule {
  @ContributesAndroidInjector abstract ProjectFragment contributeProjectFragment();
  @ContributesAndroidInjector abstract ProjectListFragment contributeProjectListFragment();
}
