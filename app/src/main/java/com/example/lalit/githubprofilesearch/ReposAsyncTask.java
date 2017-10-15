package com.example.lalit.githubprofilesearch;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jainl on 06-10-2017.
 */

public class ReposAsyncTask extends AsyncTask<String,Void,ArrayList<String>> {
    ArrayList<String> repos;
    OnDownloadListener onDownloadListener;
    public ReposAsyncTask(OnDownloadListener listener){
        repos = new ArrayList<>();
        onDownloadListener = listener;
    }
    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        //Profile profile;
        String searchUrl = strings[0];
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
        getRepos(response);
        return repos;
    }

    private void getRepos(String response) {
        try {
            JSONArray rootArray = new JSONArray(response);
            for(int i=0;i<rootArray.length();i++){
                JSONObject item = new JSONObject(String.valueOf(rootArray.getJSONObject(i)));
                String repo = item.getString("name");
                repos.add(repo);
                Log.d("Repo "+i, repo);
            }
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> repos) {
        super.onPostExecute(repos);
        onDownloadListener.onDownload(repos);
    }

    public interface OnDownloadListener{
        void onDownload(ArrayList<String> repos);
    }
}
