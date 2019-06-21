package rainmtime.com.demorepo.test_code.okhttp;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
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

        asynchronousGet();
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

