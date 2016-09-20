package com.tacademy.singleplay.pay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.ShowDetailActivity;
import com.tacademy.singleplay.data.SignInData;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.UserInfo;
import com.tacademy.singleplay.detail.UserActivity;
import com.tacademy.singleplay.manager.BookingManager;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.UserInfoRequest;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingPersonInfoActivity extends AppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_move_seat)
    Button btn_move_seat;
    @BindView(R.id.checkBox_sameUser)
    CheckBox sameUser;
    @BindView(R.id.edit_name)
    EditText nameView;
    @BindView(R.id.edit_phone)
    EditText phoneView;
    @BindView(R.id.edit_email)
    EditText emailView;
    static public SignInData signInData;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_person_info);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Button btn;

        phoneView.setInputType(InputType.TYPE_CLASS_NUMBER); // 전화번호 칸에 숫자만 가능
        phoneView.addTextChangedListener(new PhoneNumberFormattingTextWatcher()); // 전화번호 입력에 하이픈(-) 입력하는 방법
        InputFilter[] FilterArray = new InputFilter[1]; // 길이 제한
        FilterArray[0] = new InputFilter.LengthFilter(13);
        phoneView.setFilters(FilterArray);

        emailView.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        //btn = (Button)findViewById(R.id.btn_move_seat);
        btn_move_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = emailView.getText().toString();
                if(phoneView.getText().length() < 10 || checkEmailForm(a) == false){ // 이메일 정규식과 핸드폰 번호 입력 체크
                    Toast.makeText(BookingPersonInfoActivity.this, "알맞은 전화번호 / 이메일 형식을 넣어주세요", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BookingPersonInfoActivity.this);
                    builder.setTitle("가입정보 확인");
                    builder.setMessage("사용자 이름 : " + nameView.getText().toString() + "\n"
                            + "E-Mail : " + emailView.getText().toString() + "\n"
                            + "전화번호 : " + phoneView.getText().toString() + "\n" + "입력 정보가 확실합니까?");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(BookingPersonInfoActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                            BookingManager.getInstance().setBooker(nameView.getText().toString());
                            BookingManager.getInstance().setBookerPhone(phoneView.getText().toString());
                            BookingManager.getInstance().setBookerEmail(emailView.getText().toString());
                            Intent intent = new Intent(BookingPersonInfoActivity.this, BookingSeatInfoActivity.class);
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
                }
//                Intent intent = new Intent(BookingPersonInfoActivity.this, BookingSeatInfoActivity.class);
//                startActivity(intent);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
    }

    @OnClick(R.id.checkBox_sameUser)
    public void onSameUser() {
        if (sameUser.isChecked()) {
            UserInfoRequest request = new UserInfoRequest(MyApplication.getContext());
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultsList<UserInfo>>() {
                @Override
                public void onSuccess(NetworkRequest<ResultsList<UserInfo>> request, ResultsList<UserInfo> result) {
                    Toast.makeText(BookingPersonInfoActivity.this, "성공" + result.getResult().getName(), Toast.LENGTH_SHORT).show();
                    nameView.setText(result.getResult().getName());
                    phoneView.setText(result.getResult().getPhone());
                    emailView.setText(result.getResult().getEmail());
                }

                @Override
                public void onFail(NetworkRequest<ResultsList<UserInfo>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(BookingPersonInfoActivity.this, "실패"+errorCode+errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            nameView.setText("");
            phoneView.setText("");
            emailView.setText("");
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
                switch (item.getItemId()) {
                    case android.R.id.home:
                        intent = new Intent(BookingPersonInfoActivity.this, ShowDetailActivity.class);
                        startActivity(intent);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        finish();
                        break;
                    case R.id.detail_menu:
                        intent = new Intent(BookingPersonInfoActivity.this, UserActivity.class);
                        startActivity(intent);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public boolean checkEmailForm(String src){
        String emailRegex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        return Pattern.matches(emailRegex, src);
    }
}
