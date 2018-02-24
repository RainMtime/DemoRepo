package rainmtime.com.demorepo.orm.sqlitedemo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by chunyu on 2018/2/24 下午4:26.
 * Email: 746431278@qq.com
 */

public class DemoSQLiteOpenHelper extends SQLiteOpenHelper {


    private static final String CREATE_TABLE_SQL = "create table if not exist User(" +
            "id varchar(255) primary key ," +
            "name varchar(255) ," +
            "age interge )";

    private static final String TAG = "DemoSQLiteOpenHelper";


    public DemoSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DemoSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "oldVersion:" + oldVersion + "\t newVersion:" + newVersion);
    }
}
