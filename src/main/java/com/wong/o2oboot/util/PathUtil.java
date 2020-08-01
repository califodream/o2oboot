package com.wong.o2oboot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathUtil {

	private static String seperator = System.getProperty("file.separator");

	private static String winPath;

	private static String linuxPath;

	private static String shopPath;

	private static String shopCategoryPath;

	private static String headLinePath;
	
	private static String watermarkPath;

	@Value("${win.base.path}")
	public void setWinPath(String winPath) {
		PathUtil.winPath = winPath;
	}

	@Value("${linux.base.path}")
	public void setLinuxPath(String linuxPath) {
		PathUtil.linuxPath = linuxPath;
	}

	@Value("${shop.path}")
	public void setShopPath(String shopPath) {
		PathUtil.shopPath = shopPath;
	}

	@Value("${shopcategory.path}")
	public void setShopCategoryPath(String shopCategoryPath) {
		PathUtil.shopCategoryPath = shopCategoryPath;
	}

	@Value("${headline.path}")
	public void setHeadLinePath(String headLinePath) {
		PathUtil.headLinePath = headLinePath;
	}

	@Value("${watermark.path}")
	public void setWatermarkPath(String watermarkPath) {
		PathUtil.watermarkPath = watermarkPath;
	}

	/**
	 * 
	 * @return
	 */
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = winPath;
		} else {
			basePath = linuxPath;
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	/**
	 * 
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId) {
		String imagePath = shopPath + shopId + "/";
		return imagePath.replace("/", seperator);
	}

	public static String getProductImagePath(long shopId) {
		String imagePath = shopPath + shopId + "/product/";
		return imagePath.replace("/", seperator);
	}

	public static String getHeadLineImagePath() {
		String imagePath = headLinePath;
		return imagePath.replace("/", seperator);
	}

	public static String getShopCategoryPath() {
		String imagePath = shopCategoryPath;
		return imagePath.replace("/", seperator);
	}
	
	public static String getWatermarkPath() {
		String imagePath = getImgBasePath() + watermarkPath;
		System.out.println(imagePath);
		return imagePath.replace("/", seperator);
	}
}
