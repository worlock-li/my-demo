/*
SQLyog Ultimate v8.32 
MySQL - 5.5.61 : Database - my_project_vue
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_project_vue` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `my_project_vue`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `department` */

insert  into `department`(`id`,`name`) values (3,'财务部'),(4,'商务部'),(8,'技术部'),(10,'行政部'),(11,'哈哈部');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(31) NOT NULL,
  `user_name` varchar(15) NOT NULL,
  `user_sex` int(1) NOT NULL,
  `user_age` int(4) NOT NULL,
  `user_birthday` datetime NOT NULL,
  `user_department` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(31) DEFAULT NULL,
  `update_by` varchar(31) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `deleted` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_sex`,`user_age`,`user_birthday`,`user_department`,`create_time`,`update_time`,`create_by`,`update_by`,`version`,`deleted`) values ('1','张三',1,20,'2010-02-12 00:00:00',2,NULL,NULL,'tom',NULL,1,0),('2','李四',2,22,'2010-03-02 00:00:00',3,NULL,NULL,'jiny',NULL,1,0),('3','王五',1,12,'2018-03-05 00:00:00',1,NULL,NULL,'王五委',NULL,1,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
