package com.cloudNative._4.FinalProject.VO;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResultVO<T> implements Serializable{
    private String code;

    private String msg;

    private T result;

    public static <T> ResultVO<T> buildSuccess(T result) {
        return new ResultVO<T>("000", null, result);
    }

    public static <T> ResultVO<T> buildFailure(String msg,String code) {
        return new ResultVO<T>(code, msg, null);
    }
}