package com.example.lalit.githubprofilesearch;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jainl on 06-10-2017.
 */

public class ProfileAsyncTask extends AsyncTask<String, Void, Profile>{
    OnDownloadListener onDownloadListener;
    public ProfileAsyncTask(OnDownloadListener listener){
        onDownloadListener = listener;
    }
    @Override
    protected Profile doInBackground(String... strings) {
        //Profile profile;
        String searchUserName = strings[0];
        String searchUrl = "https://api.github.com/users/" + searchUserName;
        String response = "";
        Log.d("URL",searchUrl);
        try {
            URL url = new URL(searchUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner scan = new Scanner(inputStream);
            while(scan.hasNext()){
                response += scan.next();
            }
            Log.d("Response",response);
            //profile = getProfile(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getProfile(response);
    }

    private Profile getProfile(String response) {
        try {
            Profile profile;
            JSONObject user = new JSONObject(response);
            int id = user.getInt("id"),
                    followers = user.getInt("followers"),
                    following = user.getInt("following"),
                    public_repos = user.getInt("public_repos");
            String user_name = user.getString("login"),
                    name = user.getString("name"),
                    avatar_url = user.getString("avatar_url"),
                    followers_url = user.getString("followers_url"),
                    following_url = user.getString("following_url"),
                    company = user.getString("company"),
                    location = user.getString("location"),
                    email = user.getString("email");
            profile = new Profile( id, followers, following, public_repos, user_name, name, avatar_url, followers_url, following_url, company, location, email);
            Log.d("Profile Info",profile.toString());
            return profile;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Profile profile) {
        onDownloadListener.onDownload(profile);
        super.onPostExecute(profile);
    }

    public interface OnDownloadListener{
        void onDownload(Profile profile);
    }
}
