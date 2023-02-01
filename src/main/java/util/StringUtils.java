package util;

/**
 * @author hq7733
 * @version 1.0
 * @date 2022/9/23 9:42 AM
 */
public class StringUtils {

    public static boolean isBlank(String value) {
        int strLen = length(value);
        if (strLen == 0) {
            return true;
        } else {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}
