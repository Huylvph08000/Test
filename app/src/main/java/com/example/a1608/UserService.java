package com.example.a1608;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users/")
    Call<List<UserResponse>> getAllUsers();

}
