/*
 Navicat Premium Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : cirs

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 08/05/2021 16:22:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `uid` int NOT NULL COMMENT '用户编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `sex` int NOT NULL COMMENT '性别：0，男；1，女',
  `birthday` date NOT NULL COMMENT '出生日期',
  `depart_id` int NULL DEFAULT NULL COMMENT '部门编号',
  `clazz_id` int NULL DEFAULT NULL COMMENT '学院班级号',
  `role` int NOT NULL COMMENT '角色：0，管理员；1，教职工；2，学生',
  `position` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `u_status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '用户状态：0，未登录；1，登录',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (102400101, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, '2015-06-10', 1024001, 0, 0, '管理员', 0);
INSERT INTO `account` VALUES (123000101, '梅林', 'e10adc3949ba59abbe56e057f20f883e', 1, '1973-06-01', 1230001, 0, 1, '行政老师', 0);
INSERT INTO `account` VALUES (123000201, '郝聪', 'e10adc3949ba59abbe56e057f20f883e', 0, '1972-01-20', 1230002, 0, 1, '宣传老师', 0);
INSERT INTO `account` VALUES (179000720, '张三', 'e10adc3949ba59abbe56e057f20f883e', 0, '1998-08-14', 0, 1790007, 2, '学生', 0);
INSERT INTO `account` VALUES (179000746, '李四', 'e10adc3949ba59abbe56e057f20f883e', 1, '1998-04-17', 0, 1790007, 2, '学生', 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `uid` int NOT NULL COMMENT '用户编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `sex` int NOT NULL COMMENT '性别：0，男；1，女',
  `birthday` date NOT NULL COMMENT '出生日期',
  `depart_id` int NULL DEFAULT NULL COMMENT '部门编号',
  `role` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色：0，管理员；1，教职工；2，学生',
  `position` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `u_status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '用户状态：0，未登录；1，登录',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `uid` int NOT NULL COMMENT '评论的用户编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `info_id` int NOT NULL COMMENT '信息编号',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '这是一条测试评论1', 179000746, '李四', 1);
INSERT INTO `comment` VALUES (2, '这是一条测试评论2', 179000746, '李四', 2);
INSERT INTO `comment` VALUES (3, '这是一条测试评论3', 179000720, '张三', 1);
INSERT INTO `comment` VALUES (4, '这是一条测试评论4', 179000720, '张三', 2);

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info`  (
  `info_id` int NOT NULL AUTO_INCREMENT COMMENT '信息编号',
  `info_title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '信息标题',
  `info_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '信息内容',
  `release_time` date NOT NULL COMMENT '发布时间',
  `uid` int NOT NULL COMMENT '用户编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `info_status` int NOT NULL DEFAULT 0 COMMENT '审核状态：0，初始化；1，通过；2，未通过',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES (1, '我校举办第七届“互联网+”大学生创新创业大赛启动仪式', '本网讯 3月31日，第七届中国国际“互联网+”大学生创新创业大赛筹备工作启动仪式暨参赛讲座在南泉校区第六教学楼302举行。校长助理简玉刚、各二级学院创新创业工作分管领导、各系（教研室）负责学生技能竞赛的老师与拟参赛的师生代表近350人参加了本次启动仪式。\r\n\r\n仪式上，创新创业学院副院长兼校企合作办副主任徐先航首先发表致辞，他对往届为“互联网+”大赛艰苦奋斗和默默奉献的师生团队表达了感谢，并向全校师生发出了积极参加第七届 “互联网+”大赛的号召。管理学院的学生代表彭渝从参赛者角度分享了自己的参赛经历，软件学院的优秀青年教师赵商从指导教师角度分享了与学生共同备赛的经验。', '2018-06-10', 123000101, '梅林', 1);
INSERT INTO `info` VALUES (2, '我校举办首届ISW教学技能工作培训活动', '本网讯 3月31日-4月3日间，由教师发展中心开办的“ISW教学技能工作坊”在教发中心培训室（图书馆智慧教室）正式运营。本次特邀加拿大ISW国际咨询委员会认证引导员（FDW）、重庆大学教授徐健教授、副教授雷蕾莅临授课，工作坊训练安排周密，学习训练强度高，在经过了为期4天的紧张学习后，来自不同学院的6位骨干教师顺利结业，并获得ISW项目授权的研修证书。', '2019-05-26', 123000201, '郝聪', 1);
INSERT INTO `info` VALUES (3, '人事处举办全校职能部门工作目标管理培训交流会', '本网讯 为深入贯彻学校董事会推行的“目标管理”模式，以提高工作效率、调动员工积极性、适应高质量发展，4月15日上午，人事处（教师发展中心）在行政楼阶梯报告厅举办了全校职能部门工作目标管理培训交流会。本次培训交流会由副校长王琼花主持，学校全体职能部门工作人员、各二级学院绩效工作负责人、各部门中干和办公室主任参加。', '2021-04-17', 123000201, '郝聪', 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `uid` int NOT NULL COMMENT '用户编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `sex` int NOT NULL COMMENT '性别：0，男；1，女',
  `birthday` date NOT NULL COMMENT '出生日期',
  `clazz_id` int NULL DEFAULT NULL COMMENT '学院班级号',
  `role` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '2' COMMENT '角色：0，管理员；1，教职工；2，学生',
  `position` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `u_status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '用户状态：0，未登录；1，登录',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `uid` int NOT NULL COMMENT '用户编号',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `sex` int NOT NULL COMMENT '性别：0，男；1，女',
  `birthday` date NOT NULL COMMENT '出生日期',
  `depart_id` int NULL DEFAULT NULL COMMENT '部门编号',
  `role` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '角色：0，管理员；1，教职工；2，学生',
  `position` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `u_status` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '用户状态：0，未登录；1，登录',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
