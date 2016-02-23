package com.yuanlu.capstone;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Issue implements Parcelable {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());

    private String mTitle;
    private String mBody;
    private String mUserName;
    private String mCommentsUrl;
    private Date mDateUpdated;

    protected Issue(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("title")) {
            mTitle = jsonObject.getString("title");
        }
        if (!jsonObject.isNull("body")) {
            mBody = jsonObject.getString("body");
        }
        if (!jsonObject.isNull("comments_url")) {
            mCommentsUrl = jsonObject.getString("comments_url");
        }
        if (!jsonObject.isNull("user")) {
            JSONObject userJsonObject = jsonObject.getJSONObject("user");
            if (!userJsonObject.isNull("login")) {
                mUserName = userJsonObject.getString("login");
            }
        }
        if (!jsonObject.isNull("updated_at")) {
            try {
                mDateUpdated = DATE_FORMAT.parse(jsonObject.getString("updated_at"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    protected Issue(Parcel in) {
        mTitle = in.readString();
        mBody = in.readString();
        mCommentsUrl = in.readString();
        mUserName = in.readString();
        mDateUpdated = (Date) in.readValue(Date.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mBody);
        dest.writeString(mCommentsUrl);
        dest.writeString(mUserName);
        dest.writeValue(mDateUpdated);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }

    public String getCommentsUrl() {
        return mCommentsUrl;
    }

    public String getUserName() {
        return mUserName;
    }

    public Date getLastUpdated() {
        return mDateUpdated;
    }

    public static final Creator<Issue> CREATOR = new Creator<Issue>() {
        @Override
        public Issue createFromParcel(Parcel in) {
            return new Issue(in);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };
}
