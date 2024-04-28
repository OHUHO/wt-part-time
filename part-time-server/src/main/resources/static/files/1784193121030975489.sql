-- ----------------------------
-- Table structure for business_info
-- ----------------------------
DROP TABLE IF EXISTS `business_info`;
CREATE TABLE `business_info` (
  `business_id` varchar(64) NOT NULL COMMENT '商家ID',
  `username` varchar(255) DEFAULT NULL COMMENT '商家真实姓名',
  `gender` varchar(1) DEFAULT NULL COMMENT '性别：1男，2女，3未知',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `card_no` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `store_type` varchar(20) DEFAULT NULL COMMENT '注册店铺类型',
  `store_name` varchar(255) DEFAULT NULL COMMENT '注册店铺名称',
  `papers_type` varchar(20) DEFAULT NULL COMMENT '证件类型',
  `papers_number` varchar(255) DEFAULT NULL COMMENT '证件号码',
  `papers_img` varchar(1000) DEFAULT NULL COMMENT '证件链接地址集合',
  `status` int DEFAULT '1' COMMENT '当前状态：1待审核，2审核通过，3未通过',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人Id',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(64) DEFAULT NULL COMMENT '最后一次更新人id',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '最后一次更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `pass_user_id` varchar(64) DEFAULT NULL COMMENT '审核人ID',
  `pass_user_name` varchar(255) DEFAULT NULL COMMENT '审核人姓名',
  `pass_time` datetime DEFAULT NULL COMMENT '审核通过时间',
  PRIMARY KEY (`business_id`)
);

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
  `carousel_id` varchar(64) NOT NULL COMMENT '轮播图ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `carousel_url` varchar(500) DEFAULT NULL COMMENT '轮播图地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  PRIMARY KEY (`carousel_id`)
);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` varchar(64) NOT NULL COMMENT '评论ID',
  `experience_id` varchar(64) DEFAULT NULL COMMENT '经验ID',
  `from_user_id` varchar(64) DEFAULT NULL COMMENT '评论的用户ID',
  `from_user_name` varchar(255) DEFAULT NULL COMMENT '评论的用户姓名',
  `to_user_id` varchar(64) DEFAULT NULL COMMENT '被评论的用户ID',
  `to_user_name` varchar(255) DEFAULT NULL COMMENT '被评论的用户姓名',
  `content` text COMMENT '评论内容',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  `status` int DEFAULT '2' COMMENT '状态；1待审核，2审核通过，3未审核',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `update_user_id` varchar(64) DEFAULT NULL COMMENT '最后一次更新人ID',
  `update_user_time` varchar(255) DEFAULT NULL COMMENT '最后一次更新人姓名',
  PRIMARY KEY (`comment_id`)
);

-- ----------------------------
-- Table structure for experience
-- ----------------------------
DROP TABLE IF EXISTS `experience`;
CREATE TABLE `experience` (
  `experience_id` varchar(64) NOT NULL COMMENT '经验id',
  `name` varchar(255) DEFAULT NULL COMMENT '经验名称',
  `content` text COMMENT '经验内容',
  `types` varchar(1000) DEFAULT NULL COMMENT '经验类型',
  `good` bigint DEFAULT '0' COMMENT '点赞数量',
  `status` int DEFAULT '1' COMMENT '状态：1待审核，2审核通过，3未通过',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `update_user_id` varchar(64) DEFAULT NULL COMMENT '最后一次更新人ID',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '最后一次更新人姓名',
  PRIMARY KEY (`experience_id`)
);

-- ----------------------------
-- Table structure for experience_type
-- ----------------------------
DROP TABLE IF EXISTS `experience_type`;
CREATE TABLE `experience_type` (
  `type_id` varchar(64) NOT NULL COMMENT '经验种类ID',
  `name` varchar(100) DEFAULT NULL COMMENT '种类名称',
  `status` int DEFAULT '1' COMMENT '状态：1使用中，禁用',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人Id',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次时间',
  `update_user_id` varchar(64) DEFAULT NULL COMMENT '最后一次更新人ID',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '最后一次更新人姓名',
  PRIMARY KEY (`type_id`)
);

-- ----------------------------
-- Table structure for job_info
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `job_id` varchar(64) NOT NULL COMMENT '兼职ID',
  `business_id` varchar(64) DEFAULT NULL COMMENT '商家ID',
  `name` varchar(500) DEFAULT NULL COMMENT '兼职名称',
  `content` text COMMENT '兼职内容',
  `cover` varchar(255) DEFAULT NULL COMMENT '兼职封面',
  `type_id` varchar(64) DEFAULT NULL COMMENT '兼职类型ID',
  `salary` decimal(10,2) DEFAULT NULL COMMENT '薪水',
  `salary_type` varchar(1) DEFAULT NULL COMMENT '薪水类型：1小时，2天，3周，4月，5次',
  `pay_type` varchar(1) DEFAULT NULL COMMENT '结算方式：1日结，2周结，3月结，4完工结',
  `gender` varchar(1) DEFAULT '3' COMMENT '性别需求：1男，2女，3不限',
  `count` int DEFAULT NULL COMMENT '招聘人数',
  `registered` int DEFAULT '0' COMMENT '已报名人数',
  `begin_time` datetime DEFAULT NULL COMMENT '工作开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '工作结束时间',
  `status` int DEFAULT '1' COMMENT '状态：1待审核，2审核通过，3未通过',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '兼职发布时间',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `update_user_id` varchar(64) DEFAULT NULL COMMENT '最后一次更新人ID',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '最后一次更新人姓名',
  PRIMARY KEY (`job_id`)
);

-- ----------------------------
-- Table structure for job_type
-- ----------------------------
DROP TABLE IF EXISTS `job_type`;
CREATE TABLE `job_type` (
  `type_id` varchar(64) NOT NULL COMMENT '兼职类型ID',
  `type_name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `description` text COMMENT '兼职类型描述',
  `cover` varchar(500) DEFAULT NULL COMMENT '兼职封面',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  PRIMARY KEY (`type_id`)
);

