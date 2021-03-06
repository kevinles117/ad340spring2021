package com.example.helloworld.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class MatchesModel implements Parcelable {
    public String uid;
    public String name;
    public String imageUrl;
    public int latitude;
    public int longitude;
    public boolean liked;

    public MatchesModel(){
    }

    public MatchesModel(String name, String imageUrl, int latitude, int longitude, boolean liked) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.liked = liked;
    }

    public MatchesModel(Parcel in) {
        liked = in.readByte() != 0;
    }

    public static final Creator<MatchesModel> CREATOR = new Creator<MatchesModel>() {
        @Override
        public MatchesModel createFromParcel(Parcel in) {
            return new MatchesModel(in);
        }

        @Override
        public MatchesModel[] newArray(int size) {
            return new MatchesModel[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("imageUrl", imageUrl);
        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("liked", liked);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeInt(latitude);
        dest.writeInt(longitude);
        dest.writeByte((byte) (liked ? 1 : 0));
    }
}
