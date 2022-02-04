package com.example.native_example


import io.flutter.embedding.android.FlutterActivity
import android.os.Build
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.util.Base64
import android.app.AlertDialog

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.flutter.dev/info"
    private val CHANNEL2 = "com.flutter.dev/encrypto"
    private val CHANNEL3 = "com.flutter.dev/dialog"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL3)
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
                } else if (call.method == "showDialog") {
                    AlertDialog.Builder(this)
                        .setTitle("Flutter")
                        .setMessage("Aler From Native")
                        .show()
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
