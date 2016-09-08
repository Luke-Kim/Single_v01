package com.tacademy.singleplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.Search;
import com.tacademy.singleplay.manager.NetworkManager;
import com.tacademy.singleplay.manager.NetworkRequest;
import com.tacademy.singleplay.request.SearchRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.edit_title)
    EditText titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);


    }
    @OnClick(R.id.btn_search)
    public void onSearch() {
        final String title = titleView.getText().toString();
        if (!TextUtils.isEmpty(title)) {
            Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
            intent.putExtra(SearchResultActivity.TITLE, title);
            startActivity(intent);
        }
        else {
            Toast.makeText(SearchActivity.this, "제목을 입력해 주세요", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_search_cancel)
    public void onSearchCancel() {
        finish();
    }
}
