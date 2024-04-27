package com.example.bai1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtTaiKhoan, edtMatKhau, edtSDT, edtEmail;
    private Button btnDangKy, btnNhapLai;
    private TextView tvTaiKhoan, tvMatKhau, tvSDT, tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        setControl();

    }

    private void setView() {
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau =  findViewById(R.id.edtMatKhau);
        edtSDT =  findViewById(R.id.edtSdt);
        edtEmail =  findViewById(R.id.edtEmail);

        btnDangKy = findViewById(R.id.btnDangKy);
        btnNhapLai =findViewById(R.id.btnNhapLai);

        tvTaiKhoan =  findViewById(R.id.tvTaiKhoan);
        tvMatKhau = findViewById(R.id.tvMatKhau);
        tvSDT =  findViewById(R.id.tvSdt);
        tvEmail =  findViewById(R.id.tvEmail);
    }

    private void setControl() {
        btnDangKy.setOnClickListener(v->{
            tvTaiKhoan.setText(String.format("Tài khoản: %s", edtTaiKhoan.getText()));
            tvMatKhau.setText(String.format("Mật khẩu: %s", edtMatKhau.getText()));
            tvSDT.setText(String.format("Số điện thoại: %s", edtSDT.getText()));
            tvEmail.setText(String.format("Email: %s", edtEmail.getText()));
        });

        btnNhapLai.setOnClickListener(v->{
            edtTaiKhoan.setText("");
            edtMatKhau.setText("");
            edtSDT.setText("");
            edtEmail.setText("");

            tvTaiKhoan.setText("Tài khoản:");
            tvMatKhau.setText("Mật khẩu:");
            tvSDT.setText("Số điện thoại:");
            tvEmail.setText("Email:");
        });


    }
}