package com.empedocles.travelapp.data.mock

import android.content.Context
import android.util.Log
import com.empedocles.travelapp.data.remote.TravelApiService
import com.empedocles.travelapp.domain.model.TravelModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.File
import java.io.FileWriter
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockTravelApiService @Inject constructor(
    @ApplicationContext private val context: Context
) : TravelApiService {
    private val gson = Gson()
    private val TAG = "MockTravelApiService"

    private fun readJsonFromAssets(fileName: String): String? {
        try {
            // 使用 AssetManager 读取 assets 目录下的文件
            val inputStream = context.assets.open(fileName)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            
            Log.d(TAG, "成功从 assets 目录读取文件: $fileName")
            return jsonString
        } catch (e: Exception) {
            Log.e(TAG, "从 assets 目录读取文件失败: $fileName", e)
            return null
        }
    }

    override suspend fun getAllTravelList(): Response<List<TravelModel>> {
        Log.d(TAG, "开始获取所有旅行列表")
        val json = readJsonFromAssets("travel_all_list.json")
        if (json != null) {
            try {
                val travelList = gson.fromJson<List<TravelModel>>(json, object : TypeToken<List<TravelModel>>() {}.type)
                Log.d(TAG, "成功从文件加载旅行列表")
                Log.d(TAG, "加载了 ${travelList.size} 个旅行项目")
                travelList.forEach { item ->
                    Log.d(TAG, """
                        旅行项目详情:
                        ID: ${item.id}
                        标题: ${item.title}
                        描述: ${item.description}
                        城市: ${item.city}
                        国家: ${item.country}
                        分类: ${item.category}
                        书签状态: ${item.isBookmark}
                        图片数量: ${item.images.size}
                        图片列表: ${item.images.map { it.url }.joinToString()}
                    """.trimIndent())
                }
                return Response.success(travelList)
            } catch (e: Exception) {
                Log.e(TAG, "解析旅行列表数据失败", e)
            }
        }
        Log.d(TAG, "使用默认模拟数据")
        return Response.success(MockData.mockTravelList)
    }

    override suspend fun getTravelItem(id: String): Response<TravelModel> {
        Log.d(TAG, "开始获取单个旅行项目，ID: $id")
        
        // 从 travel_all_list.json 中读取所有数据
        val json = readJsonFromAssets("travel_all_list.json")
        if (json != null) {
            try {
                val travelList = gson.fromJson<List<TravelModel>>(json, object : TypeToken<List<TravelModel>>() {}.type)
                Log.d(TAG, "成功从 assets 加载旅行列表")
                
                // 更新图片 URL
                val updatedTravelList = travelList.map { item ->
                    val updatedImages = item.images.map { image ->
                        image.copy(
                            url = "file:///android_asset/pics/${URLEncoder.encode(image.url, StandardCharsets.UTF_8.toString())}"
                        )
                    }
                    item.copy(images = updatedImages)
                }
                
                // 查找指定 ID 的项目
                val item = updatedTravelList.find { it.id == id }
                if (item != null) {
                    Log.d(TAG, """
                        找到旅行项目详情:
                        ID: ${item.id}
                        标题: ${item.title}
                        描述: ${item.description}
                        城市: ${item.city}
                        国家: ${item.country}
                        分类: ${item.category}
                        书签状态: ${item.isBookmark}
                        图片数量: ${item.images.size}
                        图片列表: ${item.images.joinToString()}
                    """.trimIndent())
                    return Response.success(item)
                } else {
                    Log.d(TAG, "未找到 ID 为 $id 的旅行项目")
                }
            } catch (e: Exception) {
                Log.e(TAG, "解析旅行列表数据失败", e)
            }
        }
        
        Log.d(TAG, "使用默认模拟数据")
        val item = MockData.mockTravelList.first { it.id == id }
        return Response.success(item)
    }

    override suspend fun searchAllTravelList(query: String): Response<List<TravelModel>> {
        Log.d(TAG, "开始搜索旅行列表，关键词: $query")
        
        // 从 travel_all_list.json 中读取所有数据
        val json = readJsonFromAssets("travel_all_list.json")
        if (json != null) {
            try {
                val travelList = gson.fromJson<List<TravelModel>>(json, object : TypeToken<List<TravelModel>>() {}.type)
                Log.d(TAG, "成功从文件加载旅行列表")
                
                // 根据关键词过滤数据
                val filteredList = travelList.filter { item ->
                    item.title.contains(query, ignoreCase = true) ||
                    item.description.contains(query, ignoreCase = true) ||
                    item.city.contains(query, ignoreCase = true) ||
                    item.country.contains(query, ignoreCase = true) ||
                    item.category.contains(query, ignoreCase = true)
                }
                
                Log.d(TAG, "找到 ${filteredList.size} 个匹配结果")
                filteredList.forEach { item ->
                    Log.d(TAG, """
                        匹配项目详情:
                        ID: ${item.id}
                        标题: ${item.title}
                        描述: ${item.description}
                        城市: ${item.city}
                        国家: ${item.country}
                        分类: ${item.category}
                        书签状态: ${item.isBookmark}
                        图片数量: ${item.images.size}
                    """.trimIndent())
                }
                
                return Response.success(filteredList)
            } catch (e: Exception) {
                Log.e(TAG, "解析旅行列表数据失败", e)
            }
        }
        
        Log.d(TAG, "使用默认模拟数据")
        val filteredList = MockData.mockTravelList.filter { 
            it.title.contains(query, ignoreCase = true) ||
            it.description.contains(query, ignoreCase = true) ||
            it.city.contains(query, ignoreCase = true) ||
            it.country.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true)
        }
        return Response.success(filteredList)
    }

    override suspend fun putTravelItem(id: String, isBookmark: Boolean): Response<TravelModel> {
        Log.d(TAG, "开始更新旅行项目，ID: $id, 书签状态: $isBookmark")
        
        // 从 travel_all_list.json 中读取所有数据
        val json = readJsonFromAssets("travel_all_list.json")
        if (json != null) {
            try {
                val travelList = gson.fromJson<List<TravelModel>>(json, object : TypeToken<List<TravelModel>>() {}.type)
                Log.d(TAG, "成功从文件加载旅行列表")
                
                // 查找并更新指定 ID 的项目
                val updatedList = travelList.map { item ->
                    if (item.id == id) {
                        item.copy(isBookmark = isBookmark)
                    } else {
                        item
                    }
                }
                
                // 找到更新后的项目
                val updatedItem = updatedList.find { it.id == id }
                if (updatedItem != null) {
                    Log.d(TAG, """
                        更新后的旅行项目详情:
                        ID: ${updatedItem.id}
                        标题: ${updatedItem.title}
                        描述: ${updatedItem.description}
                        城市: ${updatedItem.city}
                        国家: ${updatedItem.country}
                        分类: ${updatedItem.category}
                        书签状态: ${updatedItem.isBookmark}
                        图片数量: ${updatedItem.images.size}
                    """.trimIndent())
                    
                    // 将更新后的列表保存回文件
                    val assetsDir = File(context.getExternalFilesDir(null)?.parentFile?.parentFile?.parentFile?.parentFile, "app/src/main/assets")
                    val file = File(assetsDir, "travel_all_list.json")
                    
                    FileWriter(file).use { writer ->
                        writer.write(gson.toJson(updatedList))
                    }
                    Log.d(TAG, "成功更新 travel_all_list.json 文件")
                    
                    return Response.success(updatedItem)
                } else {
                    Log.d(TAG, "未找到 ID 为 $id 的旅行项目")
                }
            } catch (e: Exception) {
                Log.e(TAG, "更新旅行项目数据失败", e)
            }
        }
        
        Log.d(TAG, "使用默认模拟数据")
        val item = MockData.mockTravelList.first { it.id == id }
        val updatedItem = item.copy(isBookmark = isBookmark)
        return Response.success(updatedItem)
    }
} 