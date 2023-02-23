package com.example.jobtes.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "members")
public class Member {
    @PrimaryKey(autoGenerate = true)
    public int kode_member;
    public String nama;
    public String tanggal_lahir;
    public String alamat;
    public String jenis_kelamin;
    public String username;
    public String password;

    // Constructor
    public Member(String nama, String tanggal_lahir, String alamat, String jenis_kelamin, String username, String password) {
        this.nama = nama;
        this.tanggal_lahir = tanggal_lahir;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.username = username;
        this.password = password;
    }

    public int getKode_member() {
        return kode_member;
    }

    public void setKode_member(int kode_member) {
        this.kode_member = kode_member;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
