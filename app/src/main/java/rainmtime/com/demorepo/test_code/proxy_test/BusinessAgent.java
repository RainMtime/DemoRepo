package rainmtime.com.demorepo.test_code.proxy_test;

/**
 * Created by chunyu on 2018/12/13 3:29 PM.
 * Email: 746431278@qq.com
 */
public class BusinessAgent implements Sell {
    @Override
    public void sell() {
        System.out.println("BusinessAgent::sell()");
    }

    @Override
    public void ad() {
         System.out.println("BusinessAgent::ad()");
    }
}
