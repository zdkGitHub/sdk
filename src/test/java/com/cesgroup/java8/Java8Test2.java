package com.cesgroup.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @ClassName Java8Test2
 * @Description: TODO
 * @Author zk
 * @Date 2020/4/9
 **/
public class Java8Test2 {
    @Test
    public void testCollect(){
        List<String> collected = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("a", "b", "c"), collected);
    }

    @Test
    public void testMap(){
        List<String> collected = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }
        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void testMap2(){
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void testFilter() {
        long count = Stream.of("a", "b", "hello")
                .filter(item -> item.equals("a"))
                .count();
        System.out.println(count);
    }

    @Test
    public void testFlatMap() {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 3, 4), together);
    }

    @Test
    public void testMin() {
        List<Integer> nums = Arrays.asList(524, 378, 451);
        Integer shortestNum = nums.stream()
                .min(Comparator.comparing(item -> item))
                .get();
        assertEquals(nums.get(2), shortestNum);
    }

    @Test
    public void testReduce() {
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        assertEquals(6, count);
    }

    @Test
    public void testOptional() {
        Optional<String> a = Optional.of("a");
        assertEquals("a", a.get());
    }

    @Test
    public void testOptional2() {
        Optional emptyOptional = Optional.empty();
        //ofNullable 则可将一个空值转换成 Optional 对象
        Optional alsoEmpty = Optional.ofNullable(null);
        //Optional 对象里是否有值
        assertFalse(emptyOptional.isPresent());
        assertEquals("b", emptyOptional.orElse("b"));
        assertEquals("c", emptyOptional.orElseGet(() -> "c"));
    }


    @Test
    public void testSort() {
        String str = "c,a,t,u,i,b,d,h";
        List<String> strList = Arrays.asList(str.split(","));
        System.out.println(strList);
        strList.sort((a,b) -> a.compareTo(b));
        System.out.println(strList);
    }

    @Test
    public void testFor() {
        List<String> strList = Arrays.asList("c,a,t,u,i,b,d,h".split(","));
        strList.forEach(str -> System.out.println(str));
    }
}
