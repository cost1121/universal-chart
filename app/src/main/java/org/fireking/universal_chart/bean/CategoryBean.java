package org.fireking.universal_chart.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangg on 2016/1/8.
 */
public class CategoryBean implements Parcelable {

    private String id;
    private String cate;
    private String name;
    private String name_zh;

    public CategoryBean(String id, String cate, String name, String name_zh) {
        this.id = id;
        this.cate = cate;
        this.name = name;
        this.name_zh = name_zh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_zh() {
        return name_zh;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.cate);
        dest.writeString(this.name);
        dest.writeString(this.name_zh);
    }

    public CategoryBean() {
    }

    protected CategoryBean(Parcel in) {
        this.id = in.readString();
        this.cate = in.readString();
        this.name = in.readString();
        this.name_zh = in.readString();
    }

    public static final Parcelable.Creator<CategoryBean> CREATOR = new Parcelable.Creator<CategoryBean>() {
        public CategoryBean createFromParcel(Parcel source) {
            return new CategoryBean(source);
        }

        public CategoryBean[] newArray(int size) {
            return new CategoryBean[size];
        }
    };
}
