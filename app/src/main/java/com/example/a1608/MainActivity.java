package com.example.a1608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UserAdapter.ClickedItem {
Toolbar toolbar;
RecyclerView recyclerView;
UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        userAdapter = new UserAdapter(this::ClickerUser);
        getAllUsers();
    }
    public void getAllUsers(){
        Call<List<UserResponse>> userlist = ApiClient.getUserService().getAllUsers();
        userlist.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if (response.isSuccessful()){
                    List<UserResponse> userResponses = response.body();
                    userAdapter.setData(userResponses);
                    recyclerView.setAdapter(userAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void ClickerUser(UserResponse userResponse) {
        startActivity(new Intent(this, UserDetailActivity.class).putExtra("data", userResponse));
    }
}