package com.mobis.review;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobis.review.R;
import com.mobis.review.RetroFit.ApiInterface;
import com.mobis.review.RetroFit.RetrofitClient;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();


    private EditText mSearchTextBar;

    private Context mContext;
//    private RecyclerView mRecyclerView;


    TextView ttttt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreateonCreateonCreateonCreateonCreate");
//        presenter = new MainPresenter(this);
        mContext = this;


        ttttt = findViewById(R.id.ttttt);

        ttttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClickonClickonClickonClickonClick");

                String uri = "http://172.30.1.28:8080/";
                Retrofit retrofit = RetrofitClient.getClient(uri);
                ApiInterface retrofitExService = retrofit.create(ApiInterface.class);
                retrofitExService.getReview().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG,"onResponseonResponse : ");
                        ResponseBody a = response.body();
                        if (response.isSuccessful()){
                            try {
                                ttttt.setText(a.string());
                                Log.d(TAG,"isSuccessful : " + a.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Log.d(TAG,"elseelseelse : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG,"onFailureonFailureonFailure : ");
                    }
                });

            }
        });


//        init();
//        textWatcher();
//        addSearchList();
    }
//
//
//    private void init() {
//        mSearchTextBar = findViewById(R.id.searchTextBar);
//        mRecyclerView = findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setHasFixedSize(true);
//    }
//
//    private void textWatcher() {
//        TextWatcher tw = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                presenter.loadData(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        };
//        mSearchTextBar.addTextChangedListener(tw);
//    }
//
//    private void addSearchList() {
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
//                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
//                if (lastVisibleItemPosition == itemTotalCount) {
//                    presenter.loadAddData(mSearchTextBar.getText().toString());
//                }
//            }
//        });
//    }
//
//    @Override
//    public void search(List<documents> list) {
//        mAdapter = new RecyclerAdapter(mContext);
//        mAdapter.setData(list);
//        mRecyclerView.setAdapter(mAdapter);
//    }
//
//    @Override
//    public void addList(List<documents> list) {
//        if (mAdapter != null) {
//            mAdapter.setData(list);
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void errorToast(String text) {
//        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
//    }


}
