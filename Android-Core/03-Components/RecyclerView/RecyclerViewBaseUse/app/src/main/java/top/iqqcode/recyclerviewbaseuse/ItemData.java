package top.iqqcode.recyclerviewbaseuse;

/**
 * @Author: iqqcode
 * @Date: 2022-03-05 16:09
 * @Description:实体类
 */
public class ItemData {
    private String name;
    protected int imageId;

    public ItemData() { }

    public ItemData(String name, int imageId) {
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
