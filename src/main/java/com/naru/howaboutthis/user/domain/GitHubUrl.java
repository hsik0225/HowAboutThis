package com.naru.howaboutthis.user.domain;

public enum GitHubUrl {
    CODE("https://github.com/login/oauth/authorize"),
    USER_API("https://api.github.com/user"),
    ACCESS_TOKEN("https://github.com/login/oauth/access_token");

    String url;

    private GitHubUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
