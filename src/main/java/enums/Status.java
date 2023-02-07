package enums;

import java.util.Objects;

/**
 * @author Zhang.hq
 * @date 2023/2/6
 */
public enum Status {
    SUCCESS(200, "请求成功"),
    FAIL(500, "服务器异常"),
    ;

    private Integer code;
    private String message;

    private Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     *
     * 方法描述 根据类型的名称，返回类型的枚举实例。
     *
     */
    public static Status findByCode(Integer code) {
        for (Status type : Status.values()) {
            if (Objects.equals(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Status{" + "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
