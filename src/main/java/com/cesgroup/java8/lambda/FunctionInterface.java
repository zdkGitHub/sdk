/**
 * <p>Copyright:Copyright(c) 2018</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.java8.lambda</p>
 * <p>文件名:FunctionInterface.java</p>
 * <p>创建时间:2018-09-15 20:14</p>
 * <p>作者:huz</p>
 */

package com.cesgroup.java8.lambda;

import java.util.Objects;

/**
 *
 * @author huz
 * @version 1.0.0 2018-09-15
 */
public class FunctionInterface<T> {

    @FunctionalInterface
    interface Executable<T> {

        @Override
        boolean equals(Object obj);

        void execute(T t);

        default String name() {
            return "name";
        }
    }

    public void execute(Executable<? super T> executable,T t) {
        Objects.requireNonNull(executable);
        executable.execute(t);
    }

    public static void main(String[] args) {
        FunctionInterface<String> functionInterface = new FunctionInterface<String>();
        String param = "abc";
        functionInterface.execute(param1 -> {
            System.out.println(1);
            System.out.println(param1);
            },"a");
    }
}
