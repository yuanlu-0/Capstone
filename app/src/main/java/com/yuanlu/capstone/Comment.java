package com.yuanlu.capstone;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Comment implements Parcelable {

    private String mBody;

    protected Comment(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("body")) {
            mBody = jsonObject.getString("body");
        }
    }

    protected Comment(Parcel in) {
        mBody = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBody);
    }

    public String getBody() {
        return mBody;
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
