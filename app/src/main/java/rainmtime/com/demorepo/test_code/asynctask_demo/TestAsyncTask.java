package rainmtime.com.demorepo.test_code.asynctask_demo;

import android.os.AsyncTask;

/**
 * Created by chunyu on 2019/1/11 10:57 AM.
 * Email: 746431278@qq.com
 *
 * task = new TestAsyncTask()
 * task.execute( "https://downloads/fm.jpg")
 *
 * 实现原理，serialExecute 在一个线程中，ThreadPoolExecutor 线程池
 */
public class TestAsyncTask extends AsyncTask<String, Void, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    /**
     * @param strings 下载所需要的url
     * @return
     */
    @Override
    protected Void doInBackground(String... strings) {
        //sleep(3000)
//        publishProgress(进度);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

}
