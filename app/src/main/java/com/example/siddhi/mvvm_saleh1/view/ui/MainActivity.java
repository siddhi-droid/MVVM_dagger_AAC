package com.example.siddhi.mvvm_saleh1.view.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.siddhi.mvvm_saleh1.R;
import com.example.siddhi.mvvm_saleh1.service.model.Project;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  @Inject DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      ProjectListFragment fragment = new ProjectListFragment();

      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragment_container, fragment, ProjectListFragment.TAG)
          .commit();
    }
  }

  public void show(Project project) {
    ProjectFragment projectFragment = ProjectFragment.forProject(project.name);

    getSupportFragmentManager().beginTransaction()
        .addToBackStack("project")
        .replace(R.id.fragment_container, projectFragment, null)
        .commit();
  }

  @Override public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
    return dispatchingAndroidInjector;
  }
}
