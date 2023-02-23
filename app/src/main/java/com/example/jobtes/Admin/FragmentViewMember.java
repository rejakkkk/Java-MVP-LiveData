package com.example.jobtes.Admin;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobtes.Database.DatabaseClient;
import com.example.jobtes.Model.Member;
import com.example.jobtes.Presenter.AdminPresenter;
import com.example.jobtes.R;
import com.example.jobtes.View.AdminContract;

import java.util.List;
public class FragmentViewMember extends Fragment implements AdminContract.View {

    private RecyclerView membersRecyclerView;
    private AdminContract.Presenter presenter;
    private ViewMemberAdapter adapter;

    public FragmentViewMember() {
        // empty constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        presenter = new AdminPresenter(this, DatabaseClient.getInstance(requireActivity().getApplicationContext()).databaseDao());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_member, container, false);
        membersRecyclerView = view.findViewById(R.id.rv_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        membersRecyclerView.setLayoutManager(layoutManager);
        adapter = new ViewMemberAdapter(presenter);
        membersRecyclerView.setAdapter(adapter);
        presenter.loadMembers();
        return view;
    }

    @Override
    public void showError() {
        // handle error
    }

    @Override
    public void showSuccess() {
        // handle success
    }

    @Override
    public void showMembers(List<Member> members) {
        adapter.setMembers(members);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(AdminContract.Presenter presenter) {
        this.presenter = presenter;
    }
}