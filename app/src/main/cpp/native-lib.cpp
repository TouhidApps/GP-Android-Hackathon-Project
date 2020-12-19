#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_touhidapps_hackathonproject_networkService_MyApiUrl_baseUrlFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string mUrl = "aHR0cHM6Ly9hcGkudGhlbW92aWVkYi5vcmcv"; // https://api.themoviedb.org/
    return env->NewStringUTF(mUrl.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_touhidapps_hackathonproject_networkService_MyApiUrl_apiKeyFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string mUrl = "1a97f3b8d5deee1d649c0025f3acf75c";
    return env->NewStringUTF(mUrl.c_str());
}




