package rainmtime.com.demorepo.test_code.proxy_test;

/**
 * Created by chunyu on 2018/12/13 3:28 PM.
 * Email: 746431278@qq.com
 */
public class Vendor implements Sell {

    @Override
    public void sell() {
        System.out.println("Vendor::sell()");
    }

    @Override
    public void ad() {
        System.out.println("Vendor::ad()");
    }
}
