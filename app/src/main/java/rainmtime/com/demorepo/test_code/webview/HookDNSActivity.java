package rainmtime.com.demorepo.test_code.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.utils.DNSHookUtils;

/**
 * Created by 雨时光 on 2019-08-23 16:24
 */
public class HookDNSActivity extends AppCompatActivity {


    private static final String TAG = "HookDNSActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hookdns_activity);
        DNSHookUtils.getInstance().init();
        dnsLookUP();
    }


    private void dnsLookUP() {

        new Thread() {
            @Override
            public void run() {
                InetAddress[] result = new InetAddress[0];
                try {
                    result = InetAddress.getAllByName("172.21.7.230");
                    if (result != null) {
                        ArrayList<InetAddress> lists = new ArrayList<>(Arrays.asList(result));
                        for (InetAddress item : lists) {
                            Log.i("chunyu-test-dns", item != null ? item.getHostAddress() : "");
                        }
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
