package com.example.siddhi.mvvm_saleh1.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.siddhi.mvvm_saleh1.R;
import com.example.siddhi.mvvm_saleh1.databinding.FragmentProjectListBinding;
import com.example.siddhi.mvvm_saleh1.di.Injectable;
import com.example.siddhi.mvvm_saleh1.service.model.Project;
import com.example.siddhi.mvvm_saleh1.view.adapter.ProjectAdapter;
import com.example.siddhi.mvvm_saleh1.view.callback.ProjectClickCallback;
import com.example.siddhi.mvvm_saleh1.viewmodel.ProjectListViewModel;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectListFragment extends Fragment implements Injectable {
  public static final String TAG = "ProjectListFragment";
  private ProjectAdapter projectAdapter;
  private FragmentProjectListBinding binding;

  @Inject ViewModelProvider.Factory viewModelFactory;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false);

    projectAdapter = new ProjectAdapter(projectClickCallback);
    binding.projectList.setAdapter(projectAdapter);
    Log.e("FirstTime","" + binding.getIsLoading());
    binding.setIsLoading(true);
    Log.e("SecondTime","" + binding.getIsLoading());

    return binding.getRoot();
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    final ProjectListViewModel viewModel =
        ViewModelProviders.of(this, viewModelFactory).get(ProjectListViewModel.class);
    observeViewModel(viewModel);
  }

  private void observeViewModel(ProjectListViewModel viewModel) {
    viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
      @Override public void onChanged(@Nullable List<Project> projects) {
        if (projects != null) {
          binding.setIsLoading(false);
          Log.e("ThirdTime","" + binding.getIsLoading());
          projectAdapter.setProjectList(projects);
        }
      }
    });
  }

  private final ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
    @Override public void onClick(Project project) {
      if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
        ((MainActivity) getActivity()).show(project);
      }
    }
  };
}
