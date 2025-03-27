package com.empedocles.travelapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import android.graphics.drawable.Drawable
import android.util.Log
import android.os.Handler
import android.os.Looper

private const val TAG = "ImageExtension"
private const val PICS_DIR = "pics"

// Helper function to get random image from assets/pics directory
private fun getRandomImagePath(context: Context): String? {
    try {
        val assetManager = context.assets
        val files = assetManager.list(PICS_DIR)
        
        if (files.isNullOrEmpty()) {
            Log.e(TAG, "assets/pics 目录中没有图片文件")
            return null
        }

        // 过滤出图片文件
        val imageFiles = files.filter { 
            it.endsWith(".jpg") || it.endsWith(".jpeg") || it.endsWith(".png") || it.endsWith(".webp")
        }

        if (imageFiles.isEmpty()) {
            Log.e(TAG, "assets/pics 目录中没有支持的图片格式")
            return null
        }

        val randomFile = imageFiles.random()
        val imagePath = "file:///android_asset/$PICS_DIR/$randomFile"
        Log.d(TAG, "随机选择图片: $randomFile")
        return imagePath
    } catch (e: Exception) {
        Log.e(TAG, "获取随机图片失败", e)
        return null
    }
}

// Helper function to download image
fun ImageView.downloadFromUrl(url: String, circularProgressDrawable: CircularProgressDrawable) {
    // 保存 ImageView 引用
    val imageView = this
    
    Glide.with(context)
        .load(url)
        .placeholder(circularProgressDrawable)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e(TAG, "图片加载失败: $url", e)
                
                // 获取随机本地图片路径
                val randomPath = getRandomImagePath(context)
                if (randomPath != null) {
                    Log.d(TAG, "尝试加载随机本地图片: $randomPath")
                    // 使用 Handler 在主线程中加载备用图片
                    Handler(Looper.getMainLooper()).post {
                        Glide.with(context)
                            .load(randomPath)
                            .placeholder(circularProgressDrawable)
                            .into(imageView)
                    }
                } else {
                    Log.e(TAG, "无法获取随机本地图片")
                }
                
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                Log.d(TAG, "图片加载成功: $url")
                return false
            }
        })
        .into(this)
}

// Function to create progress bar
fun circularProgressFactory(context: Context): CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    return circularProgressDrawable
}