/*
 Navicat Premium Data Transfer

 Source Server         : 404z.cn_3306
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 404z.cn:3306
 Source Schema         : networkdisk

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 27/05/2020 18:53:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。用户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名',
  `nameindex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名全文索引',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件描述',
  `pwd` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件密码',
  `length` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文件长度。单位B',
  `timestamp` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '文件上传时间戳',
  `path` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '文件保存路径',
  `md5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '文件MD5',
  `uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '文件下载地址',
  `browsing` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文件浏览次数',
  `download` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文件下载次数',
  `isdelete` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0未删除1已删除',
  `isshare` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0私有1共享',
  `issearch` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0不可搜索1可搜索',
  `islimit` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0不限制下载1限制下载',
  `isfolder` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0文件1文件夹',
  `issubfile` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0文件1子文件',
  `time` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '文件上传时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `timestamp`(`timestamp`) USING BTREE,
  UNIQUE INDEX `uuid`(`uuid`) USING BTREE,
  INDEX `md5`(`md5`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  FULLTEXT INDEX `nameindex`(`nameindex`),
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for folder
-- ----------------------------
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `folderid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。文件夹id',
  `fileid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。文件id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `folderid`(`folderid`) USING BTREE,
  INDEX `fileid`(`fileid`) USING BTREE,
  CONSTRAINT `folder_ibfk_1` FOREIGN KEY (`folderid`) REFERENCES `file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `folder_ibfk_2` FOREIGN KEY (`fileid`) REFERENCES `file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of folder
-- ----------------------------

-- ----------------------------
-- Table structure for history_browsing
-- ----------------------------
DROP TABLE IF EXISTS `history_browsing`;
CREATE TABLE `history_browsing`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。用户id',
  `fileid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。文件id',
  `ip` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'IP地址',
  `time` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  INDEX `fileid`(`fileid`) USING BTREE,
  CONSTRAINT `history_browsing_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `history_browsing_ibfk_2` FOREIGN KEY (`fileid`) REFERENCES `file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history_browsing
-- ----------------------------

-- ----------------------------
-- Table structure for history_download
-- ----------------------------
DROP TABLE IF EXISTS `history_download`;
CREATE TABLE `history_download`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。用户id',
  `fileid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。文件id',
  `ip` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'IP地址',
  `time` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '下载时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  INDEX `fileid`(`fileid`) USING BTREE,
  CONSTRAINT `history_download_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `history_download_ibfk_2` FOREIGN KEY (`fileid`) REFERENCES `file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history_download
-- ----------------------------

-- ----------------------------
-- Table structure for tip_off
-- ----------------------------
DROP TABLE IF EXISTS `tip_off`;
CREATE TABLE `tip_off`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `userid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。用户id',
  `fileid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。文件id',
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `ip` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'IP地址',
  `time` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '时间',
  `status` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态。0未处理1正常2违法',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  INDEX `fileid`(`fileid`) USING BTREE,
  CONSTRAINT `tip_off_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tip_off_ibfk_2` FOREIGN KEY (`fileid`) REFERENCES `file` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tip_off
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userinfoid` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键。用户信息id',
  `account` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `pwd` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `gender` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别。1男0女',
  `year` int(0) UNSIGNED NOT NULL DEFAULT 2000 COMMENT '出生年。默认2000',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `type` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型。0已删除1未注册2管理员3教师4学生',
  `sizeone` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个文件最大容量。单位MB',
  `sizeall` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大容量。单位MB',
  `sizecurrent` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前使用容量。单位MB',
  `time` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  UNIQUE INDEX `userinfoid`(`userinfoid`) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`userinfoid`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 2, 'ROOT', '', 'ROOT', 0, 2000, '', 1, 0, 0, 0, 0);
INSERT INTO `user` VALUES (2, 3, 'ADMIN', '$2a$10$C/hDjMo.S4Ki4LnZuBDnMO94sUuoxiHmGkCvvWQZ2HXmLLkil16Ba', 'ADMIN', 0, 2000, '', 2, 2047, 1024000, 0, 0);
INSERT INTO `user` VALUES (3, 1, 'DELETE', '', 'DELETE', 0, 2000, '', 0, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `number` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '学号或工号',
  `idcard` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '身份证号码',
  `type` tinyint(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型。0已删除1未注册2管理员3教师4学生',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number`(`number`) USING BTREE,
  UNIQUE INDEX `idcard`(`idcard`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'ROOT', '1', '1', 1);
INSERT INTO `user_info` VALUES (2, 'ADMIN', '2', '2', 2);
INSERT INTO `user_info` VALUES (3, 'DELETE', '0', '0', 0);

SET FOREIGN_KEY_CHECKS = 1;
