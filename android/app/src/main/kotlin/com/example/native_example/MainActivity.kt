package com.example.native_example


import io.flutter.embedding.android.FlutterActivity
import android.os.Build
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.util.Base64

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.flutter.dev/info"
    private val CHANNEL2 = "com.flutter.dev/encrypto"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL2)
            .setMethodCallHandler{ call, result ->
                if (call.method == "getEncrypto") {
//                    val deviceInfo = getDeviceInfo()
//                    result.success(deviceInfo)
                    val data = call.arguments.toString().toByteArray();
                    val changeText = Base64.encodeToString(data, Base64.DEFAULT)
                    result.success(changeText)
                } else if (call.method == "getDecode") {
                    val changedText = Base64.decode(call.arguments.toString(), Base64.DEFAULT)
                    result.success(String(changedText))
                }
            }
    }

    private fun getDeviceInfo(): String {
        val sb = StringBuffer()
        sb.append(Build.DEVICE + "\n")
        sb.append(Build.BRAND + "\n")
        sb.append(Build.MODEL + "\n")
        return sb.toString()
    }
}
