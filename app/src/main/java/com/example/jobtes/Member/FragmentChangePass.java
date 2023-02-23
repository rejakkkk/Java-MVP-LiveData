package com.example.jobtes.Member;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jobtes.Database.DatabaseClient;
import com.example.jobtes.Model.Member;
import com.example.jobtes.Presenter.MemberPresenter;
import com.example.jobtes.R;
import com.example.jobtes.View.MemberContract;

public class FragmentChangePass extends Fragment implements MemberContract.View {

    private EditText etPassword;
    private Button btnUpdate;
    private int kodeMember;
    private MemberContract.Presenter presenter;

    public FragmentChangePass() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etPassword = view.findViewById(R.id.et_change_password);
        btnUpdate = view.findViewById(R.id.btn_update_password);

        presenter = new MemberPresenter(this, DatabaseClient.getInstance(requireActivity().getApplicationContext()).databaseDao());

        SharedPreferences sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String username = sharedPref.getString("USERNAME", "");
        String password = sharedPref.getString("PASSWORD", "");

        presenter.getMemberData(username, password);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = etPassword.getText().toString();
                if (kodeMember > 0) {
                    presenter.updateMemberPassword(kodeMember, newPassword);
                } else {
                    Toast.makeText(getContext(), "Tidak dapat melakukan update password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showMemberData(Member member) {
        kodeMember = member.getKode_member();
        etPassword.setText("");
    }

    @Override
    public void showUpdateSuccess() {
        Toast.makeText(getContext(), "Update password berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpdateError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MemberContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
