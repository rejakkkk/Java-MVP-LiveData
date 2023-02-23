package com.example.jobtes.Presenter;

import android.text.TextUtils;

import com.example.jobtes.Database.DatabaseDao;
import com.example.jobtes.View.LoginContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private DatabaseDao databaseDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoginPresenter(LoginContract.View view, DatabaseDao databaseDao) {
        this.view = view;
        this.databaseDao = databaseDao;
        this.view.setPresenter(this);
    }

    @Override
    public void loginAdmin(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            view.showInvalidInputError();
        } else {
            // username dan password admin
            if (username.equals("admin") && password.equals("admin")) {
                view.showInputSuccess(true);
            } else {
                view.showInvalidInputError();
            }
        }
    }

    @Override
    public void loginMember(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            view.showInvalidInputError();
        } else {
            // login menggunakan RxJava dan Room
            Disposable disposable = databaseDao.getMemberByUsernameAndPassword(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(member -> {
                        if (member != null) {
                            view.showInputSuccess(false);
                        } else {
                            view.showInvalidInputError();
                        }
                    }, throwable -> {
                        view.showInvalidInputError();
                    });
            compositeDisposable.add(disposable);
        }
    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }
}