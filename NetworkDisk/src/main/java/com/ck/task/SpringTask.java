package com.ck.task;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ck.constant.Constant;
import com.ck.util.DateUtils;
import com.ck.util.FileUtils;
import com.ck.util.StringUtils;

@Service
@EnableScheduling
public class SpringTask {
	private final static Logger logger = LoggerFactory.getLogger(SpringTask.class);

	public static void initial() {
		logger.info("初始化任务开始...");
		createFolder();
		createLibrary();
		ansj();
		logger.info("初始化任务结束。");
	}

	@Scheduled(cron = "0 0 0 ? * SUN") // 每周日，凌晨执行
	public static void deleteFile() {
		logger.info("定时计划：删除临时文件...");
		Long deleteDay = DateUtils.getTimestamp() - (3 * 24 * 60 * 60 * 1000);// 7天前
		File[] files = new File(Constant.FILE_TEMP_PATH).listFiles();
		logger.trace("扫描到" + files.length + "个文件");
		for (File file : files) {
			Long fileTime = file.lastModified();
			if (fileTime - deleteDay < 0) {
				logger.trace("删除文件: " + file.getName());
				file.delete();
			}
		}
		logger.info("定时计划：临时文件删除完成。");
	}

	public static void ansj() {
		logger.info("ansj分词工具开始加载...");
		logger.trace(StringUtils.getAnsj("ansj分词工具加载完成。"));
		logger.info("ansj分词工具加载结束。");
	}

	public static void createFolder() {
		logger.info("检查并创建文件夹...");
		File fileSavePath = new File(Constant.FILE_SAVE_PATH);
		File filetempPath = new File(Constant.FILE_TEMP_PATH);
		if (!fileSavePath.exists()) {
			logger.trace("保存文件文件夹FILE_SAVE_PATH缺失，创建在" + Constant.FILE_SAVE_PATH);
			fileSavePath.mkdir();
		}
		if (!filetempPath.exists()) {
			logger.trace("临时文件文件夹FILE_TEMP_PATH缺失，创建在" + Constant.FILE_TEMP_PATH);
			filetempPath.mkdir();
		}
		logger.info("检查并创建文件夹结束。");
	}

	public static void createLibrary() {
		try {
			logger.info("检查并创建词典文件...");
			File library = new File("C:/library/");
			File _DoNotDeleteTxt = new File("C:/library/_DoNotDelete.txt");
			File ambiguityDic = new File("C:/library/ambiguity.dic");
			File defaultDic = new File("C:/library/default.dic");
			if (!library.exists()) {
				library.mkdir();
				logger.trace("词典文件夹library缺失，创建在" + library.getPath());
			}
			if (!_DoNotDeleteTxt.exists()) {
				ClassPathResource _DoNotDeleteTxtR = new ClassPathResource("static/library/_DoNotDelete.txt");
				InputStream _DoNotDeleteTxtI = _DoNotDeleteTxtR.getInputStream();
				FileUtils.inputStream2File(_DoNotDeleteTxtI, _DoNotDeleteTxt.getPath());
				logger.trace("请勿删除文件_DoNotDeleteTxt缺失，创建在" + _DoNotDeleteTxt.getPath());
			}
			if (!ambiguityDic.exists()) {
				ClassPathResource ambiguityDicR = new ClassPathResource("static/library/ambiguity.dic");
				InputStream ambiguityDicI = ambiguityDicR.getInputStream();
				FileUtils.inputStream2File(ambiguityDicI, ambiguityDic.getPath());
				logger.trace("词典文件ambiguity.dic缺失，创建在" + ambiguityDic.getPath());
			}
			if (!defaultDic.exists()) {
				ClassPathResource defaultDicR = new ClassPathResource("static/library/default.dic");
				InputStream defaultDicI = defaultDicR.getInputStream();
				FileUtils.inputStream2File(defaultDicI, defaultDic.getPath());
				logger.trace("词典文件default.dic缺失，创建在" + defaultDic.getPath());
			}
			logger.info("检查并创建词典文件结束。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] s) {
//		deleteFile();
//		createFolder();
		createLibrary();
//		ansj();
	}
}
