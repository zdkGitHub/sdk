/**
 * <p>Copyright:Copyright(c) 2018</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.java8.lambda</p>
 * <p>文件名:Lambda.java</p>
 * <p>创建时间:2018-09-15 15:25</p>
 * <p>作者:huz</p>
 */

package com.cesgroup.java8.lambda;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author huz
 * @version 1.0.0 2018-09-15
 */
public class Lambda {

    public void lambdaSort(List<String> strList) {
        strList.sort((t1, t2) -> {
            return t1.compareTo(t2);
        });
    }

    public void innerClassSort(List<String> strList) {
        strList.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

}
