package com.example.to_do_list;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;


public class ExampleItem implements Parcelable {
    private String mImageResource;
    private String mLine1;
    private String mLine2;



    public ExampleItem(String up_image, String line1, String line2) {
        mImageResource = up_image;
        mLine1 = line1;
        mLine2 = line2;
    }

    protected ExampleItem(Parcel in) {
        mImageResource = in.readString();
        mLine1 = in.readString();
        mLine2 = in.readString();
    }

    public static final Parcelable.Creator<ExampleItem> CREATOR = new Creator<ExampleItem>() {
        @Override
        public ExampleItem createFromParcel(Parcel in) {
            return new ExampleItem(in);
        }

        @Override
        public ExampleItem[] newArray(int size) {
            return new ExampleItem[size];
        }
    };



    public String getImageResource() {
        return mImageResource;
    }


    public String getLine1() {
        return mLine1;
    }

    public String getLine2() {
        return mLine2;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImageResource);
        dest.writeString(mLine1);
        dest.writeString(mLine2);
    }


}