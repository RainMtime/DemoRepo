//
// Created by chunyu on 2019-08-06.
//

#include "rainmtime_com_demorepo_ndk_NDKTools.h"

JNIEXPORT jstring JNICALL Java_rainmtime_com_demorepo_ndk_NDKTools_getStringFromNDK
        (JNIEnv *env, jclass){
    return env -> NewStringUTF("Hellow JNI");
}
