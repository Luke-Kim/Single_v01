package com.tacademy.singleplay.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tacademy.singleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.btn_ok)
    Button btn_ok;
    @BindView(R.id.radio_group)
    RadioGroup rg;
    @BindView(R.id.btn_no_acpt)
    RadioButton btn_reject;
    @BindView(R.id.acpt)
    RadioButton btn_acpt;
    @BindView(R.id.signin_text)
    TextView signin_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        signin_text.setMovementMethod(new ScrollingMovementMethod());

        //Button btn = (Button)findViewById(R.id.btn_ok);

        btn_ok.setEnabled(false);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int isChecked) {
                switch (isChecked){
                    case R.id.btn_no_acpt :
                        btn_ok.setEnabled(false);
                        break;
                    case R.id.acpt :
                        btn_ok.setEnabled(true);
                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(SignInActivity.this, InsertPersonInfoActivity.class);
                                startActivity(intent);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                finish();
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });


    }
}
