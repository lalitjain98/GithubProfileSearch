package com.example.lalit.githubprofilesearch;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    Profile userProfile;
    ImageView userAvatarView, userAvatarToolbarView;
    TextView userNameView,userUserNameView,userCompanyView,userLocationView,userEmailView,userReposCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        userUserNameView = (TextView) findViewById(R.id.userUserNameView);
        userNameView = (TextView) findViewById(R.id.userNameView);
        userCompanyView = (TextView) findViewById(R.id.userCompamyView);
        userLocationView = (TextView) findViewById(R.id.userLocationView);
        userEmailView = (TextView) findViewById(R.id.userEmailView);
        userAvatarView = (ImageView) findViewById(R.id.userAvatarView);
        userAvatarToolbarView = (ImageView) findViewById(R.id.userAvatarToolbarView);
        userReposCountView = (TextView) findViewById(R.id.userReposCountView);
        Intent intent = getIntent();
        String searchUserName = intent.getStringExtra("key_profile_username");
        Log.d("user name", searchUserName);
        ProfileAsyncTask profileAsyncTask = new ProfileAsyncTask(new ProfileAsyncTask.OnDownloadListener() {
            @Override
            public void onDownload(Profile profile) {
            if(profile != null){
                final String repos_url = "https://api.github.com/users/"+profile.user_name+"/repos";
                userProfile = new Profile(profile);
                String profile_url = userProfile.avatar_url;
                String user_name = userProfile.name;
                userNameView.setText("Name: "+user_name);
                userUserNameView.setText("User Name: "+profile.user_name);
                userCompanyView.setText("Company: "+profile.company);
                userLocationView.setText("Location: "+profile.location);
                userEmailView.setText("E-mail: "+profile.email);
                userReposCountView.setText("Public Repos: "+ profile.public_repos);
                userReposCountView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(ScrollingActivity.this,ReposActivity.class);
                        intent1.putExtra("key_repos_url",repos_url);
                        startActivity(intent1);
                    }
                });
                Picasso.with(getApplicationContext()).load(profile_url).into(userAvatarToolbarView);
                //Picasso.with(getApplicationContext()).load(profile_url).into(userAvatarView);
                Log.d("Repos URL",repos_url);
            }
            }
        });
        profileAsyncTask.execute(searchUserName);
        //String profile_url = userProfile.avatar_url;
        //Picasso.with(getApplicationContext()).load(profile_url).into((Target) collapsingToolbarLayout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
//app:contentScrim="?attr/colorPrimary"
//"@dimen/app_bar_height"