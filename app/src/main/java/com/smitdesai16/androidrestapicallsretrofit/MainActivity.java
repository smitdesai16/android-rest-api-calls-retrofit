package com.smitdesai16.androidrestapicallsretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvPosts;
    private final Context context;

    public MainActivity() {
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPosts = findViewById(R.id.lvPosts);

        PostService postService = ServiceBuilder.buildService(PostService.class);
        Call<List<Post>> getPostsCall = postService.getPosts();
        getPostsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.code() == HttpURLConnection.HTTP_OK) {
                    String[] posts = new String[response.body().size()];
                    for (int i = 0; i < response.body().size(); i++) {
                        posts[i] = response.body().get(i).title;
                    }
                    ListAdapter listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, posts);
                    lvPosts.setAdapter(listAdapter);
                } else {
                    String[] posts = { "Error occurred" };
                    ListAdapter listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, posts);
                    lvPosts.setAdapter(listAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                String[] posts = { "Error occurred" };
                ListAdapter listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, posts);
                lvPosts.setAdapter(listAdapter);
            }
        });
        lvPosts.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}