package rainmtime.com.demorepo.test_code.webview;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import rainmtime.com.demorepo.R;

/**
 * Created by 雨时光 on 2019-07-02 14:41
 */
public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
    }

    private void initView(){


        mWebView = findViewById(R.id.my_webview);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebClient());



        mWebView.loadData("<html>\n" +
                "  \n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\"/>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link href=\"https://cdn.bootcss.com/jsoneditor/5.32.5/jsoneditor.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <script src=\"https://cdn.bootcss.com/jsoneditor/5.32.5/jsoneditor.min.js\"></script>\n" +
                "    <link rel=\"stylesheet\" href=\"https://cdn.bootcss.com/prism/1.16.0/themes/prism.min.css\">\n" +
                "    <script src=\"https://cdn.bootcss.com/prism/1.16.0/prism.min.js\"></script>\n" +
                "    <script>\n" +
                "       function parseJsonForpdd(jsonstr){\n" +
                "        var e=new JSONEditor(document.getElementById('ee'),{'mode':'view'});\n" +
                "        e.set(JSON.parse(decodeURIComponent(escape(window.atob(jsonstr)))));\n" +
                "       }\n" +
                "    </script>\n" +
                "  </head>\n" +
                "  \n" +
                "  <body>\n" +
                "    <div id=\"ee\"></div>\n" +
                "    <div id='hh' style='display:none;width:100%;height:100%;'>\n" +
                "      <pre>\n" +
                "        <code class=\"language-css\" style=\"word-wrap:break-word;white-space:pre-wrap;font-size:.8em;\" id=\"cc\"></code>\n" +
                "      </pre>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "\n" +
                "</html>","text/html;charset=utf-8","utf-8");



    }


    public static class MyWebClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.i("chunyu-test","url:"+url);
            view.loadUrl("javascript:parseJsonForpdd('"+"ewogICAgIm5hbWUiOiAiQmVKc29uIiwKICAgICJ1cmwiOiAiaHR0cDovL3d3dy5iZWpzb24uY29tIiwKICAgICJwYWdlIjogODgsCiAgICAiaXNOb25Qcm9maXQiOiB0cnVlLAogICAgImFkZHJlc3MiOiB7CiAgICAgICAgInN0cmVldCI6ICLnp5HmioDlm63ot68uIiwKICAgICAgICAiY2l0eSI6ICLmsZ/oi4/oi4/lt54iLAogICAgICAgICJjb3VudHJ5IjogIuS4reWbvSIKICAgIH0sCiAgICAibGlua3MiOiBbCiAgICAgICAgewogICAgICAgICAgICAibmFtZSI6ICJHb29nbGUiLAogICAgICAgICAgICAidXJsIjogImh0dHA6Ly93d3cuZ29vZ2xlLmNvbSIKICAgICAgICB9LAogICAgICAgIHsKICAgICAgICAgICAgIm5hbWUiOiAiQmFpZHUiLAogICAgICAgICAgICAidXJsIjogImh0dHA6Ly93d3cuYmFpZHUuY29tIgogICAgICAgIH0sCiAgICAgICAgewogICAgICAgICAgICAibmFtZSI6ICJTb1NvIiwKICAgICAgICAgICAgInVybCI6ICJodHRwOi8vd3d3LlNvU28uY29tIgogICAgICAgIH0KICAgIF0KfQ=="+"')");

        }
    }







}
