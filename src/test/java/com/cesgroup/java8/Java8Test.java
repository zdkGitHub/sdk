/**
 * <p>Copyright:Copyright(c) 2018</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.test</p>
 * <p>文件名:Test.java</p>
 * <p>创建时间:2018-09-15 12:35</p>
 * <p>作者:huz</p>
 */

package com.cesgroup.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.cesgroup.java8.inter.EnhanceInterface;
import com.cesgroup.java8.inter.EnhanceInterfaceImpl;
import com.cesgroup.java8.lambda.Lambda;

/**
 *
 * @author huz
 * @version 1.0.0 2018-09-15
 */
public class Java8Test {

    @Test
    public void testInterface() {
        EnhanceInterface inter = new EnhanceInterfaceImpl();
        //静态常量
        System.out.println(EnhanceInterface.str);
        //普通方法
        inter.execute("普通方法");
        //静态方法
        EnhanceInterface.staticExecute();
        //默认方法
        inter.defaultExecute();
    }

    @Test
    public void testLambda() {
        String str = "c,a,t,u,i,b,d,h";
        Lambda lambda = new Lambda();
        //Lambda排序
        List<String> strList1 = Arrays.asList(str.split(","));
        lambda.lambdaSort(strList1);
        //内部类排序
        List<String> strList2 = Arrays.asList(str.split(","));
        lambda.innerClassSort(strList2);
        System.out.println(strList1);
        Assert.assertArrayEquals(strList1.toArray(), strList2.toArray());
    }

    @Test
    public void testConsumer() {
        List<String> strList = Arrays.asList("c,a,t,u,i,b,d,h".split(","));
        strList.forEach(s -> System.out.println(s));
        strList.forEach(System.out::print);
    }

    @Test
    public void testFunction() {
        Map<String, String> map = new HashMap<>();
        //如果不存在则添加
        //map.computeIfAbsent("str", k -> k + "1");
        map.computeIfAbsent("str", k -> {
            return k + "2";
        });
        Assert.assertEquals(map.get("str"), "str1");
    }

    @Test
    public void testPredicate() {
        List<String> strList = new ArrayList<>(Arrays.asList("c,a,t,u,i,b,d,h".split(",")));
        //存在则删除
        strList.removeIf(s -> "c".equals(s));
        Assert.assertEquals(strList.size(), 7);
    }

    @Test
    public void testSupplier() {
        Integer num = Integer.valueOf(10);
        ThreadLocal<String> local = ThreadLocal.withInitial(() -> String.valueOf(num));
        Assert.assertEquals(local.get(), "10");
    }

    @Test
    public void testArg() {
        //将字符串转为大写
        Function<String, String> upper = s -> s.toUpperCase();

        String abc = upper.apply("abc");

        List<String> strList = new ArrayList<>(Arrays.asList("c,a,t,u,i,b,d,h".split(",")));
        //此处使用Stream进行遍历，将集合内字符串转为大写
        List<String> upperList = strList.stream().map(upper).collect(Collectors.toList());

        Map<String, String> map = new HashMap<>();
        //如果不存在则添加
        map.computeIfAbsent("str", upper);
    }

    @Test
    public void testNew() {
        ThreadLocal<String> local = ThreadLocal.withInitial(String::new);
        //相当于使用Supplier
        local = ThreadLocal.withInitial(() -> new String());
    }

    @Test
    public void testObjectMethod() {
        List<String> strList = Arrays.asList("c,a,t,u,i,b,d,h".split(","));
        strList.forEach(System.out::println);
    }

