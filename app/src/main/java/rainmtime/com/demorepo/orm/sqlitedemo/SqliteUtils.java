package rainmtime.com.demorepo.orm.sqlitedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import javax.annotation.Nonnull;

import rainmtime.com.demorepo.utils.StorageUtils;

/**
 * Created by chunyu on 2018/2/13 上午10:26.
 * Email: 746431278@qq.com
 */

public class SqliteUtils {


    /**
     * 测试用的代码片段
     */
    public static void sqliteTest() {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(StorageUtils.getDataBaseDir("sqliteTest.db3").toString(), null);

        if (!isTableExist("user", "sqliteTest.db3")) {
            String sql = "create table if not exists user(id varchar(255) primary key,"
                    + "user_name varchar(255),"
                    + "user_age varchar(255) ) ";
            db.execSQL(sql);
            insertData(db, "10086", "chunyu", "24");
        }
        db.close();

    }

    private static void insertData(SQLiteDatabase db, String id, String name, String age) {
        db.execSQL("insert into user values(?,?,?)", new String[]{id, name, age});
    }

    public static void execSQL(@NonNull String dbName, @NonNull String sql) {

        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(StorageUtils.getDataBaseDir(dbName).toString(), null);

        db.execSQL(sql);

        db.close();
    }


    /**
     * @param tableName 表名
     * @param dbName    数据库名称
     * @return
     */
    public static boolean isTableExist(@NonNull String tableName, @NonNull String dbName) {
        boolean isTableExist = true;
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(StorageUtils.getDataBaseDir(dbName).toString(), null);
        Cursor cursor = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='user" + tableName + "'", null);

        if (cursor.moveToNext()) {
            if (cursor.getInt(0) == 0) {
                isTableExist = false;
            }
        }
        cursor.close();
        db.close();
        return isTableExist;
    }


    /**
     * @param db        数据库
     * @param tableName 表名
     * @return
     */
    public static boolean isTableExist(SQLiteDatabase db, @Nonnull String tableName) {

        boolean isTableExist = true;
        Cursor cursor = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='user" + tableName + "'", null);

        if (cursor.moveToNext()) {
            if (cursor.getInt(0) == 0) {
                isTableExist = false;
            }
        }
        cursor.close();
        db.close();
        return isTableExist;
    }
}
