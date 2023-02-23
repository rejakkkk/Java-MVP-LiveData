package com.example.jobtes.View;

import com.example.jobtes.Model.Member;

import java.util.List;

public interface AdminContract {
    interface View {
        void showError();

        void showSuccess();

        void showMembers(List<Member> members);

        void setPresenter(AdminContract.Presenter presenter);
    }

    interface Presenter {
        void inputData(Member member);
        void loadMembers();
    }
}