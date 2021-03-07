package top.iqqcode.transferobject;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/**
 * @Author: iqqcode
 * @Date: 2021-03-07 10:09
 * @Description:实体类
 */
public class User implements Parcelable {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 手动添加序列化内容
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeInt(getAge());
    }


    public static final Creator<User> CREATOR = new Creator<User>() {

        /**
         * 通过Parcel来创建对象
         * @param source
         * @return
         */
        @Override
        public User createFromParcel(Parcel source) {
            // 多个同类型的成员(String)使用Bundle
            return new User(source.readString(), source.readInt());
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
