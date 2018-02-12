package rainmtime.com.demorepo.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

import rainmtime.com.demorepo.global.GlobalContext;

/**
 * Created by chunyu on 2018/2/12 下午3:31.
 * Email: 746431278@qq.com
 */

public final class StorageUtils {

    private static final String TAG = "StorageUtils";


    /**
     * @return 获取在其中存储内部文件的文件系统目录的绝对路径。
     */
    public static File getFilesDir() {
        return GlobalContext.get().getApplication().getFilesDir();
    }

    /**
     * <pre>
     *     如果您正在处理的文件不适合其他应用使用（例如仅供您的应用使用的图形纹理或音效）
     *     ，则应该通过调用 getExternalFilesDir() 来使用外部存储上的私有存储目录。
     *     此方法还会采用 type 参数指定子目录的类型（例如 DIRECTORY_MOVIES）。
     *     如果您不需要特定的媒体目录，请传递 null 以接收应用私有目录的根目录
     * </pre>
     *
     * @param type {@link Environment#DIRECTORY_MUSIC}
     * @return /storage/emulated/0/Android/data/rainmtime.com.demorepo/files/Music
     */
    public static File getExternalFilesDir(String type) {
        return GlobalContext.get().getApplication().getExternalFilesDir(type);
    }

    /**
     * <pre>
     *     要打开表示应该将缓存文件保存到的外部存储目录的 File，请调用 getExternalCacheDir()。
     *     如果用户卸载您的应用，这些文件也会被自动删除。与前述 ContextCompat.getExternalFilesDirs()
     *     相似，您也可以通过调用 ContextCompat.getExternalCacheDirs() 来访问辅助外部存储（如果可用）上的缓存目录。
     * </pre>
     *
     * @return /storage/emulated/0/Android/data/rainmtime.com.demorepo/cache
     */
    public static File getExternalCacheDir() {
        return GlobalContext.get().getApplication().getExternalCacheDir();
    }

    /**
     * <pre>
     *     一般而言，应该将用户可通过您的应用获取的新文件保存到设备上的“公共”位置，
     *     以便其他应用能够在其中访问这些文件，并且用户也能轻松地从该设备复制这些文件。
     *     执行此操作时，应使用共享的公共目录之一，例如 Music/、Pictures/ 和 Ringtones/ 等
     *     要获取表示相应的公共目录的 File，请调用 getExternalStoragePublicDirectory()，
     *     向其传递您需要的目录类型，例如 DIRECTORY_MUSIC、DIRECTORY_PICTURES、
     *     DIRECTORY_RINGTONES 或其他类型。通过将您的文件保存到相应的媒体类型目录，
     *     系统的媒体扫描程序可以在系统中正确地归类您的文件（例如铃声在系统设置中显示为铃声而不是音乐）。
     * </pre>
     *
     * @param type {@link Environment#DIRECTORY_MUSIC}
     * @return
     */
    public static File getpublicDir(String type) {
        return Environment.getExternalStoragePublicDirectory(type);
    }


    public static File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);

        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }

        return file;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadable() {

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    /**
     * 02-12 17:31:47.776 16446-16446/rainmtime.com.demorepo W/StorageUtils: getExternalStoragePublicDirectory(music):   /storage/emulated/0/Music
     * 02-12 17:31:47.778 16446-16446/rainmtime.com.demorepo W/StorageUtils: getExternalStorageDirectory:   /storage/emulated/0
     * 02-12 17:31:47.778 16446-16446/rainmtime.com.demorepo W/StorageUtils: getDownloadCacheDirectory:   /data/cache
     * 02-12 17:31:47.781 16446-16446/rainmtime.com.demorepo E/StorageUtils: getExternalCacheDir:   /storage/emulated/0/Android/data/rainmtime.com.demorepo/cache
     * 02-12 17:31:47.783 16446-16446/rainmtime.com.demorepo E/StorageUtils: getExternalFilesDir：/storage/emulated/0/Android/data/rainmtime.com.demorepo/files/Music
     * 02-12 17:31:47.784 16446-16446/rainmtime.com.demorepo W/StorageUtils: getRootDirectory:   /system
     * 02-12 17:31:47.784 16446-16446/rainmtime.com.demorepo W/StorageUtils: getDataDirectory:   /data
     * 02-12 17:31:47.784 16446-16446/rainmtime.com.demorepo E/StorageUtils: getFilesDir:   /data/user/0/rainmtime.com.demorepo/files
     */
    public static void printDir() {
        if (StorageUtils.isExternalStorageWritable()) {
            Log.w(TAG, "getExternalStoragePublicDirectory(music):   " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
            Log.w(TAG, "getExternalStorageDirectory:   " + Environment.getExternalStorageDirectory());
            Log.w(TAG, "getDownloadCacheDirectory:   " + Environment.getDownloadCacheDirectory());
            Log.e(TAG, "getExternalCacheDir:   " + StorageUtils.getExternalCacheDir());
            Log.e(TAG, "getExternalFilesDir：" + StorageUtils.getExternalFilesDir(Environment.DIRECTORY_MUSIC));

            Log.w(TAG, "getRootDirectory:   " + Environment.getRootDirectory());
            Log.w(TAG, "getDataDirectory:   " + Environment.getDataDirectory());
            Log.e(TAG, "getFilesDir:   " + StorageUtils.getFilesDir());
        }
    }

}
