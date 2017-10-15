package com.example.lalit.githubprofilesearch;

/**
 * Created by jainl on 06-10-2017.
 */

public class Profile {
    public int id, followers,following,public_repos;
    public String user_name, name, avatar_url, followers_url, following_url, company, location, email;

    public Profile(int id, int followers, int following, int public_repos, String user_name, String name, String avatar_url, String followers_url, String following_url, String company, String location, String email) {
        this.id = id;
        this.followers = followers;
        this.following = following;
        this.public_repos = public_repos;
        this.user_name = user_name;
        this.name = name;
        this.avatar_url = avatar_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.company = company;
        this.location = location;
        this.email = email;
    }

    public Profile(Profile profile) {
        this.id = profile.id;
        this.followers = profile.followers;
        this.following = profile.following;
        this.public_repos = profile.public_repos;
        this.user_name = profile.user_name;
        this.name = profile.name;
        this.avatar_url = profile.avatar_url;
        this.followers_url = profile.followers_url;
        this.following_url = profile.following_url;
        this.company = profile.company;
        this.location = profile.location;
        this.email = profile.email;
    }
}
