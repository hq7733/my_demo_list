package util;

/**
 * @author hq7733
 * @version 1.0
 * @date 2022/6/18 1:52 PM
 */
public class Base64Utils {
    private static String commonsBase64(String msg) {
        // test
        byte[] encode = org.apache.commons.codec.binary.Base64.encodeBase64(msg.getBytes());
        return new String(encode);
    }
}
