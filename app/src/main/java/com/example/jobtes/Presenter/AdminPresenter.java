package com.example.jobtes.Presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jobtes.Database.DatabaseDao;
import com.example.jobtes.Model.Member;
import com.example.jobtes.View.AdminContract;

import java.util.List;

public class AdminPresenter implements AdminContract.Presenter {
    private AdminContract.View view;
    private DatabaseDao databaseDao;
    private MutableLiveData<List<Member>> membersLiveData = new MutableLiveData<>();

    public AdminPresenter(AdminContract.View view, DatabaseDao databaseDao){
        this.view = view;
        this.databaseDao = databaseDao;
        this.view.setPresenter(this);
    }

    @Override
    public void inputData(Member member) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseDao.insertMember(member);
                LiveData<List<Member>> members = databaseDao.getAllMembers();
                membersLiveData.postValue(members.getValue());
                view.showSuccess();
            }
        }).start();
    }



    @Override
    public void loadMembers() {
        databaseDao.getAllMembers()
                .observeForever(members -> {
                    membersLiveData.postValue(members);
                    view.showMembers(members);
                });
    }

    public LiveData<List<Member>> getMembersLiveData() {
        return membersLiveData;
    }
}