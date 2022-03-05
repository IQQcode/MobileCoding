package top.iqqcode.listview01;

/**
 * @Author: iqqcode
 * @Date: 2021-04-20 15:30
 * @Description:实体类
 */
public class Fruit {
    private String name;
    protected int imageId;

    public Fruit() {
    }

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }


}
