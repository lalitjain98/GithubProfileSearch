package com.example.lalit.githubprofilesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReposActivity extends AppCompatActivity {
    ListView userReposListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> reposList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);
        userReposListView = (ListView) findViewById(R.id.userReposView);
        reposList = new ArrayList<>();
        adapter = new ArrayAdapter<>(userReposListView.getContext(),R.layout.repos_row_layout,R.id.repoNameView,reposList);
        userReposListView.setAdapter(adapter);
        Intent intent = getIntent();
        String repos_url = intent.getStringExtra("key_repos_url");
        Log.d("Repos-ReposUrl",repos_url);
        ReposAsyncTask reposAsyncTask = new ReposAsyncTask(new ReposAsyncTask.OnDownloadListener() {
            @Override
            public void onDownload(ArrayList<String> repos) {
                for(int i=0;i<repos.size();i++){
                    reposList.add(repos.get(i));
                }
                adapter.notifyDataSetChanged();
                Log.d("Repos: ", repos.toString());
                Log.d("Repos2: ", reposList.toString());
            }
        });
        reposAsyncTask.execute(repos_url);
    }
}
