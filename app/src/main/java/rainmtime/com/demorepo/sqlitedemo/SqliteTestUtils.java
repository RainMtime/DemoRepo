package rainmtime.com.demorepo.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;

import rainmtime.com.demorepo.utils.StorageUtils;

/**
 * Created by chunyu on 2018/2/13 上午10:26.
 * Email: 746431278@qq.com
 */

public class SqliteTestUtils {



    public static void sqliteTest() {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(StorageUtils.getDataBaseDir("sqliteTest.db3").toString(), null);

        String sql = "create table user(id varchar(255) primary key,"
                + "user_name varchar(255),"
                + "user_age varchar(255) ) ";

        db.execSQL(sql);


        insertData(db, "10086", "chunyu", "24");

        db.close();

    }


    private static void insertData(SQLiteDatabase db, String id, String name, String age) {
        db.execSQL("insert into user values(?,?,?)", new String[]{id, name, age});
    }
}
