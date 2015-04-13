/*************************************************************************
 *  
 *  Copyright (C) 2013 SuZhou Raipeng Information Technology co., LTD.
 * 
 *                       All rights reserved.
 *
 *************************************************************************/
package com.raipeng.appAB.common;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.raipeng.appAB.R;

/**
 * @date: 2013-11-4 上午9:27:24
 * 
 * @email: tchen@raipeng.com
 * 
 * @version: V1.0
 * 
 * @description:
 * 
 */
public class ImageLoaderConfig {
	public static DisplayImageOptions initDisplayOptions() {
		DisplayImageOptions.Builder displayImageOptionsBuilder = new DisplayImageOptions.Builder();
		displayImageOptionsBuilder.imageScaleType(ImageScaleType.EXACTLY);
		displayImageOptionsBuilder.cacheInMemory(true);
		displayImageOptionsBuilder.cacheOnDisc(true);
		displayImageOptionsBuilder.considerExifParams(true);
		displayImageOptionsBuilder.showImageForEmptyUri(R.drawable.ic_launcher);
		displayImageOptionsBuilder.showImageOnFail(R.drawable.ic_launcher);
		displayImageOptionsBuilder.showImageOnLoading(R.drawable.ic_launcher);
		displayImageOptionsBuilder.showStubImage(R.drawable.ic_launcher);
		displayImageOptionsBuilder.bitmapConfig(Bitmap.Config.RGB_565);
		displayImageOptionsBuilder.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2);
		

		return displayImageOptionsBuilder.build();
	}

	public static DisplayImageOptions initDisplayOptions(int targetWidth) {
		DisplayImageOptions.Builder displayImageOptionsBuilder = new DisplayImageOptions.Builder();
		displayImageOptionsBuilder.imageScaleType(ImageScaleType.EXACTLY);
		displayImageOptionsBuilder.cacheInMemory(true);
		displayImageOptionsBuilder.cacheOnDisc(true);
		displayImageOptionsBuilder.showImageForEmptyUri(R.drawable.ic_launcher);
		displayImageOptionsBuilder.showImageOnFail(R.drawable.ic_launcher);
		displayImageOptionsBuilder.showStubImage(R.drawable.ic_launcher);
		displayImageOptionsBuilder.bitmapConfig(Bitmap.Config.RGB_565);
//		displayImageOptionsBuilder.displayer(new SimpleImageDisplayer(targetWidth));

		return displayImageOptionsBuilder.build();
	}
	
	public static DisplayImageOptions initRounderDisplayOptions(int radius) {
		DisplayImageOptions.Builder displayImageOptionsBuilder = new DisplayImageOptions.Builder();
		displayImageOptionsBuilder.imageScaleType(ImageScaleType.EXACTLY);
		displayImageOptionsBuilder.cacheInMemory(true);
		displayImageOptionsBuilder.cacheOnDisc(true);
		displayImageOptionsBuilder.showImageForEmptyUri(R.drawable.ic_launcher);
		displayImageOptionsBuilder.showImageOnFail(R.drawable.ic_launcher);
		displayImageOptionsBuilder.showStubImage(R.drawable.ic_launcher);
		displayImageOptionsBuilder.bitmapConfig(Bitmap.Config.RGB_565);
		displayImageOptionsBuilder.displayer(new RoundedBitmapDisplayer(radius));

		return displayImageOptionsBuilder.build();
	}

	public static void initImageLoader(Context context, String cacheDisc) {
		// 配置ImageLoader
		// 获取本地缓存的目录，该目录在SDCard的根目录下
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, cacheDisc);
		// 实例化Builder
		ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context);
		// 设置线程数量
		builder.threadPoolSize(3);
		// 设定线程等级比普通低一点
		builder.threadPriority(Thread.NORM_PRIORITY);
		// 设定内存缓存为弱缓存，默认是强引用
		builder.memoryCache(new WeakMemoryCache());
		// 设定内存图片缓存大小限制，不设置默认为屏幕的宽高
		builder.memoryCacheExtraOptions(480, 800);
		// 设定只保存同一尺寸的图片在内存
		builder.denyCacheImageMultipleSizesInMemory();
		// 设定缓存的SDcard目录，UnlimitDiscCache速度最快
		builder.discCache(new UnlimitedDiscCache(cacheDir));
		// 设定缓存到SDCard目录的文件命名
		builder.discCacheFileNameGenerator(new HashCodeFileNameGenerator()); 
		// 设定网络连接超时 timeout: 10s 读取网络连接超时read timeout: 60s
		builder.imageDownloader(new BaseImageDownloader(context, 10000, 60000));
		// 设置ImageLoader的配置参数
		builder.defaultDisplayImageOptions(initDisplayOptions());
		//队列机制
		builder.tasksProcessingOrder(QueueProcessingType.LIFO);
		//打印log信息
		builder.writeDebugLogs();
		// 初始化ImageLoader
		ImageLoader.getInstance().init(builder.build());
	}

	
//	public static void initImageLoader(Context context,String cacheDisc) {
//		// 获取本地缓存的目录，该目录在SDCard的根目录下
//		File cacheDir = StorageUtils.getOwnCacheDirectory(context, cacheDisc);
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.diskCache(new UnlimitedDiscCache(cacheDir))
//				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
//				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.writeDebugLogs() // Remove for release app
//				.build();
//		// Initialize ImageLoader with configuration.
//		ImageLoader.getInstance().init(config);
//	}
	
}
