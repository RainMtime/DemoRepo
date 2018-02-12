package rainmtime.com.demorepo.utils;

import android.support.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rainmtime.com.demorepo.global.GlobalContext;

/**
 * Created by chunyu on 2018/2/12 下午3:25.
 * Email: 746431278@qq.com
 */

public final class FileUtils {


    /**
     * <pre>
     *     内部存储相关：
     *     您可以直接在设备的内部存储中保存文件。默认情况下，保存到内部存储的文件是应用的私有文件，
     *     其他应用（和用户）不能访问这些文件。 当用户卸载您的应用时，这些文件也会被移除。
     * </pre>
     *
     * @param str      待存储的str
     * @param fileName 文件名
     * @param mode     {@link android.content.Context#MODE_PRIVATE}
     */
    public static void saveStringToInternalFile(@NonNull String str, @NonNull String fileName, int mode) {
        try {
            FileOutputStream fos = GlobalContext.get().getApplication().openFileOutput(fileName, mode);
            try {
                fos.write(str.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 内部存储相关：
     *
     * @param fileName 等待读取的文件
     * @return 返回读取得到的String
     */
    public static String readStringFromInternalFile(@NonNull String fileName) {
        try {
            FileInputStream fis = GlobalContext.get().getApplication().openFileInput(fileName);
            byte[] buff = new byte[1024];

            int hasread = 0;
            StringBuilder sb = new StringBuilder();

            while ((hasread = fis.read(buff)) != -1) {
                sb.append(new String(buff, 0, hasread));
            }
            fis.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <pre>
     *     删除保存在内部存储的文件
     * </pre>
     *
     * @param fileName 删除内部文件
     */
    public static void deleteFileFromInternal(@NonNull String fileName) {
        GlobalContext.get().getApplication().deleteFile(fileName);
    }

    /**
     * @return 返回您的应用当前保存的一系列文件
     */
    public static String[] getAllSavedFilesFromInternal() {
        return GlobalContext.get().getApplication().fileList();
    }


}
