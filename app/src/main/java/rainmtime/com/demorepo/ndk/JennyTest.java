package rainmtime.com.demorepo.ndk;

import io.github.landerlyoung.jenny.NativeClass;

/**
 * Created by 雨时光 on 2019-08-07 14:35
 */
@NativeClass
public class JennyTest {

    public native int add(int a, int b);

    public native void cpp_magic(String s, byte[] data);

    public native void sub(int a, int b);
}
