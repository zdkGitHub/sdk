/**
 * <p>Copyright:Copyright(c) 2018</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.java8.inter</p>
 * <p>文件名:EnhanceInrterfac.java</p>
 * <p>创建时间:2018-09-15 14:24</p>
 * <p>作者:huz</p>
 */

package com.cesgroup.java8.inter;

/**
 *
 * @author huz
 * @version 1.0.0 2018-09-15
 */
public interface EnhanceInterface {

    //相当于：public static final String str
    String str = "public static final";

    //相当于：public void execute(String str)
    void execute(String str);

    //Java8静态方法，必须实现内容
    static void staticExecute() {
        System.out.println("EnhanceInrterface.staticExecute()");
    }

    //Java8默认方法
    default void defaultExecute() {
        System.out.println("EnhanceInterface.defaultExecute()");
    }
}
