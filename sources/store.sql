-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1:3306
-- 生成日期： 2022-01-02 16:12:16
-- 服务器版本： 8.0.21
-- PHP 版本： 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `store`
--

-- --------------------------------------------------------

--
-- 表的结构 `applicationlist`
--

DROP TABLE IF EXISTS `applicationlist`;
CREATE TABLE IF NOT EXISTS `applicationlist` (
  `iApplicationId` int NOT NULL AUTO_INCREMENT,
  `iMedicineId` int NOT NULL,
  `iQuantityOfApplication` int DEFAULT NULL,
  `dDateOfApplication` datetime NOT NULL,
  `bStateOfApplication` enum('0','1') NOT NULL DEFAULT '0',
  `iPresentQuantity` int DEFAULT NULL,
  PRIMARY KEY (`iApplicationId`),
  KEY `iMedicineId` (`iMedicineId`)
) ENGINE=MyISAM AUTO_INCREMENT=8004 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `applicationlist`
--

INSERT INTO `applicationlist` (`iApplicationId`, `iMedicineId`, `iQuantityOfApplication`, `dDateOfApplication`, `bStateOfApplication`, `iPresentQuantity`) VALUES
(8001, 7001, 200, '2021-12-01 19:37:12', '1', 0),
(8002, 7003, 200, '2021-12-01 19:37:12', '0', 0);

-- --------------------------------------------------------

--
-- 表的结构 `genera`
--

DROP TABLE IF EXISTS `genera`;
CREATE TABLE IF NOT EXISTS `genera` (
  `iMedicineGeneraId` int NOT NULL AUTO_INCREMENT,
  `vMedicineGeneraName` varchar(50) NOT NULL,
  `vMedicineDescription` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`iMedicineGeneraId`),
  UNIQUE KEY `vMedicineGeneraName` (`vMedicineGeneraName`)
) ENGINE=MyISAM AUTO_INCREMENT=5026 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `genera`
--

INSERT INTO `genera` (`iMedicineGeneraId`, `vMedicineGeneraName`, `vMedicineDescription`) VALUES
(5000, '西药', '西药即为有机化学药品，无机化学药品和生物制品.'),
(5001, '中药', '中药大部分是天然药物，有效成分比较复杂，如生物碱、皂素、鞣酸质，挥发油等.'),
(5013, '保健药', '没有任何治疗功能,可以增强人的体质,增加人的抵抗能力.'),
(5014, '非处方药', '可以不经过医生的处方,直接出售给病人的药品.'),
(5015, '处方药认为', '病人必须持有医生的处方单才可以购买的药品.');

-- --------------------------------------------------------

--
-- 表的结构 `inputlist`
--

DROP TABLE IF EXISTS `inputlist`;
CREATE TABLE IF NOT EXISTS `inputlist` (
  `iInputListId` int NOT NULL AUTO_INCREMENT,
  `dInputDate` datetime NOT NULL,
  PRIMARY KEY (`iInputListId`)
) ENGINE=MyISAM AUTO_INCREMENT=9034 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `inputlist`
--

INSERT INTO `inputlist` (`iInputListId`, `dInputDate`) VALUES
(9020, '2009-04-17 00:00:00'),
(9021, '2009-04-17 00:00:00'),
(9022, '2009-04-18 00:00:00'),
(9023, '2009-04-18 00:00:00'),
(9024, '2009-04-18 00:00:00'),
(9025, '2009-04-18 00:00:00'),
(9033, '2022-01-03 00:11:39');

-- --------------------------------------------------------

