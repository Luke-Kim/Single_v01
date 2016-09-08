package com.tacademy.singleplay.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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

    EditText editName, editPhone, editEmail;
    static public SignInData signInData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_person_info);
        ButterKnife.bind(this);

        editEmail = (EditText)findViewById(R.id.edit_email);
        editName = (EditText)findViewById(R.id.edit_name);
        editPhone = (EditText)findViewById(R.id.edit_phone);
        editEmail.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        ///// 입력 최대 길이 제한



        btn_send_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InsertPersonInfoActivity.this);
                builder.setTitle("가입정보 확인");
                builder.setMessage("사용자 이름 : " + editName.getText().toString() + "\n"
                        + "E-Mail : " + editEmail.getText().toString() + "\n"
                        + "전화번호 : " + editPhone.getText().toString() + "\n" + "입력 정보가 확실합니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(InsertPersonInfoActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                        String name = editName.getText().toString();

                        String phone = editPhone.getText().toString();
                        signInData = new SignInData(name, phone);
                        Intent intent = new Intent(InsertPersonInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();


//                Toast.makeText(InsertPersonInfoActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
//                String name = editName.getText().toString();
//
//                String phone = editPhone.getText().toString();
//                signInData = new SignInData(name, phone);
//                Intent intent = new Intent(InsertPersonInfoActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }
}
