package com.example.jobtes.Presenter;

import androidx.room.EmptyResultSetException;

import com.example.jobtes.Database.DatabaseDao;
import com.example.jobtes.Model.Member;
import com.example.jobtes.View.MemberContract;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MemberPresenter implements MemberContract.Presenter {

    private MemberContract.View view;
    private DatabaseDao databaseDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MemberPresenter(MemberContract.View view, DatabaseDao databaseDao) {
        this.view = view;
        this.databaseDao = databaseDao;
        this.view.setPresenter(this);
    }

    @Override
    public void getMemberData(String username, String password) {
        databaseDao.getMemberByUsernameAndPassword(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Member>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // do something on subscribe
                    }

                    @Override
                    public void onSuccess(Member member) {
                        view.showMemberData(member);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof EmptyResultSetException) {
                            // handle empty result set
                        } else {
                            // handle other errors
                        }
                    }
                });

    }


    @Override
    public void updateMemberPassword(int kodeMember, String newPassword) {
        databaseDao.updateMemberPassword(kodeMember, newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // do something on subscribe
                    }

                    @Override
                    public void onComplete() {
                        view.showUpdateSuccess();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.showUpdateError(throwable.getMessage());
                    }
                });
    }


    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}
