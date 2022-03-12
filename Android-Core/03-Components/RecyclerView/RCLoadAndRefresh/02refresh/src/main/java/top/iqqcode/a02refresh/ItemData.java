package top.iqqcode.a02refresh;

/**
 * @Author: iqqcode
 * @Date: 2022-03-12 17:27
 * @Description:
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
