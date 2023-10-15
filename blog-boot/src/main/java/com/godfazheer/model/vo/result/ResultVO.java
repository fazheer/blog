package com.godfazheer.model.vo.result;

import com.godfazheer.emnus.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.godfazheer.emnus.StatusCode.FAIL;
import static com.godfazheer.emnus.StatusCode.SUCCESS;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class ResultVO<T> {

    private Boolean flag;

    private Integer code;

    private String message;

    private T data;

    public static <T> ResultVO<T> ok() {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), null);
    }

    public static <T> ResultVO<T> ok(T data) {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), data);
    }

    public static <T> ResultVO<T> ok(T data, String message) {
        return resultVO(true, SUCCESS.getCode(), message, data);
    }

    public static <T> ResultVO<T> fail() {
        return resultVO(false, FAIL.getCode(), FAIL.getDesc(), null);
    }

    public static <T> ResultVO<T> fail(StatusCode statusCode) {
        return resultVO(false, statusCode.getCode(), statusCode.getDesc(), null);
    }

    public static <T> ResultVO<T> fail(String message) {
        return resultVO(false, message);
    }

    public static <T> ResultVO<T> fail(T data) {
        return resultVO(false, FAIL.getCode(), FAIL.getDesc(), data);
    }

    public static <T> ResultVO<T> fail(T data, String message) {
        return resultVO(false, FAIL.getCode(), message, data);
    }

    public static <T> ResultVO<T> fail(Integer code, String message) {
        return resultVO(false, code, message, null);
    }

    private static <T> ResultVO<T> resultVO(Boolean flag, String message) {
        return ResultVO.<T>builder()
                .flag(flag)
                .code(flag ? SUCCESS.getCode() : FAIL.getCode())
                .message(message).build();
    }

    private static <T> ResultVO<T> resultVO(Boolean flag, Integer code, String message, T data) {
        return ResultVO.<T>builder()
                .flag(flag)
                .code(code)
                .message(message)
                .data(data).build();
    }

}
