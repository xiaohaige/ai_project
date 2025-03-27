package com.empedocles.travelapp.data.remote

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class LoggingInterceptor(private val context: Context) : Interceptor {

    //NetworkLog|OkHttp|MockTravelApiService

    private val TAG = "NetworkLog"
    private val MAX_LINE_LENGTH = 1000 // Logcat 一行最大支持 4000 个字符
    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d(TAG, message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun saveResponseToFile(url: String, responseBody: String) {
        try {
            // 只保存 getAllTravelList 的响应数据
            if (!url.contains("AllTravelList")) {
                return
            }

            // 获取应用的私有目录
            val appDir = context.getExternalFilesDir(null)
            if (appDir == null) {
                Log.e(TAG, "无法获取应用私有目录")
                return
            }

            // 创建 api_responses 目录
            val responseDir = File(appDir, "api_responses")
            if (!responseDir.exists()) {
                responseDir.mkdirs()
            }

            val file = File(responseDir, "travel_all_list.json")
            Log.d(TAG, "准备保存响应数据到文件: ${file.absolutePath}")

            // 写入文件
            FileWriter(file).use { writer ->
                writer.write(responseBody)
            }

            Log.d(TAG, "响应数据已保存到文件: ${file.absolutePath}")
        } catch (e: Exception) {
            Log.e(TAG, "保存响应数据到文件失败", e)
        }
    }

    private fun splitLongLine(line: String): List<String> {
        if (line.length <= MAX_LINE_LENGTH) {
            return listOf(line)
        }
        
        val result = mutableListOf<String>()
        var start = 0
        while (start < line.length) {
            val end = minOf(start + MAX_LINE_LENGTH, line.length)
            result.add(line.substring(start, end))
            start = end
        }
        return result
    }

    private fun logRequest(request: okhttp3.Request) {
        Log.d(TAG, "==================== 请求开始 ====================")
        Log.d(TAG, "请求方法: ${request.method}")
        
        // 分段显示URL
        Log.d(TAG, "请求URL开始:")
        splitLongLine(request.url.toString()).forEachIndexed { index, part ->
            Log.d(TAG, part)
        }
        Log.d(TAG, "请求URL结束")
        
        // 打印请求头
        Log.d(TAG, "请求头开始:")
        request.headers.forEach { (name, value) ->
            splitLongLine("$name: $value").forEachIndexed { index, part ->
                Log.d(TAG, part)
            }
        }
        Log.d(TAG, "请求头结束")
        
        // 打印请求体
        val requestBody = request.body
        if (requestBody != null) {
            Log.d(TAG, "请求体开始:")
            val buffer = okio.Buffer()
            requestBody.writeTo(buffer)
            val requestBodyString = buffer.readUtf8()
            requestBodyString.split("\n").forEachIndexed { lineIndex, line ->
                splitLongLine(line).forEachIndexed { partIndex, part ->
                    Log.d(TAG, part)
                }
            }
            Log.d(TAG, "请求体结束")
        }
        
        Log.d(TAG, "==================== 请求结束 ====================")
    }

    private fun logResponse(response: Response, responseBody: String?) {
        Log.d(TAG, "==================== 响应开始 ====================")
        Log.d(TAG, "响应码: ${response.code}")
        
        // 打印响应头
        Log.d(TAG, "响应头开始:")
        response.headers.forEach { (name, value) ->
            splitLongLine("$name: $value").forEachIndexed { index, part ->
                Log.d(TAG, part)
            }
        }
        Log.d(TAG, "响应头结束")
        
        // 打印响应体
        if (responseBody == null) {
            Log.d(TAG, "响应体: null")
        } else {
            Log.d(TAG, "响应体开始:")
            responseBody.split("\n").forEachIndexed { lineIndex, line ->
                splitLongLine(line).forEachIndexed { partIndex, part ->
                    Log.d(TAG, part)
                }
            }
            Log.d(TAG, "响应体结束")
        }
        
        Log.d(TAG, "==================== 响应结束 ====================")
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        logRequest(request)
        
        val response = loggingInterceptor.intercept(chain)
        
        // 获取响应体
        val responseBody = response.body?.string()
        // 创建新的响应体，因为原始响应体只能读取一次
        val newResponse = response.newBuilder()
            .body(responseBody?.let { okhttp3.ResponseBody.create(response.body?.contentType(), it) })
            .build()
        
        logResponse(response, responseBody)

        // 保存响应数据到文件
        responseBody?.let {
            saveResponseToFile(request.url.toString(), it)
        }

        return newResponse
    }
} 