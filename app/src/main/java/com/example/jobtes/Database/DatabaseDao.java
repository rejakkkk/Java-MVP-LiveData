package com.example.jobtes.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.jobtes.Model.Member;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface DatabaseDao {
    @Query("SELECT * FROM members")
    LiveData<List<Member>> getAllMembers();

    @Insert
    void insertMember(Member member);

    @Query("SELECT * FROM members WHERE username = :username AND password = :password")
    Single<Member> getMemberByUsernameAndPassword(String username, String password);

    @Query("UPDATE members SET password = :newPassword WHERE kode_member = :kodeMember")
    Completable updateMemberPassword(int kodeMember, String newPassword);

}
