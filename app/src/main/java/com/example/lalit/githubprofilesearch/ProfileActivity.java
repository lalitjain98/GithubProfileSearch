package com.example.lalit.githubprofilesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {
    Profile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String searchUserName = intent.getStringExtra("key_profile_username");
        ProfileAsyncTask profileAsyncTask = new ProfileAsyncTask(new ProfileAsyncTask.OnDownloadListener() {
            @Override
            public void onDownload(Profile profile) {
                userProfile = new Profile(profile);
            }
        });
        profileAsyncTask.execute(searchUserName);
    }
}
