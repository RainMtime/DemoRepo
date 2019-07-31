package rainmtime.com.demorepo.test_code.okhttp;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import rainmtime.com.demorepo.test_code.okhttp.bean.Person;
import rainmtime.com.demorepo.test_code.okhttp.listener.OkHttpEventListener;

/**
 * Created by 人间一小雨 on 2019-05-28 22:17
 * Email: 746431278@qq.com
 */
public class OkHttpActivity extends AppCompatActivity {
    final OkHttpClient client = new OkHttpClient();
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        new Thread() {
//            @Override
//            public void run() {
//                synchronousGet();
//            }
//        }.start();

//        try {
//            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 0);
//            readaFileByLine(new File("/sdcard/chunyu-test/litepal.xml"));
////            writeFile(new File("/sdcard/chunyu-test/env.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        asynchronousGet();

        testIPV6();

        new Thread() {
            @Override
            public void run() {
                testDNSParse("www.baidu.com");
                testDNSParse("1111::5555:6666:b465:310b");
                testDNSParse("180.101.49.11");
            }
        }.start();

    }

    public static void testIPV6() {
        Request request = new Request.Builder().url("https://www.baidu.com/s?wd=%E5%91%A8%E6%9D%B0%E4%BC%A6&rsv_spt=1&rsv_iqid=0xa108897e00334acb&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=21&rsv_sug1=18&rsv_sug7=100&rsv_sug2=0&inputT=3834&rsv_sug4=5081").build();
        Request request1 = new Request.Builder().url("https://[1111::5555:6666:180.101.49.11]:443/s?wd=%E5%91%A8%E6%9D%B0%E4%BC%A6&rsv_spt=1&rsv_iqid=0xa108897e00334acb&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=21&rsv_sug1=18&rsv_sug7=100&rsv_sug2=0&inputT=3834&rsv_sug4=5081").build();
        Request request2 = new Request.Builder().url("https://180.101.49.11/s?wd=%E5%91%A8%E6%9D%B0%E4%BC%A6&rsv_spt=1&rsv_iqid=0xa108897e00334acb&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=21&rsv_sug1=18&rsv_sug7=100&rsv_sug2=0&inputT=3834&rsv_sug4=5081").build();
        Request request3 = new Request.Builder().url("https://180.101.49.11/s?wd=%E5%91%A8%E6%9D%B0%E4%BC%A6&rsv_spt=1&rsv_iqid=0xa108897e00334acb&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=21&rsv_sug1=18&rsv_sug7=100&rsv_sug2=0&inputT=3834&rsv_sug4=5081").build();

        printLog(request);
        printLog(request1);
        printLog(request2);

    }

    public static void testDNSParse(String host) {
        Log.i("chunyu-dns", "host:" + host);
        try {
            List<InetAddress> list = Dns.SYSTEM.lookup(host);
            for (int i = 0; i < list.size(); i++) {
                final InetAddress inetAddress = list.get(i);
                if (inetAddress != null) {
                    Log.i("chunyu-dns", inetAddress.getHostAddress());
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private static void printLog(@NonNull Request request) {
        Log.w("chunyu-host", " host:" + request.url().host());
    }


    private void readaFileByLine(File file) throws IOException {
        try (Source fileSource = Okio.source(file);
             BufferedSource bufferedSource = Okio.buffer(fileSource)) {
            while (true) {
                String line = bufferedSource.readUtf8Line();
//                String line = bufferedSource.readUtf8LineStrict(); //这个限制了每行文本结束通过"\n \r\n"
                if (line == null) break;

                System.out.println(line);
            }
        }
    }

    private void writeFile(File file) throws IOException {
        if (file != null && !file.exists()) {
            file.createNewFile();
        }
        try (Sink fileSink = Okio.sink(file);
             BufferedSink bufferedSink = Okio.buffer(fileSink)) {
            for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
                bufferedSink.writeUtf8(entry.getKey());
                bufferedSink.writeUtf8("=");
                bufferedSink.writeUtf8(entry.getValue());
                bufferedSink.writeUtf8("\n");
            }
        }
    }


    private void testJsonParser() {
        Person person = new Gson().fromJson("{\n" +
                "    \"name\": \"yushiguang\",\n" +
                "    \"age\": 18,\n" +
                "    \"ischangeLevel\": false,\n" +
                "    \"level\": 23\n" +
                "}", Person.class);
        Log.i("chunyu-test", person.toString());
    }

    public void synchronousGet() {

//        OkHttpClient.Builder builder = new OkHttpClient.Builder().eventListenerFactory()
//        OkHttpEventListener listener = new OkHttpEventListener();
        OkHttpClient client1 = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        Response response = null;
        try {
            response = client1.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void asynchronousGet() {
        OkHttpClient client1 = new OkHttpClient.Builder().eventListenerFactory(new OkHttpEventListener.OKHttpEventListenerFactory()).build();
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();

        client1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                }
            }
        });
    }

    public void postString() {
        final MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("text/x-markdown; charset=utf-8");


        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