-- ----------------------------
-- Table structure for registration_sheet
-- ----------------------------
DROP TABLE IF EXISTS `registration_sheet`;
CREATE TABLE `registration_sheet` (
  `registration_id` varchar(64) NOT NULL COMMENT 'ID',
  `job_id` varchar(64) DEFAULT NULL COMMENT '兼职ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户ID',
  `regist_time` datetime DEFAULT NULL COMMENT '兼职报名时间',
  `finish` int DEFAULT NULL COMMENT '是否参加：1参加，2未参加',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `update_user_id` varchar(64) DEFAULT NULL COMMENT '最后一次更新人ID',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '最后一次更新人姓名',
  PRIMARY KEY (`registration_id`) USING BTREE
);

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews` (
  `reviews_id` varchar(64) NOT NULL COMMENT '兼职评价表ID',
  `job_id` varchar(64) DEFAULT NULL COMMENT '兼职ID',
  `user_id` varchar(64) DEFAULT NULL COMMENT '评价用户ID',
  `user_name` varchar(255) DEFAULT NULL COMMENT '评价用户姓名',
  `point` int DEFAULT NULL COMMENT '评分；1，2，3，4，5',
  `content` text COMMENT '评价内容',
  `additional` text COMMENT '附加材料',
  `status` int DEFAULT '2' COMMENT '评论状态：1待审核，2通过，3未通过',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `update_user_id` varchar(64) DEFAULT NULL COMMENT '最后一次更新人ID',
  `update_user_name` varchar(255) DEFAULT NULL COMMENT '最后一次更新人姓名',
  PRIMARY KEY (`reviews_id`)
);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(64) NOT NULL COMMENT '用户ID',
  `open_id` varchar(255) DEFAULT NULL COMMENT '小程序唯一标识',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `gender` varchar(1) DEFAULT '3' COMMENT '性别：1男，2女，3未知',
  `identity` varchar(1) DEFAULT '1' COMMENT '身份：1用户，2商家',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `point` decimal(10,2) DEFAULT '0.00' COMMENT '积分或余额',
  `fans` bigint DEFAULT '0' COMMENT '粉丝数量',
  `love` bigint DEFAULT '0' COMMENT '关注数量',
  `good` bigint DEFAULT '0' COMMENT '获赞数量',
  `status` int DEFAULT '1' COMMENT '用户状态：1正常，2封禁',
  `logic_delete` int DEFAULT '0' COMMENT '逻辑删除字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
);

