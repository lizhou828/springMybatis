/*
SQLyog Ultimate v8.71 
MySQL - 5.0.45-community-nt : Database - money_note
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`money_note` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `money_note`;

/*Table structure for table `classmate` */

DROP TABLE IF EXISTS `classmate`;

CREATE TABLE `classmate` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `work_unit` varchar(50) NOT NULL COMMENT '工作单位',
  `job` varchar(20) NOT NULL COMMENT '职务',
  `telephone` varchar(20) default NULL COMMENT '固定电话',
  `mobile_phone` varchar(20) default NULL COMMENT '手机号码',
  `qq_no` bigint(20) default NULL COMMENT 'qq号码',
  `weixin_no` varchar(50) default NULL COMMENT '微信号',
  `add_time` int(10) default NULL COMMENT '添加时间',
  `class_name` varchar(20) default NULL COMMENT '班级全称',
  `user_agent` varchar(300) default NULL COMMENT 'user_agent',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `classmate` */

insert  into `classmate`(`id`,`name`,`work_unit`,`job`,`telephone`,`mobile_phone`,`qq_no`,`weixin_no`,`add_time`,`class_name`,`user_agent`) values (1,'asdfas','asdfsasd','fasdf','','13412341234',0,'',NULL,NULL,NULL),(2,'asdfasasd','asdfsasdasd','fasdfas','','13412341234',0,'',NULL,'09英语教育301班','Mozilla/5.0 (Windows'),(3,'asdfasasd','asdfsasdasd','fasdfas','','13412341234',0,'',0,'09英语教育301班','Mozilla/5.0 (Windows'),(4,'asdfasasd阿萨德发生的发生','asdfsasdasd asd','fasdfas ','','13412341234',0,'',1427810245,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36'),(5,'SDf','asdf','asdf','','13112341234',0,'',1427810617,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36'),(6,'SDf','asdf','asdf','','13112341234',0,'',1427810678,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36'),(7,'SDf','asdf啊飒飒的水电费','asdf','','13112341234',0,'',1427811255,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36'),(8,'asdfsdf','asdfasdf','sadfasdf','','13212341234',0,'',1427813289,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36'),(9,'asdf','asdf','asdf','','13112311234',0,'',1427813392,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36'),(10,'asdf','asdf','asdf','','13112311234',0,'',1427813407,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36'),(11,'asdf','asdf','asdf','','13112311234',0,'',1427815491,'09英语教育301班','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36');

/*Table structure for table `mn_cate` */

DROP TABLE IF EXISTS `mn_cate`;

CREATE TABLE `mn_cate` (
  `id` int(10) NOT NULL auto_increment,
  `cate_name` varchar(255) NOT NULL,
  `parent_id` int(10) NOT NULL,
  `sort_order` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mn_cate` */

/*Table structure for table `mn_item` */

DROP TABLE IF EXISTS `mn_item`;

CREATE TABLE `mn_item` (
  `id` int(10) NOT NULL auto_increment,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `money` double(10,2) default NULL COMMENT '流动金额',
  `type` int(2) NOT NULL default '0' COMMENT '类型:0表示支出，1表示收入',
  `cate_id` int(10) default NULL COMMENT '类目id',
  `cate_name` varchar(255) default NULL COMMENT '类目名称',
  `add_time` datetime default NULL COMMENT '新增时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mn_item` */

/*Table structure for table `mn_login_log` */

DROP TABLE IF EXISTS `mn_login_log`;

CREATE TABLE `mn_login_log` (
  `id` int(10) NOT NULL auto_increment,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `user_name` varchar(64) default NULL COMMENT '用户名',
  `login_ip` varchar(15) default NULL COMMENT '登陆ip',
  `login_time` datetime default NULL COMMENT '登陆时间',
  `referer` text COMMENT '请求的referer',
  `device_type` varchar(128) default NULL COMMENT '设备类型',
  `browser_type` varchar(128) default NULL COMMENT '浏览器类型',
  `login_url` varchar(128) default NULL COMMENT '登陆的url',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mn_login_log` */

/*Table structure for table `mn_money_sum` */

DROP TABLE IF EXISTS `mn_money_sum`;

CREATE TABLE `mn_money_sum` (
  `id` int(10) NOT NULL auto_increment,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `income` double(10,2) default NULL COMMENT '总收入',
  `pay` double(10,2) default NULL COMMENT '总支出',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mn_money_sum` */

/*Table structure for table `mn_user` */

DROP TABLE IF EXISTS `mn_user`;

CREATE TABLE `mn_user` (
  `id` int(10) NOT NULL auto_increment,
  `username` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `drop_state` int(2) NOT NULL default '0' COMMENT '0表示正常',
  `create_time` datetime default NULL,
  `mobile_phone` varchar(20) default NULL,
  `email` varchar(128) default NULL,
  `qq_no` varchar(32) default NULL COMMENT 'QQ号',
  `qq_open_id` varchar(64) default NULL COMMENT 'QQ open id',
  `weixin_no` varchar(32) default NULL COMMENT '微信号',
  `weixin_open_id` varchar(64) default NULL COMMENT '微信open id',
  `t_id` int(10) default NULL COMMENT '推荐人id',
  `extra_type` int(1) default NULL COMMENT '0表示为本站手动注册用户，1为QQ注册用户，2为微信登陆用户',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `mn_user` */

insert  into `mn_user`(`id`,`username`,`password`,`drop_state`,`create_time`,`mobile_phone`,`email`,`qq_no`,`qq_open_id`,`weixin_no`,`weixin_open_id`,`t_id`,`extra_type`) values (1,'admin','123456',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `quanxian` */

DROP TABLE IF EXISTS `quanxian`;

CREATE TABLE `quanxian` (
  `id` int(10) NOT NULL,
  `user_id` int(10) default NULL,
  `classmate_table` varchar(500) default NULL,
  `a_table` varchar(500) default NULL,
  `b_table` varchar(500) default NULL,
  `c_table` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `quanxian` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `name` varchar(32) NOT NULL default '',
  `email` varchar(64) default NULL,
  `id` int(32) NOT NULL auto_increment,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`name`,`email`,`id`) values ('asdf','asdf',1),('asdfasdfasd','asdfasdf',2),('hill','hill@123.com',3),('ksjdfk','你好',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
