package top.iqqcode.demo;

/**
 * @Author: iqqcode
 * @Date: 2021-08-14 20:31
 * @Description:格式化JSON
 */
public class JSONFormat {
    private static boolean isDebug = true;

    public static void isDebug(boolean isDebug) {
        JSONFormat.isDebug = isDebug;
    }

    public static String format(String mJson) {
        StringBuilder soruce = new StringBuilder(mJson);
        if (mJson == null || mJson.equals("")) {
            return null;
        }
        int offset = 0; //目标字符串插入空格偏移量
        int bOffset = 0; //空格偏移量
        for (int i = 0; i < mJson.length(); i++) {
            char charAt = mJson.charAt(i);
            if (charAt == '{' || charAt == '[') {
                bOffset += 4;
                soruce.insert(i + offset + 1, "\n" + generateBlank(bOffset));
                offset += (bOffset + 1);
            } else if (charAt == ',') {
                soruce.insert(i + offset + 1, "\n" + generateBlank(bOffset));
                offset += (bOffset + 1);
            } else if (charAt == '}' || charAt == ']') {
                bOffset -= 4;
                soruce.insert(i + offset, "\n" + generateBlank(bOffset));
                offset += (bOffset + 1);
            }
        }
        return soruce.toString();
    }

    /**
     * 打印
     *
     * @param shouldShowAll 是否将全部打印出来
     */
    public static void printJson(String mJson, boolean shouldShowAll) {
        if (!isDebug) return;
        if (mJson == null) return;
        try {
            System.out.println("----------print begin-----------");
            String willPrintString = format(mJson);
            if (!shouldShowAll) {
                System.out.println(willPrintString);
            } else {
                int length = willPrintString.length();
                int index = 0;
                while (length > 2000) {
                    System.out.println(willPrintString.substring(index * 2000, (index + 1) * 2000));
                    length -= 2000;
                    index++;
                }
                System.out.println(willPrintString.substring(index * 2000));
            }
            System.out.println("----------print end-----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String printJson(String mJson) {
        String error = "print error";
        if (!isDebug) return error;
        if (mJson == null) return error;
        try {
            String willPrintString = format(mJson);
            int length = willPrintString.length();
            int index = 0;
            while (length > 2000) {
                System.out.println(willPrintString.substring(index * 2000, (index + 1) * 2000));
                length -= 2000;
                index++;
            }
            return willPrintString.substring(index * 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return error;
    }

    private static String generateBlank(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

}
