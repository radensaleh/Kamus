package com.example.rdsaleh.kamus.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class KamusModel implements Parcelable {
    private int id;
    private String word;
    private String translate;

    public KamusModel(){ }

    public KamusModel(String word, String translate){
        this.word      = word;
        this.translate = translate;
    }

    public KamusModel(int id, String word, String translate){
        this.id        = id;
        this.word      = word;
        this.translate = translate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getWord() { return word; }

    public void setWord(String word) { this.word = word; }

    public String getTranslate() { return translate; }

    public void setTranslate(String translate) { this.translate = translate; }

    protected KamusModel(Parcel in) {
        this.id        = in.readInt();
        this.word      = in.readString();
        this.translate = in.readString();
    }

    public static final Creator<KamusModel> CREATOR = new Creator<KamusModel>() {
        @Override
        public KamusModel createFromParcel(Parcel in) {
            return new KamusModel(in);
        }

        @Override
        public KamusModel[] newArray(int size) {
            return new KamusModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.translate);
    }
}
