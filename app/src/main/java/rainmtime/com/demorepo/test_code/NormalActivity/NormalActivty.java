package rainmtime.com.demorepo.test_code.NormalActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;

import okhttp3.HttpUrl;
import rainmtime.com.demorepo.R;

/**
 * Created by 雨时光 on 2019-07-05 15:13
 */
public class NormalActivty extends AppCompatActivity {

    private HeaderDetailLayout mHeaderDetail;
    private ArrayList<String> datas = new ArrayList<>();
    private Button button;
    private SeekBar mSeekBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        mHeaderDetail = findViewById(R.id.header_detail);
        button = findViewById(R.id.btn);
        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    Log.i("chunyu-test","progress:"+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈,你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈你归来仍是少年哈");
        datas.add("hah");
        datas.add("我的天涯，啊哈哈");
        datas.add("愿你归来仍是少年哈");
        datas.add("hah");

        mHeaderDetail.setData(datas);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        InetAddress addr = null;
                        String ip = "";
                        String localIp = "";
                        try {
                            URL url = new
                                    URL("https://www.baidu.com");
                            addr = getHostAndName(url);
//                            InetAddress localAddr = InetAddress.getLocalHost();
                            InetAddress localAddr = getLocalAddress();

                            localIp = localAddr.toString();
//                            ip = addr.getHostAddress();
                            ip = addr.toString();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        Log.i("chunyu-test", "iP:" + ip + "localIp:" + localIp);

                        Log.i("chunyu-test","localIp:"+getLocalAddress());

                        try {
                            useHttpUrlConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }.start();


            }
        });
    }


    private void useHttpUrlConnection() throws IOException {
        // 如果使用 POST 方法
        URL url = new
                URL("https://www.baidu.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
// 设置连接超时时间
        connection.setConnectTimeout(15000);

// 设置读取超时时间
        connection.setReadTimeout(15000);

// 设置是否从 httpUrlConnection 读入，默认情况下是true;
        connection.setDoInput(true);
        connection.connect();

        convertStreamToString(connection);
        connection.disconnect();
    }

    private String convertStreamToString(HttpURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        String reponse = sb.toString();
        Log.i("chunyu-test", "response:" + reponse);
        return reponse;
    }


    private InetAddress getHostAndName(URL url) {
        String host = url.getHost();
        InetAddress hostAddress = null;
        if (host == null || host.equals("")) {
            return null;
        } else {
            try {
                hostAddress = InetAddress.getByName(host);
            } catch (UnknownHostException ex) {
                return null;
            } catch (SecurityException se) {
                return null;
            }
        }
        return hostAddress;
    }


    private static InetAddress getLocalAddress(){
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while( b.hasMoreElements()){
                for ( InterfaceAddress f : b.nextElement().getInterfaceAddresses())
                    if ( f.getAddress().isSiteLocalAddress())
                        return f.getAddress();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static  InetAddress getLocalAddress2(){
        try {
            InetAddress address =  InetAddress.getLocalHost();
            return address;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void testURL(){
        try {
           String str =    URLEncoder.encode("https://s.taobao.com/search?q=钱包&imgfile=&commend=all&ssid=s5-e&search_type=item&sourceId=tb.index&spm=a21bo.2017.201856-taobao-item.1&ie=utf8&initiative_id=tbindexz_20170306");
            Log.i("chunyu-urlencode:",str);
            String str1 =    URLEncoder.encode("https://s.taobao.com/search?q=%E8%A1%A3%E6%9C%8D&imgfile=&commend=all&ssid=s5-e&search_type=item&sourceId=tb.index&spm=a21bo.2017.201856-taobao-item.1&ie=utf8&initiative_id=tbindexz_20170306");
            Log.i("chunyu-urlencode1:",str1);
            URL url1 = new URL("https://s.taobao.com/search?q=%E8%A1%A3%E6%9C%8D&imgfile=&commend=all&ssid=s5-e&search_type=item&sourceId=tb.index&spm=a21bo.2017.201856-taobao-item.1&ie=utf8&initiative_id=tbindexz_20170306");
            HttpUrl url =  HttpUrl.parse("https://s.taobao.com/search?q=%E8%A1%A3%E6%9C%8D&imgfile=&commend=all&ssid=s5-e&search_type=item&sourceId=tb.index&spm=a21bo.2017.201856-taobao-item.1&ie=utf8&initiative_id=tbindexz_20170306");
            Log.i("chunyu-url:","scheme:"+url.scheme());
            Log.i("chunyu-url:","host:"+url.host());
            Log.i("chunyu-url:","argu:"+url.encodedQuery());
            Log.i("chunyu-url:","path:"+url.encodedPath());
            Log.i("chunyu-url1:","scheme:"+url1.getProtocol());
            Log.i("chunyu-url1:","host:"+url1.getHost());
            Log.i("chunyu-url1:","argu:"+url1.getQuery());
            Log.i("chunyu-url1:","path:"+url1.getPath());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }



}
