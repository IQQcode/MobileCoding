package top.iqqcode.a03baseadapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: iqqcode
 * @Date: 2021-04-30 22:01
 * @Description:
 */
public class AppInfo implements Parcelable {

    private String appName;
    private String appSize;
    private int appIcon;

    public AppInfo() { }

    public AppInfo(String appName, String appSize, int appIcon) {
        this.appName = appName;
        this.appSize = appSize;
        this.appIcon = appIcon;
    }

    protected AppInfo(Parcel in) {
        appName = in.readString();
        appSize = in.readString();
        appIcon = in.readInt();
    }

    public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
        @Override
        public AppInfo createFromParcel(Parcel parcel) {
            return new AppInfo(parcel);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public int getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(int appIcon) {
        this.appIcon = appIcon;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "appName='" + appName + '\'' +
                ", appSize='" + appSize + '\'' +
                ", appIcon=" + appIcon +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appName);
        dest.writeString(appSize);
        dest.writeInt(appIcon);
    }
}