    @Test
    public void testStatic() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        //此处使用Stream进行遍历，将集合内数值转换为字符串
        List<String> strList = intList.stream().map(String::valueOf).collect(Collectors.toList());
        //相当于Function
        strList = intList.stream().map(i -> String.valueOf(i)).collect(Collectors.toList());
        //也可以是Consumer
        intList.stream().forEach(Print::print);
    }

    static class Print {

        public static void print(Integer i) {
            System.out.println(i);
        }

    }

    @Test
    public void testClassMethod() {
        List<String> strList = new ArrayList<>(Arrays.asList("c,a,t,u,i,b,d,h".split(",")));
        strList.forEach(String::toUpperCase);
        //相当于
        strList.forEach(s -> s.toLowerCase());

        Comparator<String> comparator = String::compareTo;
        //相当于
        comparator = (s1, s2) -> s1.compareTo(s2);
    }

    @Test
    public void testStreamCreate() {
        String[] strs = "c,a,t,u,i,b,d,h".split(",");
        List<String> strList = new ArrayList<>(Arrays.asList(strs));
        //从集合创建
        Stream<String> stream1 = strList.stream();
        Stream<String> stream2 = strList.parallelStream();
        //从数组创建
        Stream<String> stream3 = Stream.of(strs);
        Stream<String> stream4 = Arrays.stream(strs);
        //从对象创建，从初始化对象一个一个创建后续对象，创建100个对象
        Stream<String> stream5 = Stream.iterate("0", s -> String.valueOf(Integer.parseInt(s) + 1)).limit(100);
    }

    @Test
    public void testStreamMap() {
        String[] arrs = "apple,boy,cat,dog,egg,foot,greate,hill".split(",");
        //所有字符转大写
        Stream<String> upperStream = Stream.of(arrs).map(String::toUpperCase);
        //将所有字符串的字母拆分为字符集合
        Stream<Character> charStream = Stream.of(arrs).flatMap(s -> {
            //char[]数组无法直接转换为Stream
            //Stream.of(s.toCharArray())
            List<Character> result = new ArrayList<>();
            for (char c : s.toCharArray()) {
                result.add(c);
            }
            return result.stream();
        });
        //将字符串进行两两拼接，相当于笛卡尔积
        Stream<String> concatStream = Stream.of(arrs).flatMap(s -> Stream.of(arrs).map(ss -> s + " " + ss));

        //打印结果
        upperStream.forEach(System.out::println);
        charStream.forEach(System.out::println);
        concatStream.forEach(System.out::println);
    }

    @Test
    public void testStreamOperate() {
        String[] arrs = "apple,boy,cat,dog,egg,foot,apple,dog".split(",");
        //过滤字母a打头的
        Stream.of(arrs).filter(s -> s.startsWith("a")).forEach(System.out::println);
        System.out.println("-------");
        //去重
        Stream.of(arrs).distinct().forEach(System.out::println);
        System.out.println("-------");
        //排序
        Stream.of(arrs).sorted().forEach(System.out::println);
        Stream.of(arrs).sorted(String::compareTo).forEach(System.out::println);
        //peek
        System.out.println("-------");
        Stream.of(arrs).peek(s -> System.out.println("Seek: " + s)).forEach(System.out::println);
        //skip, limit，相当于取第2到4个元素
        System.out.println("-------");
        Stream.of(arrs).skip(1).limit(3).forEach(System.out::println);
    }

    @Test
    public void testStreamFinish() {
        String[] arrs = "apple,boy,cat,dog,egg,foot,apple,dog".split(",");
        //打印
        Stream.of(arrs).forEach(System.out::println);
        System.out.println("-------");
        //转数组
        Stream.of(arrs).toArray(String[]::new);
        System.out.println("-------");
        //合并，将所有元素合并
        Stream.of(arrs).reduce((s1, s2) -> s1 + s2).ifPresent(System.out::println);
        //带初始元素合并
        String str = Stream.of(arrs).reduce("start:", (s1, s2) -> s1 + s2);
        //初始、合并、后置
        StringBuilder builder = Stream.of(arrs).reduce(new StringBuilder(), (sb, s) -> {
            sb.append(s);
            return sb;
        }, (sb, b) -> sb);
        System.out.println("-------");
        //收集转换
        List<String> list = Stream.of(arrs).collect(Collectors.toList());
        Set<String> set = Stream.of(arrs).distinct().collect(Collectors.toSet());
    }

    @Test
    public void testStreamFinish2() {
        String[] arrs = "apple,boy,cat,dog,egg,foot,apple,dog".split(",");
        //分析函数
        Stream.of(arrs).min(String::compareTo).ifPresent(System.out::println);
        Stream.of(arrs).max(String::compareTo).ifPresent(System.out::println);
        System.out.println(Stream.of(arrs).count());
        System.out.println("-------");
        //匹配，任一包含a，全都包含a，全都不包含a
        Predicate<String> p = s -> s.contains("a");
        boolean anyMatch = Stream.of(arrs).anyMatch(p);
        boolean allMatch = Stream.of(arrs).allMatch(p);
        boolean noneMatch = Stream.of(arrs).noneMatch(p);
        System.out.println("-------");
        //获取第一个或任意一个元素
        Stream.of(arrs).findFirst().ifPresent(System.out::println);
        Stream.of(arrs).findAny().ifPresent(System.out::println);
        //迭代器
        Iterator<String> iter = Stream.of(arrs).iterator();
    }

    @Test
    public void testStream() {
        String[] arrs = "apple,boy,cat,dog,egg,foot,apple,dog".split(",");
        //stream1与stream2不同
        Stream<String> stream1 = Stream.of(arrs);
        Stream<String> stream2 = stream1.map(String::toUpperCase);

        //不执行终止操作，stream是不会进行循环的
        Stream<String> stream3 = Stream.of(arrs).peek(System.out::println);
        System.out.println("此时没有输出！");
        System.out.println("此时才输出：" + stream3.count());

        //多个中间操作时，一个元素全部完成操作后才会进入下一个循环
        Stream.of(arrs).map(String::toUpperCase)
            .peek(s -> System.out.println("peek1：" + s))
            .filter(s -> s.contains("A"))
            .peek(s -> System.out.println("peek2：" + s))
            .forEach(s -> System.out.println("forEach：" + s));
    }

    class User {

        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    @Test(expected = NoSuchElementException.class)
    public void testOptional() {
        User user = new User(null);
        //以上面的User对象为例，要对用户名进行大写转换要如何判断
        String upper = "default";
        if (user != null) {
            String name = user.getName();
            if (name != null) {
                upper = name.toUpperCase();
            }
        }
        //使用Optional
        upper = Optional.ofNullable(user)
            .map(u -> u.getName())
            .map(n -> n.toUpperCase())
            .orElse("default");
        //如果不为空执行
        Optional.ofNullable(user).ifPresent(System.out::println);
        //如果为空，返回其它值
        Optional.ofNullable(user).map(u -> u.getName()).orElseGet(String::new);
        //如果为空，抛异常
        Optional.ofNullable(user).map(u -> u.getName()).orElseThrow(NoSuchElementException::new);
    }

    @Test
    public void testBase64() {
        String str = "cesgroup";
        String base64 = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println(base64);

        String s = new String(Base64.getDecoder().decode(base64));
        Assert.assertEquals(str, s);
    }
}
