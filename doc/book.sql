create database book;

use book;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(200) DEFAULT NULL COMMENT '书名',
  `author` varchar(200) DEFAULT NULL COMMENT '作者',
  `oldname` varchar(200) DEFAULT NULL COMMENT '原名',
  `translator` varchar(200) DEFAULT NULL COMMENT '译者',
  `publishing` varchar(50) DEFAULT NULL COMMENT '出版日期',
  `papers` int(11) DEFAULT NULL COMMENT '页数',
  `money` decimal(7,2) DEFAULT NULL COMMENT '定价',
  `isbn` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------

-- ----------------------------
-- Table structure for `bookscore`
-- ----------------------------
DROP TABLE IF EXISTS `bookscore`;
CREATE TABLE `bookscore` (
  `
scoreid` int(11) NOT NULL,
  `
bookid` int(11) NOT NULL COMMENT '书ID',
  `scorevalue` int(11) DEFAULT NULL COMMENT '分值',
  PRIMARY KEY (`
scoreid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookscore
-- ----------------------------

-- ----------------------------
-- Table structure for `booktarg`
-- ----------------------------
DROP TABLE IF EXISTS `booktarg`;
CREATE TABLE `booktarg` (
  `targid` int(11) NOT NULL COMMENT '标签id',
  `targname` varchar(200) DEFAULT NULL COMMENT '标签内容',
  `bookid` int(11) DEFAULT NULL COMMENT '书ID',
  PRIMARY KEY (`targid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of booktarg
-- ----------------------------

-- ----------------------------
-- Table structure for `booktype`
-- ----------------------------
DROP TABLE IF EXISTS `booktype`;
CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` int(11) DEFAULT NULL,
  `typename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


