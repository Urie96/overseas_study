package me.urie.overseas_study.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Msg {
    private int code;
    private Map<String, Object> extend = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        return result;

    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        return result;
    }

    public Msg add(String key, Object value) {
        extend.put(key, value);
        return this;
    }
}
