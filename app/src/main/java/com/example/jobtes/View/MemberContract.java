package com.example.jobtes.View;

import com.example.jobtes.Model.Member;

public interface MemberContract {
    interface View {
        void showMemberData(Member member);
        void showUpdateSuccess();
        void showUpdateError(String message);
        void setPresenter(MemberContract.Presenter presenter);

    }

    interface Presenter {
        void getMemberData(String username, String password);
        void updateMemberPassword(int kodeMember, String newPassword);
        void onDestroy();
    }
}
