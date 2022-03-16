package cn.compose.admin.controller;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class Test {
    public static void main(String[] args) {

        System.err.println(DateUtil.parse(DateUtil.yesterday().toDateStr(), "yyyy-MM-dd"));
        System.err.println(DateUtil.beginOfDay(new Date()));
    }
}
