#include <jni.h>
#include <string>
#include "./SFML/include/Network.hpp"
#include "./SFML/include/System.hpp"
#include <android/log.h>

#define TAG "FTP" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型


sf::Ftp ftp;

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_xuexiang_ftp_FtpClient_connect(JNIEnv *env, jobject instance, jstring ipAddress_) {
    const char *ipAddress = env->GetStringUTFChars(ipAddress_, 0);

    sf::Ftp::Response response = ftp.connect(ipAddress);
    bool result = response.isOk();

    env->ReleaseStringUTFChars(ipAddress_, ipAddress);

    return static_cast<jboolean>(result);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_xuexiang_ftp_FtpClient_login__Ljava_lang_String_2Ljava_lang_String_2(JNIEnv *env,
                                                                              jobject instance,
                                                                              jstring loginName_,
                                                                              jstring password_) {
    const char *loginName = env->GetStringUTFChars(loginName_, 0);
    const char *password = env->GetStringUTFChars(password_, 0);

    bool result = ftp.login(loginName, password).isOk();

    env->ReleaseStringUTFChars(loginName_, loginName);
    env->ReleaseStringUTFChars(password_, password);

    return static_cast<jboolean>(result);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_xuexiang_ftp_FtpClient_login__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_String_2(
        JNIEnv *env, jobject instance, jstring ipAddress_, jstring loginName_, jstring password_) {
    const char *ipAddress = env->GetStringUTFChars(ipAddress_, 0);
    const char *loginName = env->GetStringUTFChars(loginName_, 0);
    const char *password = env->GetStringUTFChars(password_, 0);

    bool result = ftp.connect(ipAddress).isOk();
    if (result) {
        result = ftp.login(loginName, password).isOk();
    }
    env->ReleaseStringUTFChars(ipAddress_, ipAddress);
    env->ReleaseStringUTFChars(loginName_, loginName);
    env->ReleaseStringUTFChars(password_, password);
    return static_cast<jboolean>(result);
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_xuexiang_ftp_FtpClient_download(JNIEnv *env, jobject instance, jstring remoteFile_,
                                         jstring localPath_) {
    const char *remoteFile = env->GetStringUTFChars(remoteFile_, 0);
    const char *localPath = env->GetStringUTFChars(localPath_, 0);

    LOGE("download, remoteFile:%s, localPath:%s", remoteFile, localPath);

    sf::Ftp::Response response = ftp.download(remoteFile, localPath);

    LOGE("download, status:%d", response.getStatus());
    env->ReleaseStringUTFChars(remoteFile_, remoteFile);
    env->ReleaseStringUTFChars(localPath_, localPath);

    return static_cast<jboolean>(response.isOk());
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_xuexiang_ftp_FtpClient_upload(JNIEnv *env, jobject instance, jstring localFile_,
                                       jstring remotePath_) {
    const char *localFile = env->GetStringUTFChars(localFile_, 0);
    const char *remotePath = env->GetStringUTFChars(remotePath_, 0);

    LOGE("upload, localFile:%s, remotePath:%s", localFile, remotePath);

    sf::Ftp::Response response = ftp.upload(localFile, remotePath);

    LOGE("upload, status:%d", response.getStatus());

    env->ReleaseStringUTFChars(localFile_, localFile);
    env->ReleaseStringUTFChars(remotePath_, remotePath);
    return static_cast<jboolean>(response.isOk());
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_xuexiang_ftp_FtpClient_quit(JNIEnv *env, jobject instance) {
    return static_cast<jboolean>(ftp.disconnect().isOk());
}