package com.example.jobtes.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.jobtes.Database.DatabaseClient;
import com.example.jobtes.Model.Member;
import com.example.jobtes.Presenter.AdminPresenter;
import com.example.jobtes.R;
import com.example.jobtes.View.AdminContract;

import java.util.List;
public class FragmentInputMember extends Fragment implements AdminContract.View{

    private EditText etNama, etTanggalLahir, etAlamat, etJenisKelamin, etUsername, etPassword;
    private Button btnSave;
    private AdminContract.Presenter presenter;

    public FragmentInputMember(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_input_member, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNama = view.findViewById(R.id.et_nama);
        etTanggalLahir = view.findViewById(R.id.et_tanggal_lahir);
        etAlamat = view.findViewById(R.id.et_alamat);
        etJenisKelamin = view.findViewById(R.id.et_jenis_kelamin);
        etUsername = view.findViewById(R.id.et_username);
        etPassword = view.findViewById(R.id.et_password);
        btnSave = view.findViewById(R.id.btn_input);

        presenter = new AdminPresenter(this, DatabaseClient.getInstance(requireActivity().getApplicationContext()).databaseDao());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String tanggalLahir = etTanggalLahir.getText().toString();
                String alamat = etAlamat.getText().toString();
                String jenisKelamin = etJenisKelamin.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (nama.isEmpty() || tanggalLahir.isEmpty() || alamat.isEmpty() || jenisKelamin.isEmpty() || username.isEmpty() || password.isEmpty()){
                    showError();
                } else {
                    // Buat objek member
                    Member member = new Member(nama, tanggalLahir, alamat, jenisKelamin, username, password);

                    // Simpan data menggunakan presenter
                    presenter.inputData(member);
                }

            }
        });
    }


    @Override
    public void showError() {
        Toast.makeText(getContext(), "Harap isi Semua kolom", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showMembers(List<Member> members) {

    }


    @Override
    public void setPresenter(AdminContract.Presenter presenter) {
        this.presenter = presenter;
    }
}