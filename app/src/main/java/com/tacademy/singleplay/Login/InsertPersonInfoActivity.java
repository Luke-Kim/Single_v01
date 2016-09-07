package com.tacademy.singleplay.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tacademy.singleplay.MainActivity;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.SignInData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsertPersonInfoActivity extends AppCompatActivity {
    @BindView(R.id.btn_send_info)
    Button btn_send_info;

    EditText editName, editPhone;
    static public SignInData signInData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_person_info);
        ButterKnife.bind(this);


        editName = (EditText)findViewById(R.id.edit_name);
        editPhone = (EditText)findViewById(R.id.edit_phone);

        //Button btn = (Button)findViewById(R.id.btn_send_info);
        btn_send_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InsertPersonInfoActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                String name = editName.getText().toString();

                String phone = editPhone.getText().toString();
                signInData = new SignInData(name, phone);
                Intent intent = new Intent(InsertPersonInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
