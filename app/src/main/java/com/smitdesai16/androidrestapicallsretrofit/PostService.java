package com.smitdesai16.androidrestapicallsretrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostService {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @POST("posts")
    Call<Post> createPost(Post post);

    @PUT("posts/{id}")
    Call<Post> updatePost(Post post, @Path("id") int id);

    @DELETE("posts/{id}")
    Call<ResponseBody> deletePost(@Path("id") int id);
}