--
-- 表的结构 `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
CREATE TABLE IF NOT EXISTS `manufacturer` (
  `iFacturerId` int NOT NULL AUTO_INCREMENT,
  `vFacturerName` varchar(50) NOT NULL,
  `cContanctMan` char(10) NOT NULL,
  `cContanctPhone` char(15) NOT NULL,
  `cContanctAddress` varchar(100) NOT NULL,
  `vDescription` varchar(500) DEFAULT NULL,
  `cPostalCode` char(6) NOT NULL,
  `cSimplifiedCode` char(20) NOT NULL,
  `vBusinessScope` varchar(250) NOT NULL,
  PRIMARY KEY (`iFacturerId`),
  UNIQUE KEY `vFacturerName` (`vFacturerName`)
) ENGINE=MyISAM AUTO_INCREMENT=4051 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `manufacturer`
--

INSERT INTO `manufacturer` (`iFacturerId`, `vFacturerName`, `cContanctMan`, `cContanctPhone`, `cContanctAddress`, `vDescription`, `cPostalCode`, `cSimplifiedCode`, `vBusinessScope`) VALUES
(4000, '西南制药厂', '王强', '13409345682', '四川省成都市一环路2段49号', '主要生产阵痛止疼类药物', '610000', 'xnzyc', '主要提供治疗过敏，神经性药品'),
(4005, '南瓜制药', '南瓜', '12345678910', '四川省成都市一环路XXXXXXXXXX', 'jfladlfjadfj啊地方了afljadslfadfl艾斯黛拉', '610000', 'ngzy', '挨打发的解放东方建安;撒砥砺风节adfl暗地里放adlfj'),
(4006, '冬瓜制药', '冬瓜', '76543214567', '四川省成都市二环路XXXXXXXXXX', '阿道夫阿道夫安抚', '610000', 'dgzy', 'adf ad lkjf ;afkjf龙卷风挨打aflkad分离的房间挨打死了开放就挨打死了卡的解放;辣的房间大连国际啊的攻击啊第四浪费噶打死就看了个安德森'),
(4009, '华东制药', '华南', '15901928472', '四川省成都市三环路XXXXXXXXXX', '提供保健类药品', '610000', 'hdzy', '脑白金,黄金搭档,'),
(4010, '江中制药', '江中', '1903857382', '四川省成都市XXXXXXXXXX', '专治胃病', '610000', 'jzzy', '江中牌健胃消食片'),
(4011, '哈尔滨制药六厂', 'XXXX', '01099828483', '哈尔滨XXXXX路', '广告很讨厌', '610000', 'hylc', '盖中盖,蓝瓶的好喝');

-- --------------------------------------------------------

--
-- 表的结构 `medicine`
--

DROP TABLE IF EXISTS `medicine`;
CREATE TABLE IF NOT EXISTS `medicine` (
  `iMedicineId` int NOT NULL AUTO_INCREMENT,
  `vMedicineName` varchar(30) NOT NULL,
  `vMedicineDescription` varchar(255) NOT NULL,
  `mRetailPrices` decimal(5,2) DEFAULT NULL,
  `vMedicineStandard` varchar(100) NOT NULL,
  `vMedicineDosage` varchar(100) NOT NULL,
  `iFacturerId` int NOT NULL,
  `iMedicineGeneraId` int NOT NULL,
  PRIMARY KEY (`iMedicineId`),
  KEY `iFacturerId` (`iFacturerId`),
  KEY `iMedicineGeneraId` (`iMedicineGeneraId`)
) ENGINE=MyISAM AUTO_INCREMENT=7022 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `medicine`
--

INSERT INTO `medicine` (`iMedicineId`, `vMedicineName`, `vMedicineDescription`, `mRetailPrices`, `vMedicineStandard`, `vMedicineDosage`, `iFacturerId`, `iMedicineGeneraId`) VALUES
(7001, '阿司匹林', '可缓解轻度或中度的疼痛，如腰疼，头疼等', '20.00', '2板*20粒', '成人1-2片/次/天，儿童1片/次/天', 4000, 5000),
(7002, '盖中盖', '补钙不在话下...绝对一流', '400.00', '片状', '15mg/片，儿童1片/次/天', 4011, 5013),
(7003, '黄金搭档', '过节送礼用.....广告特烦人', '300.00', '瓶', '500ml/瓶', 4009, 5013),
(7004, '江中牌健胃消食片', '专治胃病', '434.00', '12片/版', '15mg/片', 4010, 5014),
(7005, '999复方感冒灵颗粒', '感冒灵丹妙药', '34.00', '15袋/盒', '14g/袋', 4006, 5015);

-- --------------------------------------------------------

--
-- 表的结构 `medicineinput`
--

DROP TABLE IF EXISTS `medicineinput`;
CREATE TABLE IF NOT EXISTS `medicineinput` (
  `iInputListId` int NOT NULL,
  `iMedicineId` int NOT NULL,
  `inputQuantity` int NOT NULL,
  `iFacturerId` int DEFAULT NULL,
  `iSupplierId` int DEFAULT NULL,
  `dDateOfProduction` datetime DEFAULT NULL,
  `dDateOfExpiry` datetime DEFAULT NULL,
  `mInputPrice` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`iInputListId`,`iMedicineId`),
  KEY `iFacturerId` (`iFacturerId`),
  KEY `iSupplierId` (`iSupplierId`),
  KEY `iMedicineId` (`iMedicineId`)
) ENGINE=MyISAM DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `medicineinput`
--

INSERT INTO `medicineinput` (`iInputListId`, `iMedicineId`, `inputQuantity`, `iFacturerId`, `iSupplierId`, `dDateOfProduction`, `dDateOfExpiry`, `mInputPrice`) VALUES
(9033, 7002, 20, 4000, 3001, '2020-05-06 00:00:00', '2023-05-06 00:00:00', '25.05');

-- --------------------------------------------------------

--
-- 表的结构 `medicineoutput`
--

DROP TABLE IF EXISTS `medicineoutput`;
CREATE TABLE IF NOT EXISTS `medicineoutput` (
  `iOutputId` int NOT NULL,
  `iMedicineId` int NOT NULL,
  `iQuantity` int NOT NULL,
  `iPharmacyId` int NOT NULL,
  PRIMARY KEY (`iOutputId`,`iMedicineId`),
  KEY `iPharmacyId` (`iPharmacyId`),
  KEY `iMedicineId` (`iMedicineId`)
) ENGINE=MyISAM AUTO_INCREMENT=9313 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `medicineoutput`
--

INSERT INTO `medicineoutput` (`iOutputId`, `iMedicineId`, `iQuantity`, `iPharmacyId`) VALUES
(9310, 7001, 100, 6001),
(9311, 7001, 100, 6001),
(9311, 7003, 233, 6002),
(9312, 7003, 123, 6003),
(9315, 7002, 34, 6001);

-- --------------------------------------------------------

--
-- 表的结构 `muser`
--

DROP TABLE IF EXISTS `muser`;
CREATE TABLE IF NOT EXISTS `muser` (
  `iUserId` int NOT NULL AUTO_INCREMENT,
  `vUserName` varchar(20) NOT NULL,
  `vUserPass` varchar(20) NOT NULL,
  `vUserRealName` varchar(20) NOT NULL,
  `iTypeId` int DEFAULT NULL,
  PRIMARY KEY (`iUserId`),
  UNIQUE KEY `vUserName` (`vUserName`),
  KEY `iTypeId` (`iTypeId`)
) ENGINE=MyISAM AUTO_INCREMENT=2050 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `muser`
--

INSERT INTO `muser` (`iUserId`, `vUserName`, `vUserPass`, `vUserRealName`, `iTypeId`) VALUES
(2001, 'zj', 'zj', '张静', 1001),
(2006, 'hm', 'hm', '黄铭', 1000),
(2012, 'lyy', 'lyy', '刘宇阳', 1002),
(2013, 'zy', 'zy', '张燕', 1001),
(2020, 'admin', 'admin', '王晓钱', 1000),
(2021, 'admin1', 'admin1', '王晓钱', 1002);

-- --------------------------------------------------------

--
-- 表的结构 `musertype`
--

DROP TABLE IF EXISTS `musertype`;
CREATE TABLE IF NOT EXISTS `musertype` (
  `iTypeId` int NOT NULL AUTO_INCREMENT,
  `cTypeName` char(20) NOT NULL,
  PRIMARY KEY (`iTypeId`),
  UNIQUE KEY `cTypeName` (`cTypeName`)
) ENGINE=MyISAM AUTO_INCREMENT=1064 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `musertype`
--

INSERT INTO `musertype` (`iTypeId`, `cTypeName`) VALUES
(1001, '库房管理员'),
(1000, '系统管理员'),
(1002, '业务审核员');

-- --------------------------------------------------------

--
-- 表的结构 `outputlist`
--

DROP TABLE IF EXISTS `outputlist`;
CREATE TABLE IF NOT EXISTS `outputlist` (
  `iOutputListId` int NOT NULL AUTO_INCREMENT,
  `dOutputDate` datetime NOT NULL,
  PRIMARY KEY (`iOutputListId`)
) ENGINE=MyISAM AUTO_INCREMENT=9316 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `outputlist`
--

INSERT INTO `outputlist` (`iOutputListId`, `dOutputDate`) VALUES
(9308, '2009-04-17 00:00:00'),
(9309, '2009-04-17 00:00:00'),
(9310, '2009-04-17 00:00:00'),
(9311, '2009-04-17 00:00:00'),
(9312, '2009-04-17 00:00:00'),
(9315, '2022-01-02 21:50:30');

-- --------------------------------------------------------

--
-- 表的结构 `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
CREATE TABLE IF NOT EXISTS `pharmacy` (
  `iPharmacyId` int NOT NULL AUTO_INCREMENT,
  `vPharmacyName` varchar(50) NOT NULL,
  `vPharmacyDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`iPharmacyId`),
  UNIQUE KEY `vPharmacyName` (`vPharmacyName`)
) ENGINE=MyISAM AUTO_INCREMENT=6008 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `pharmacy`
--

INSERT INTO `pharmacy` (`iPharmacyId`, `vPharmacyName`, `vPharmacyDescription`) VALUES
(6001, '西药房', '用于存放西药的仓库'),
(6002, '中药房', '用于存放中药的仓库'),
(6003, '保健品药房', '存放保健类药品');

-- --------------------------------------------------------

--
-- 表的结构 `storeroom`
--

DROP TABLE IF EXISTS `storeroom`;
CREATE TABLE IF NOT EXISTS `storeroom` (
  `iSupplierId` int NOT NULL,
  `iMedicineId` int NOT NULL,
  `iMinimumStorage` int DEFAULT NULL,
  `iPresentQuantity` int DEFAULT NULL,
  PRIMARY KEY (`iSupplierId`,`iMedicineId`),
  KEY `iMedicineId` (`iMedicineId`)
) ENGINE=MyISAM DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `storeroom`
--

INSERT INTO `storeroom` (`iSupplierId`, `iMedicineId`, `iMinimumStorage`, `iPresentQuantity`) VALUES
(3000, 7003, 50, 10644),
(3000, 7005, 100, 0),
(3002, 7001, 100, 0),
(3002, 7004, 100, 0);

-- --------------------------------------------------------

--
-- 表的结构 `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE IF NOT EXISTS `supplier` (
  `iSupplierId` int NOT NULL AUTO_INCREMENT,
  `vSupplierName` varchar(100) NOT NULL,
  `cContanctMan` char(10) NOT NULL,
  `cContanctPhone` char(15) NOT NULL,
  `vContanctAddress` varchar(100) NOT NULL,
  `vDescription` varchar(500) DEFAULT NULL,
  `cPostalCode` char(6) NOT NULL,
  `cSimplifiedCode` char(20) NOT NULL,
  `vBusinessScope` varchar(200) NOT NULL,
  PRIMARY KEY (`iSupplierId`)
) ENGINE=MyISAM AUTO_INCREMENT=3008 DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `supplier`
--

INSERT INTO `supplier` (`iSupplierId`, `vSupplierName`, `cContanctMan`, `cContanctPhone`, `vContanctAddress`, `vDescription`, `cPostalCode`, `cSimplifiedCode`, `vBusinessScope`) VALUES
(3000, '西南药房', '王强', '13369345682', '新疆乌鲁木齐', '提供感冒类药品的供货商', '831000', 'xnyf', '主要经营阵痛退烧类药品'),
(3001, '明生药房', '明张生', '18097547321', '四川绵阳XXXXXXX', '提供保健类药品', '610301', 'msyf', '脑白金 黄金搭档');

-- --------------------------------------------------------

--
-- 表的结构 `ullagelist`
--

DROP TABLE IF EXISTS `ullagelist`;
CREATE TABLE IF NOT EXISTS `ullagelist` (
  `iUllageListId` int NOT NULL,
  `iMedicineId` int DEFAULT NULL,
  `iPresentQuantity` int DEFAULT NULL,
  PRIMARY KEY (`iUllageListId`),
  KEY `iMedicineId` (`iMedicineId`)
) ENGINE=MyISAM DEFAULT CHARSET=gb2312;

--
-- 转存表中的数据 `ullagelist`
--

INSERT INTO `ullagelist` (`iUllageListId`, `iMedicineId`, `iPresentQuantity`) VALUES
(9527, 7005, 0),
(9528, 7001, 0),
(9529, 7004, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
