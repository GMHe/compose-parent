package cn.compose.admin.utils;

import com.beust.jcommander.internal.Lists;
import org.springframework.util.CollectionUtils;

import java.util.*;


public class ComposeUtils {

    /**
     * 获取当前系统时区
     */
    public static String getDefaultTimeZone() {
        return TimeZone.getDefault().getID();
    }


    /**
     * 数组转List
     *
     * @param arr
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T[] arr) {
        return Arrays.asList(arr);
    }

    /**
     * list中是否包含某个元素，顺序流式处理
     *
     * @param list
     * @param o
     * @param <T>
     * @return
     */
    public static <T> Boolean streamMatch(List<T> list, T o) {
        return list.stream().anyMatch(x -> x.equals(o));
    }

    /**
     * list中是否包含某个元素，并行流式处理
     *
     * @param list
     * @param o
     * @param <T>
     * @return
     */
    public static <T> Boolean parallelStreamMatch(List<T> list, T o) {
        return list.parallelStream().anyMatch(x -> x.equals(o));
    }

    /**
     * Optional判空，不为空返回值，为空new新值
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> notNullList(List<T> list) {
        return Optional.ofNullable(list).orElse(Lists.newArrayList());
    }

    /**
     * list/map判空
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Boolean isNullList(Collection<T> list) {
        return CollectionUtils.isEmpty(list);
    }

    /**
     * 返回List字符串集合长度最长的数据
     *
     * @param list
     * @return
     */
    public static String maxLenStr(List<String> list) {
        return isNullList(list) ? null : list.stream().max(Comparator.comparing(String::length)).get();
    }

    /**
     * 返回List字符串集合长度最小的数据
     *
     * @param list
     * @return
     */
    public static String minLenStr(List<String> list) {
        return isNullList(list) ? null : list.stream().max(Comparator.comparing(String::length)).get();
    }


    /**
     * 返回List<Integer>中最大值
     *
     * @param list
     * @return
     */
    public static Integer maxInteger(List<Integer> list) {
        return isNullList(list) ? null : list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }).get();
    }


    /**
     * 返回List<Integer>中最小值
     *
     * @param list
     * @return
     */
    public static Integer minInteger(List<Integer> list) {
        return isNullList(list) ? null : list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        }).get();
    }
    




}
