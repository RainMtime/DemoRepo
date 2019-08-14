#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_rainmtime_com_demorepo_ndk_NDKTools_getStringFromNDK(
        JNIEnv *env, jobject /* this */) {
    std::string hello = "(*^__^*) 嘻嘻……~Hello from C++ 隔壁老李头";
    return env->NewStringUTF(hello.c_str());
}