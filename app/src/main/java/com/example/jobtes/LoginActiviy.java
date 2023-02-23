package com.example.jobtes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jobtes.Admin.AdminActivity;
import com.example.jobtes.Database.DatabaseClient;
import com.example.jobtes.Member.MemberActivity;
import com.example.jobtes.View.LoginContract;
import com.example.jobtes.Presenter.LoginPresenter;

public class LoginActiviy extends AppCompatActivity implements LoginContract.View {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi view
        usernameEditText = findViewById(R.id.et_username);
        passwordEditText = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);

        // Inisialisasi presenter
        presenter = new LoginPresenter(this, DatabaseClient.getInstance(this).databaseDao());

        // Set listener pada tombol login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                presenter.loginAdmin(username, password);
                presenter.loginMember(username, password);
            }
        });
    }

    @Override
    public void showInvalidInputError() {
        Toast.makeText(this, "Username atau password tidak valid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputSuccess(boolean isAdmin) {
        Intent intent;
        if (isAdmin) {
            intent = new Intent(this, AdminActivity.class);
            Toast.makeText(this, "Login sebagai admin", Toast.LENGTH_SHORT).show();
        } else {
            intent = new Intent(this, MemberActivity.class);
            Toast.makeText(this, "Login sebagai member", Toast.LENGTH_SHORT).show();
        }
        startActivity(intent);
        finish();

    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}