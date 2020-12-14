/**
 * <p>Copyright:Copyright(c) 2018</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.java8.inter</p>
 * <p>文件名:EnhanceInterfaceImpl.java</p>
 * <p>创建时间:2018-09-15 14:38</p>
 * <p>作者:huz</p>
 */

package com.cesgroup.java8.inter;

/**
 *
 * @author huz
 * @version 1.0.0 2018-09-15
 */
public class EnhanceInterfaceImpl implements EnhanceInterface {

    @Override
    public void execute(final String str) {
        System.out.println("EnhanceInterfaceImpl.execute()");
    }

}
