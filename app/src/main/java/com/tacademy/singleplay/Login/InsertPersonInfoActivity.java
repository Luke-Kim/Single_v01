//package com.tacademy.singleplay.login;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.telephony.PhoneNumberFormattingTextWatcher;
//import android.text.InputFilter;
//import android.text.InputType;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.tacademy.singleplay.MainActivity;
//import com.tacademy.singleplay.R;
//import com.tacademy.singleplay.data.SignInData;
//
//import java.util.regex.Pattern;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class InsertPersonInfoActivity extends AppCompatActivity {
//    @BindView(R.id.btn_send_info)
//    Button btn_send_info;
//    @BindView(R.id.edit_email)
//    EditText editEmail;
//    @BindView(R.id.edit_name)
//    EditText editName;
//    @BindView(R.id.edit_phone)
//    EditText editPhone;
//
//    static public SignInData signInData;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_insert_person_info);
//        ButterKnife.bind(this);
//
//        ///// 입력 값에 대한 조건달기
//
//        editPhone.setInputType(InputType.TYPE_CLASS_NUMBER); // 전화번호 칸에 숫자만 가능
//        editPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher()); // 전화번호 입력에 하이픈(-) 입력하는 방법
//        InputFilter[] FilterArray = new InputFilter[1];
//        FilterArray[0] = new InputFilter.LengthFilter(11);
//        editPhone.setFilters(FilterArray);
//
//        editEmail.setInputType(InputType.TYPE_CLASS_TEXT
//                | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
//
//        // 이메일 정규식 체크
//        editPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
//
//
//        btn_send_info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String a = editEmail.getText().toString();
//                if(checkEmailForm(a) != false){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(InsertPersonInfoActivity.this);
//                    builder.setTitle("가입정보 확인");
//                    builder.setMessage("사용자 이름 : " + editName.getText().toString() + "\n"
//                            + "E-Mail : " + editEmail.getText().toString() + "\n"
//                            + "전화번호 : " + editPhone.getText().toString() + "\n" + "입력 정보가 확실합니까?");
//                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(InsertPersonInfoActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
//                            String name = editName.getText().toString();
//
//                            String phone = editPhone.getText().toString();
//                            signInData = new SignInData(name, phone);
//                            Intent intent = new Intent(InsertPersonInfoActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    });
//                    builder.show();
//                } else {
//                    Toast.makeText(InsertPersonInfoActivity.this, "알맞은 이메일 형식을 넣어주세요", Toast.LENGTH_SHORT).show();
//                }
//
//
//
////                Toast.makeText(InsertPersonInfoActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
////                String name = editName.getText().toString();
////
////                String phone = editPhone.getText().toString();
////                signInData = new SignInData(name, phone);
////                Intent intent = new Intent(InsertPersonInfoActivity.this, MainActivity.class);
////                startActivity(intent);
////                finish();
//            }
//        });
//    }
//
//    public boolean checkEmailForm(String src){
//        String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
//        return Pattern.matches(emailRegex, src);
//    }
//
//
//}
