package com.example.jobtes.View;

import com.example.jobtes.Model.Member;

import java.util.List;

public interface LoginContract {
    interface View {
        void showInvalidInputError();

        void showInputSuccess(boolean isAdmin);

        void setPresenter(Presenter presenter);
    }

    interface Presenter {
        void loginAdmin(String username, String password);
        void loginMember(String username, String password);
    }
}