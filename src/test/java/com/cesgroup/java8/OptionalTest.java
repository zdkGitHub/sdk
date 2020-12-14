package com.cesgroup.java8;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @ClassName OptionalTest
 * @Description: TODO
 * @Author zk
 * @Date 2020/4/13
 **/
public class OptionalTest {


    @Test
    public void testOptional1 () {
        Optional opt1 = Optional.empty();
        boolean isPresent = opt1.isPresent();
        System.out.println(isPresent);
    }

    @Test
    public void testOptional2 () {
        Optional<String> opt1 = Optional.empty();
        String res = opt1.orElse("hi");
        System.out.println(res);
    }

    @Test
    public void testOptional3 () {
        String str = "hello";
        Integer length = Optional.ofNullable(str).map(item -> item.length()).orElseThrow(NoSuchElementException::new);
        System.out.println(length);
    }

    @Test
    public void testOptional4 () {
        String str = "hello";
        Integer length = Optional.ofNullable(str).map(item -> item.length()).orElse(0);
        System.out.println(length);
    }

    @Test
    public void testOptional5 () {
        String str = "hello";
        String res = Optional.ofNullable(str).map(item -> item.toUpperCase()).orElseGet(String::new);
        System.out.println(res);
    }

    @Test
    public void testOptional6 () {
        String str = null;
        String res = Optional.ofNullable(str).map(item -> item.toUpperCase()).orElseGet(String::new);
        System.out.println(res);
    }

    @Test
    public void testOptional7 () {
        //String str = "hello";
        String str = null;
        Integer length = Optional.ofNullable(str).map(item -> item.length()).orElseGet(() -> 0);
        System.out.println(length);
    }
}
