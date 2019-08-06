LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := test-jni

LOCAL_SRC_FILES := ndkdemotest.cpp

include $(BUILD_SHARED_LIBRARY)