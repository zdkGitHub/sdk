package com.cesgroup.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StreamTest
 * @Description: TODO
 * @Author zk
 * @Date 2020/4/10
 **/
public class StreamTest {
    /*
    * stream() − 为集合创建串行流
    * */
    @Test
    public void testStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    /*
    * parallelStream() − 为集合创建并行流
    * */
    @Test
    public void testParallelStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.parallelStream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        filtered.forEach(System.out::println);
    }

    @Test
    public void testCreateStream1() {
        String[] strs = "a,c,f,d,g,e,b,w,q".split(",");
        long count = Stream.of(strs).count();
        System.out.println(count);
    }

    @Test
    public void testCreateStream2() {
        String[] strs = "a,c,f,d,g,e,b,w,q".split(",");
        long count = Arrays.stream(strs).count();
        System.out.println(count);
    }

    @Test
    public void testForEach(){
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    @Test
    public void testMap() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }

    @Test
    public void testFilter() {
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println(count);
    }

    @Test
    public void testLimit() {
        Random random = new Random();
        random.doubles(1.0d,2.0).limit(5).forEach(System.out::println);
    }

    @Test
    public void testSorted() {
        Random random = new Random();
        random.ints(1,10).limit(7).sorted().forEach(System.out::println);
    }

    @Test
    public void testCollectors() {
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    @Test
    public void testStatistic() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
