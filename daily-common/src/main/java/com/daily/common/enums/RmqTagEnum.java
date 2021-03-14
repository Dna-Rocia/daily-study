package com.daily.common.enums;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/17
 */
public enum RmqTagEnum {
    ALL("*"),

    COMMON("common"),

    ORDER("order"),

    PARTITION("part"),

    TRANSACTION("transaction"),


    ;


    private String tag;

    private String describe;

    RmqTagEnum(String tag) {
        this.tag = tag;
    }

    RmqTagEnum(String tag, String describe) {
        this.tag = tag;
        this.describe = describe;
    }


    public String getTag() {
        return tag;
    }


}
