public class DemoTest {
    public static void main(String[] args) {

        System.out.println(find());
    }

    public int find() {
        int[] arr = new int[]{1, 3, 4, 3, 3, 2, 4, 4};
        int val = 4;
        int lef = 0;
        int rig = arr.length - 1;
        while (lef <= rig) {
            int mid = (lef + rig) / 2;
            if (arr[mid] < val) {
                lef = mid + 1;
            } else if (arr[mid] > val) {
                rig = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
