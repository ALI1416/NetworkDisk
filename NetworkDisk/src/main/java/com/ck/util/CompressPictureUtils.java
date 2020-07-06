package com.ck.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import com.ck.constant.Constant;

public class CompressPictureUtils {
	public static void CompressPicture(String sourceImagePath, String destinationImagePath) {
		try {
			/* 过滤小于64kb和大于64mb的图片 */
			File sourceImageFile = new File(sourceImagePath);
			File destinationImageFile = new File(destinationImagePath);
			long sourceImageSize = sourceImageFile.length();
			if (sourceImageSize == 0) {
				throw new Exception("empty");
			}
			if (sourceImageSize < Constant.PICTURE_COMPRESS_MIN) {
				throw new Exception("small");
			}
			if (sourceImageSize > Constant.PICTURE_COMPRESS_MAX) {
				throw new Exception("large");
			}
			/* 设置图片大小 */
			BufferedImage inputImage = ImageIO.read(sourceImageFile);
			int sourceImageWidth = inputImage.getWidth();
			int sourceImageHeight = inputImage.getHeight();
			int destinationImageWidth = sourceImageWidth;
			int destinationImageHeight = sourceImageHeight;
			if (sourceImageWidth > Constant.PICTURE_COMPRESS_WIDTH_MAX
					|| sourceImageHeight > Constant.PICTURE_COMPRESS_HEIGHT_MAX) {
				if (sourceImageWidth > sourceImageHeight) {
					destinationImageWidth = Constant.PICTURE_COMPRESS_WIDTH_MAX;
					destinationImageHeight = destinationImageWidth * sourceImageHeight / sourceImageWidth;
				} else {
					destinationImageHeight = Constant.PICTURE_COMPRESS_HEIGHT_MAX;
					destinationImageWidth = destinationImageHeight * sourceImageWidth / sourceImageHeight;
				}
			}
			/* 压缩图片 */
			Image image = inputImage.getScaledInstance(destinationImageWidth, destinationImageHeight,
					Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(destinationImageWidth, destinationImageHeight,
					BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			ImageIO.write(outputImage, "jpg", destinationImageFile);
			/* 过滤比原图小8kb以下的的图片 */
			long destinationImageSize = destinationImageFile.length();
			if (destinationImageSize > (sourceImageSize - Constant.PICTURE_COMPRESS_DIFFER_MIN)) {
				throw new Exception("low");
			}
		} catch (Exception e) {
			try {
				String msg = e.getMessage();
				if (msg == "small" || msg == "low") {// 过小或压缩率过低，保存原图
					File sourceImageFile = new File(sourceImagePath);
					File destinationImageFile = new File(destinationImagePath);
					destinationImageFile.delete();
					Files.copy(sourceImageFile.toPath(), destinationImageFile.toPath());
				} else {// 空或过大或异常，保存空图
					File emptyfile = new File(destinationImagePath);
					emptyfile.delete();
					emptyfile.createNewFile();
				}
			} catch (Exception e1) {
			}
		}
	}

	public static void main(String[] args) {
		CompressPicture("D:\\1.png", "D:\\00.jpg");
	}

}
