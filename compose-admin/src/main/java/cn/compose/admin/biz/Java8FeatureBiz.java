package cn.compose.admin.biz;

import cn.compose.admin.utils.ComposeUtils;
import com.beust.jcommander.internal.Lists;
import com.github.xiaoymin.knife4j.core.util.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class Java8FeatureBiz {


    /**
     * 流式操作
     */
    public void stream(){

        //通过 java.util.Collection.stream() 方法用集合创建流

        List<String> list = Arrays.asList("a", "b", "c","d","s","2","8","3","g","x");
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();


        //java.util.Arrays.stream(T[] array)方法用数组创建流
        int[] array={1,3,5,6,8};
        IntStream arrStream = Arrays.stream(array);


        Stream<Integer> streamOf = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println);

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);


        List<Integer> list1 = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 遍历输出符合条件的元素
        List<Integer> flList = list1.stream().filter(x -> x > 6).collect(Collectors.toList());




    }


    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        List<String> list2 = null;
        List<Integer> list3 = null;


        Integer l = ComposeUtils.minInteger(list1);
        System.err.println(l);
    }



}
