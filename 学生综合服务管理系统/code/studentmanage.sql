# Host: localhost  (Version: 5.6.24)
# Date: 2016-09-07 19:56:36
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "college"
#

CREATE TABLE `college` (
  `collegeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `collegeName` varchar(30) NOT NULL,
  PRIMARY KEY (`collegeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "college"
#

INSERT INTO `college` VALUES (1,'计算机学院'),(2,'软件学院');

#
# Structure for table "course"
#

CREATE TABLE `course` (
  `courseID` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(30) NOT NULL,
  `courseType` varchar(10) NOT NULL,
  `credit` int(11) NOT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

#
# Data for table "course"
#

INSERT INTO `course` VALUES (1,'数据结构','必修课',4),(2,'面向对象程序设计','必修课',4),(3,'电路与电子技术基础','必修课',4),(4,'离散数学','必修课',4),(5,'信号与系统','必修课',3),(6,'软件工程概论','必修课',3),(7,'计算机组成与结构','必修课',4),(8,'计算机导论','必修课',3),(9,'线性代数','必修课',3),(10,'概率论与数理统计','必修课',3),(11,'数据库系统概论','必修课',3),(12,'算法设计与分析','必修课',3),(13,'java技术','必修课',3),(14,'操作系统','必修课',4),(15,'编译原理','必修课',4),(16,'软件体系结构','必修课',2),(17,'大学体育','必修课',3),(18,'思想道德修养与法律基础','必修课',3),(19,'马克思主义基本原理','必修课',3),(20,'中国近代史纲要','必修课',2),(21,'大学英语','必修课',5),(22,'形式与政策','必修课',3),(23,'军事理论','必修课',2),(24,'军事训练','必修课',1),(25,'web工程','必修课',5),(26,'毕业设计','必修课',10),(27,'软件过程与项目管理','必修课',4),(28,'软件工程经济学','必修课',2),(29,'知识产权与软件保护','必修课',2),(30,'系统分析与设计','必修课',3),(31,'数据通信与计算机网络','必修课',4),(32,'数据库应用','必修课',3),(33,'web信息搜索','必修课',2),(34,'分布对象技术','必修课',4),(35,'就业指导','必修课',2),(36,'管理经济学','必修课',2),(37,'大学生价值观','选修课',2),(39,'兵学与中国文化','选修课',2),(40,'人际关系心理学','选修课',2),(41,'市场营销学','选修课',2),(42,'人际传播','选修课',2),(43,'演讲与口才','选修课',2),(44,'书法鉴赏','选修课',2),(45,'大学生健康教育','选修课',2),(46,'嵌入式系统设计','选修课',2),(47,'现代电子测量','选修课',1),(48,'电子系统设计','选修课',2),(49,'工程设计','选修课',2),(50,'平面设计','选修课',2),(51,'信息战概论','选修课',1),(52,'物联网技术','选修课',2),(53,'人力资源管理','选修课',2),(54,'国际贸易','选修课',2),(55,'会计学原理','选修课',2),(56,'交互图形与界面设计','选修课',2),(57,'专业集成电路设计','选修课',2);

#
# Structure for table "fee"
#

CREATE TABLE `fee` (
  `feeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `feeName` varchar(30) NOT NULL DEFAULT '',
  `type` varchar(30) NOT NULL DEFAULT '',
  `publishDate` date NOT NULL DEFAULT '0000-00-00',
  `state` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`feeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "fee"
#

INSERT INTO `fee` VALUES (1,'军训费用','按学院+年级','2016-09-07','已发布'),(3,'asdf','按班级','2016-09-07','未发布');

#
# Structure for table "feetype"
#

CREATE TABLE `feetype` (
  `feetypeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `feeID` bigint(20) DEFAULT NULL,
  `collegeID` bigint(20) DEFAULT NULL,
  `specialtyID` bigint(20) DEFAULT NULL,
  `classID` bigint(20) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `value` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`feetypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "feetype"
#

INSERT INTO `feetype` VALUES (1,1,1,NULL,NULL,1,1000),(2,1,2,NULL,NULL,1,1100),(4,3,NULL,NULL,5,NULL,2312);

#
# Structure for table "manager"
#

CREATE TABLE `manager` (
  `managerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL DEFAULT '',
  `managerName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`managerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "manager"
#

INSERT INTO `manager` VALUES (1,'1234','杨杰','000000');

#
# Structure for table "notice"
#

CREATE TABLE `notice` (
  `noticeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `managerID` bigint(20) DEFAULT NULL,
  `title` varchar(30) DEFAULT '',
  `content` varchar(20000) NOT NULL DEFAULT '',
  `publishDate` date DEFAULT NULL,
  PRIMARY KEY (`noticeID`),
  KEY `FK_Reference_49` (`managerID`),
  CONSTRAINT `FK_Reference_49` FOREIGN KEY (`managerID`) REFERENCES `manager` (`managerID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "notice"
#

INSERT INTO `notice` VALUES (1,NULL,'room','<h3 style=\"padding:0 20px;\">为进一步加强对学生宿舍夜不归宿的检查工作，保护学生的人生安全，维护校园稳定，经学院研究决定加强学生宿舍夜不归宿检查力度，在组织学生进行夜不归宿检查的同时，由院、系学办组织辅导员突击检查。现将有关事项通知如下：</h3>\n\t<ul>\n\t\t<li>\n\t\t\t一、各系学办、全体辅导员要高度认识该项工作的重要性。学生在校的安全无小事，通过该工作的检查、落实，本篇文章来自资料管理下载。让辅导员及时了解学生动态，开展有针对性的思想教育工作，切实保证学生在校安全，保证学院正常的教学、生活秩序。\n\t\t</li>\n\t\t<li>\n\t\t\t二、各系以班级为单位通过召开主题班会等形式组织学生学习资料管理下载学院《学生手册》中：学生宿舍管理补充规定的相关内容，教育、引导学生自觉遵守学院相关规定，并主动配合学院的检查工作。\n\t\t</li>\n\t\t<li>三、院学办将强化对夜不归宿检查工作的管理，对参加检查的学生强化工作职责，明确工作内容，要求做到认真检查、文明检查。</li>\n\t\t<li>四、院学办将安排值班A组人员对学生宿舍进行抽查，并将抽查情况报院学办，由院学办通知相关系及辅导员。</li>\n\t\t<li>五、院学办对夜不归宿检查结果每周三、周五进行通报，要求相关辅导员进行情况核实并于三个工作日之内将核实结果反馈至学办。</li>\n\t\t<li>六、对有夜不归宿现象的学生，各系、辅导员须及时了解情况，进行批评教育，经教育不改的学生，按照资料管理下载学院《学生手册》中相应规定给予纪律处分。</li>\n\t</ul>\n\n\n',NULL),(2,NULL,'charge','<h3 style=\"padding:10px 20px;text-indent:2em;\">我校按照国家规定标准确定学费和住宿费标准。依照国家示范性软件学院学费标准，软件工程专业学费为一、二年级6000元/年，三、四年级14000元/年;其他理工类专业学费6000-4950元/年。文史类专业3850元/年。艺术类专业9900元/年。电子信息工程(中外合作办学项目)专业52000元/年。通信工程(中外合作办学项目)专业前三年在国内学习，学费60000元/年，第四年在英国学习，按照英国赫瑞瓦特大学当年的国际学生标准缴纳学费。如果相关主管部门调整本年度本科生学费收费标准，我校将按照新规定收取学费。</h3>\n\n',NULL);

#
# Structure for table "register"
#

CREATE TABLE `register` (
  `registerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `registerDate` date DEFAULT '0000-00-00',
  `notice` varchar(20000) NOT NULL DEFAULT '',
  PRIMARY KEY (`registerID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "register"
#

INSERT INTO `register` VALUES (1,'2016-08-24','<ul>\n        <li>为进一步培养学生良好的生活习惯，加强对学生的生活管理，创造良好的育人环境，促进寝室的规范化、标准化建设，提升宿舍管理水平，制定如下规定：</li>\n        <li>一、宿舍内外必须保持整洁，要求做到：\n            <ul class=\"\">\n                <li>1、学费：6000元／年；</li>\n                <li>2、住宿费：500元/年;</li>\n                <li>3、教材费：2000元(预交第一年教材费，多退少补)。</li>\n                <li>4、根据上级有关文件要求大学生须参加城镇居民医疗保险，应缴纳三年医保费：普招生（含对口生）90元；（3+2）新生60元。</li>\n            </ul>\n        </li>\n        <li>二、严格遵守作息制度，上课及自习不得回宿舍。特殊情况需要回寝，必须有学生科批条。 三、节约用水，水龙头用后立即关好；室内一切设备不得擅自拆装或随意搬到室外；开窗时要挂好窗钩，假日、节日最后离开宿舍时必须关好门窗、电灯，门要上锁；爱护公共财产，损坏公物照价赔偿。 </li>\n        <li>三、各专业学生报到时需交纳以下费用，可登陆新生入学系统在网上缴费或报到时现场缴费：\n            <ul class=\"\">\n                <li>1、各寝要建立值日生制度，每天清扫一次，每周大扫除一次。</li>\n                <li>2、学生起床后必须整理好自己的床铺，叠好被子，统一安放，床单要拉平，床上的物品要整齐。被褥要经常洗涤，保持清洁。</li>\n                <li>3、各种物品要保持清洁，摆放整齐美观，肥皂盒、热水瓶、毛巾、牙刷、杯子、书、鞋子等，按线状排列。</li>\n                <li>4、不准向窗外倒水或乱抛果壳、纸屑。</li>\n                <li>5、人人重视公共场所的清洁卫生，走廊、楼梯等做到不堆积垃圾、无纸屑、无果壳、无痰迹。厕所、盥洗室保持整洁，水槽里不倒杂物。</li>\n            </ul>\n        </li>\n        \n        <li>四、学生必须按舍务教师安排的寝室与铺位就寝，未经学校舍务教师同意，不得自行调换寝室与铺位。 </li>\n        <li>五、学生一律不得留客住宿，有特殊情况必须经宿舍管理教师的同意，不得擅自在宿舍内接待客人。 </li>\n        <li>六、学生携带大件物品出校，须凭宿舍管理人员签发的出门条。 </li>\n        <li>七、学生贵重物品（含钱、包）要妥善保管，不得存放在寝室内，防止失窃。 </li>\n        <li>八、宿舍内不得烧煤油炉，点煤油灯，不准使用大功率电器（电水壶、热得快等），以防失火。 </li>\n    </ul>\n\n\n\n\n\n\n\n\n\n\n\n\n\n');

#
# Structure for table "section"
#

CREATE TABLE `section` (
  `sectionID` bigint(20) NOT NULL AUTO_INCREMENT,
  `managerID` bigint(20) DEFAULT NULL,
  `campus` varchar(20) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`sectionID`),
  KEY `FK_Reference_33` (`managerID`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`managerID`) REFERENCES `manager` (`managerID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "section"
#

INSERT INTO `section` VALUES (1,1,'红河A区','11111111'),(2,1,'红河B区','22222222'),(3,1,'红河C区','33333333'),(4,1,'红河D区','44444444');

#
# Structure for table "building"
#

CREATE TABLE `building` (
  `buildingID` bigint(20) NOT NULL AUTO_INCREMENT,
  `sectionID` bigint(20) DEFAULT NULL,
  `buildingName` varchar(20) NOT NULL,
  PRIMARY KEY (`buildingID`),
  KEY `FK_Reference_34` (`sectionID`),
  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`sectionID`) REFERENCES `section` (`sectionID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "building"
#

INSERT INTO `building` VALUES (1,1,'海棠公寓1'),(2,1,'海棠公寓2'),(3,2,'竹园公寓1'),(4,2,'竹园公寓2'),(5,3,'丁香公寓1'),(6,3,'丁香公寓2'),(7,4,'樱花公寓1'),(8,4,'樱花公寓2');

#
# Structure for table "room"
#

CREATE TABLE `room` (
  `roomID` bigint(20) NOT NULL AUTO_INCREMENT,
  `buildingID` bigint(20) DEFAULT NULL,
  `roomNumber` varchar(10) NOT NULL,
  `type` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `available` int(11) NOT NULL DEFAULT '0',
  `total` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`roomID`),
  KEY `FK_Reference_35` (`buildingID`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`buildingID`) REFERENCES `building` (`buildingID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

#
# Data for table "room"
#

INSERT INTO `room` VALUES (1,1,'101','1',1200,'男',0,8),(2,1,'102','1',1200,'男',0,8),(3,1,'103','1',1200,'男',0,8),(4,1,'104','1',1200,'男',0,8),(5,1,'105','1',1200,'男',0,8),(6,1,'106','1',1200,'男',0,8),(7,1,'201','1',1200,'女',0,8),(8,1,'202','1',1200,'女',0,8),(9,1,'203','1',1200,'女',0,8),(10,1,'204','1',1200,'女',0,8),(11,1,'205','1',1200,'女',0,8),(12,1,'206','1',1200,'女',0,8),(13,2,'101','2',1200,'男',0,8),(14,2,'102','2',1200,'男',0,8),(15,2,'103','2',1200,'男',0,8),(16,2,'104','2',1200,'男',0,8),(17,2,'105','2',1200,'男',0,8),(18,2,'106','2',1200,'男',0,8),(19,2,'201','2',1200,'女',0,8),(20,2,'202','2',1200,'女',0,8),(21,2,'203','2',1200,'女',0,8),(22,2,'204','2',1200,'女',0,8),(23,2,'205','2',1200,'女',0,8),(24,2,'206','2',1200,'女',0,8),(25,3,'101','3',1200,'男',0,8),(26,3,'102','3',1200,'男',0,8),(27,3,'103','3',1200,'男',0,8),(28,3,'104','3',1200,'男',0,8),(29,3,'105','3',1200,'男',0,8),(30,3,'106','3',1200,'男',0,8),(31,3,'201','3',1200,'女',0,8),(32,3,'202','3',1200,'女',0,8),(33,3,'203','3',1200,'女',0,8),(34,3,'204','3',1200,'女',0,8),(35,3,'205','3',1200,'女',0,8),(36,3,'206','3',1200,'女',0,8),(37,4,'101','4',1200,'男',0,8),(38,4,'102','4',1200,'男',0,8),(39,4,'103','4',1200,'男',0,8),(40,4,'104','4',1200,'男',0,8),(41,4,'105','4',1200,'男',0,8),(42,4,'106','4',1200,'男',0,8),(43,4,'201','4',1200,'女',0,8),(44,4,'202','4',1200,'女',0,8),(45,4,'203','4',1200,'女',0,8),(46,4,'204','4',1200,'女',0,8),(47,4,'205','4',1200,'女',0,8),(48,4,'206','4',1200,'女',0,8);

#
# Structure for table "roomscore"
#

CREATE TABLE `roomscore` (
  `roomgradeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `roomID` bigint(20) DEFAULT NULL,
  `managerID` bigint(20) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `scoreDate` date NOT NULL,
  PRIMARY KEY (`roomgradeID`),
  KEY `FK_Reference_47` (`roomID`),
  KEY `FK_Reference_48` (`managerID`),
  CONSTRAINT `FK_Reference_47` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`),
  CONSTRAINT `FK_Reference_48` FOREIGN KEY (`managerID`) REFERENCES `manager` (`managerID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "roomscore"
#

INSERT INTO `roomscore` VALUES (1,1,NULL,99,'2016-09-07'),(2,2,NULL,98,'2016-09-07'),(3,3,NULL,97,'2016-09-07'),(4,4,NULL,55,'2016-09-07'),(5,5,NULL,69,'2016-09-07'),(6,6,NULL,12,'2016-09-07'),(7,7,NULL,99,'2016-09-07'),(8,8,NULL,30,'2016-09-07'),(9,9,NULL,14,'2016-09-07');

#
# Structure for table "maintenance"
#

CREATE TABLE `maintenance` (
  `maintenanceID` bigint(20) NOT NULL AUTO_INCREMENT,
  `roomID` bigint(20) DEFAULT '0',
  `handle` varchar(20) NOT NULL,
  `note` varchar(50) DEFAULT NULL,
  `maintenanceDate` date NOT NULL DEFAULT '0000-00-00',
  `state` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`maintenanceID`),
  KEY `roomID` (`roomID`),
  CONSTRAINT `maintenance_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "maintenance"
#


#
# Structure for table "specialty"
#

CREATE TABLE `specialty` (
  `specialtyID` bigint(20) NOT NULL AUTO_INCREMENT,
  `collegeID` bigint(20) DEFAULT NULL,
  `specialtyName` varchar(30) NOT NULL,
  PRIMARY KEY (`specialtyID`),
  KEY `FK_Reference_57` (`collegeID`),
  CONSTRAINT `FK_Reference_57` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "specialty"
#

INSERT INTO `specialty` VALUES (1,1,'信息工程'),(2,1,'计算机科学与技术'),(3,2,'物联网工程'),(4,2,'软件工程');

#
# Structure for table "studentclass"
#

CREATE TABLE `studentclass` (
  `classID` bigint(20) NOT NULL AUTO_INCREMENT,
  `specialtyID` bigint(20) DEFAULT NULL,
  `classNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`classID`),
  KEY `FK_Reference_58` (`specialtyID`),
  CONSTRAINT `FK_Reference_58` FOREIGN KEY (`specialtyID`) REFERENCES `specialty` (`specialtyID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

#
# Data for table "studentclass"
#

INSERT INTO `studentclass` VALUES (1,1,'130101'),(2,1,'130102'),(3,2,'130201'),(4,2,'130202'),(5,3,'130301'),(6,3,'130302'),(7,4,'130401'),(8,4,'130402'),(9,1,'140101'),(10,1,'140102'),(11,2,'140201'),(12,2,'140202'),(13,3,'140301'),(14,3,'140302'),(15,4,'140401'),(16,4,'140402'),(17,1,'150101'),(18,1,'150102'),(19,2,'150201'),(20,2,'150202'),(21,3,'150301'),(22,3,'150302'),(23,4,'150401'),(24,4,'150402'),(25,1,'160101'),(26,1,'160102'),(27,2,'160201'),(28,2,'160202'),(29,3,'160301'),(30,3,'160302'),(31,4,'160401'),(32,4,'160402');

#
# Structure for table "student"
#

CREATE TABLE `student` (
  `studentID` bigint(20) NOT NULL AUTO_INCREMENT,
  `classID` bigint(20) DEFAULT NULL,
  `roomID` bigint(20) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `identityID` varchar(20) NOT NULL,
  `studentNumber` varchar(20) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `year` varchar(5) NOT NULL,
  `password` varchar(30) NOT NULL DEFAULT '000000',
  PRIMARY KEY (`studentID`),
  KEY `FK_Reference_30` (`classID`),
  KEY `FK_Reference_31` (`roomID`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`classID`) REFERENCES `studentclass` (`classID`),
  CONSTRAINT `FK_Reference_31` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

#
# Data for table "student"
#

INSERT INTO `student` VALUES (1,25,NULL,'杨杰','11110001','1601010001','男','2016','110001'),(2,25,NULL,'李杰','11110002','1601010002','男','2016','110002'),(3,25,NULL,'李烔','11110003','1601010003','男','2016','110003'),(4,25,NULL,'白杨正','11110004','1601010004','男','2016','110004'),(5,25,NULL,'增瑞涵','11110005','1601010005','男','2016','110005'),(6,25,NULL,'李瑞安','11110006','1601010006','男','2016','110006'),(7,25,NULL,'甘东辉','11110007','1601010007','男','2016','110007'),(8,25,NULL,'毛睿康','11110008','1601010008','男','2016','110008'),(9,25,NULL,'邓勇','11110009','1601010009','男','2016','110009'),(10,25,NULL,'涂佳明','11110010','1601010010','男','2016','110010'),(11,25,NULL,'肖少宁','11110011','1601010011','男','2016','110011'),(12,25,NULL,'樊昊','11110012','1601010012','男','2016','110012'),(13,26,NULL,'王力宏','11110021','1601020001','男','2016','110021'),(14,26,NULL,'成龙','11110022','1601020002','男','2016','110022'),(15,26,NULL,'刘亦菲','11110023','1601020003','女','2016','110023'),(16,26,NULL,'刘诗诗','11110024','1601020004','女','2016','110024'),(17,26,NULL,'关晓彤','11110025','1601020005','女','2016','110025'),(18,26,NULL,'李连杰','11110026','1601020006','男','2016','110026'),(19,26,NULL,'释小龙','11110027','1601020007','男','2016','110027'),(20,26,NULL,'哈喽','11110028','1601020008','男','2016','110028'),(21,26,NULL,'张一山','11110029','1601020009','男','2016','110029'),(22,26,NULL,'杨紫','11110030','1601020010','男','2016','110030'),(23,26,NULL,'杨颖','11110031','1601020011','男','2016','110031'),(24,27,NULL,'科比','11110041','1602010001','男','2016','110041'),(25,27,NULL,'詹姆斯','11110042','1602010002','男','2016','110042'),(26,27,NULL,'罗斯','11110043','1602010003','男','2016','110043'),(27,27,NULL,'杜兰特','11110044','1602010004','男','2016','110044'),(28,27,NULL,'韦德','11110045','1602010005','男','2016','110045'),(29,27,NULL,'保罗','11110046','1602010006','男','2016','110046'),(30,27,NULL,'霍华德','11110047','1602010007','男','2016','110047'),(31,27,NULL,'诺维斯基','11110049','1602010008','男','2016','110049'),(32,27,NULL,'威廉姆斯','11110050','1602010009','男','2016','110050'),(33,27,NULL,'纳什','11110052','1602010010','男','2016','110052'),(34,27,NULL,'加索尔','11110048','1602010011','男','2016','110048'),(35,27,NULL,'邓肯','11110051','1602010012','男','2016','110051'),(36,28,NULL,'张怡宁','11110061','1602020001','女','2016','110061'),(37,28,NULL,'邓亚萍','11110062','1602020002','女','2016','110062'),(38,28,NULL,'张继科','11110063','1602020003','男','2016','110063'),(39,28,NULL,'王皓','11110064','1602020004','男','2016','110064'),(40,28,NULL,'马龙','11110065','1602020005','男','2016','110065'),(41,28,NULL,'林丹','11110066','1602020006','男','2016','110066'),(42,28,NULL,'刘国梁','11110067','1602020007','男','2016','110067'),(43,28,NULL,'田亮','11110068','1602020008','男','2016','110068'),(44,28,NULL,'许海峰','11110069','1602020009','男','2016','110069'),(45,28,NULL,'郭跃','11110070','1602020010','男','2016','110070'),(46,28,NULL,'丁宁','11110071','1602020011','男','2016','110071'),(47,28,NULL,'程菲','11110072','1602020012','男','2016','110072'),(48,30,NULL,'路飞','11111001','1603020001','男','2016','111001'),(49,29,NULL,'索隆','11111002','1603010001','男','2016','111002'),(50,29,NULL,'香吉士','11111003','1603010002','男','2016','111003'),(51,29,NULL,'娜美','11111004','1603010003','女','2016','111004'),(52,30,NULL,'黑胡子','11111006','1603020002','男','2016','111006'),(53,30,NULL,'白胡子','11111007','1603020003','男','2016','111007'),(54,30,NULL,'犬夜叉','11111008','1603020004','男','2016','111008'),(55,31,NULL,'柯南','11111009','1604010001','男','2016','111009'),(56,31,NULL,'卡卡西','11111010','1604010002','男','2016','111010'),(57,31,NULL,'鸣人','11111011','1604010003','男','2016','111011'),(58,31,NULL,'佐助','11111012','1604010004','男','2016','111012'),(59,32,NULL,'小樱','11111013','1604020001','女','2016','111013'),(60,32,NULL,'龙猫','11111014','1604020002','男','2016','111014'),(61,17,NULL,'黑崎一护','11112001','1501010001','男','2015','112001'),(62,17,NULL,'更木八剑','11112002','1501010002','男','2015','112002'),(63,18,NULL,'朽木露琪亚','11112003','1501020001','女','2015','112003'),(64,18,NULL,'黑崎一心','11112004','1501020002','男','2015','112004'),(65,19,NULL,'松本乱菊','11112005','1502010001','女','2015','112005'),(66,19,NULL,'冬狮郎','11112006','1502010002','男','2015','112006'),(67,20,NULL,'朽木白哉','11112007','1502020001','男','2015','112007'),(68,21,NULL,'蓝染','11112009','1503010001','男','2015','112009'),(69,22,NULL,' 碎蜂','11112010','1503020001','女','2015','112010'),(70,19,NULL,' 井上织姬','11112011','1502010003','女','2015','112011'),(71,21,NULL,'京乐春水','11112008','1503010002','男','2015','112008'),(72,22,NULL,'夜一','11112012','1503020002','女','2015','112012'),(73,23,NULL,'茶渡泰虎','11112013','1504010001','男','2015','112013'),(74,23,NULL,'东仙要','11112014','1504010002','男','2015','112014'),(75,24,NULL,'阿散井恋次','11112015','1504020001','男','2015','112015'),(76,24,NULL,'雏森桃','11112016','1504020002','女','2015','112016'),(77,9,NULL,'罗杰','11113001','1401010001','男','2014','113001'),(78,9,NULL,'卡普','11113002','1401010002','男','2014','113002'),(79,10,NULL,'龙','11113003','1401020001','男','2014','113003'),(80,10,NULL,'凯多','11113004','1401020002','男','2014','113004'),(81,11,NULL,'赤犬','11113005','1402010001','男','2014','113005'),(82,11,NULL,' 藤虎','11113006','1402010002','男','2014','113006'),(83,12,NULL,' 青稚','11113007','1402020001','男','2014','113007'),(84,12,NULL,'战国','11113008','1402020002','男','2014','113008'),(85,13,NULL,'雷利','11113009','1403010001','男','2014','113009'),(86,13,NULL,'黄猿','11113010','1403010002','男','2014','113010'),(87,14,NULL,'马尔科','11113011','1403020001','男','2014','113011'),(88,14,NULL,'明哥','11113012','1403020002','男','2014','113012'),(89,15,NULL,'鹰眼','11110013','1404010001','男','2014','110013'),(90,15,NULL,'罗','11113013','1404010002','男','2014','113013'),(91,15,NULL,'熊','11113014','1404010003','男','2014','113014'),(92,16,NULL,'萨博','11113015','1404020001','男','2014','113015'),(93,16,NULL,'艾尼路','11113016','1404020002','男','2014','113016');

#
# Structure for table "application"
#

CREATE TABLE `application` (
  `applicationID` bigint(20) NOT NULL AUTO_INCREMENT,
  `studentID` bigint(20) DEFAULT NULL,
  `reason` varchar(50) NOT NULL,
  `beginDate` date NOT NULL,
  `endDate` date NOT NULL DEFAULT '0000-00-00',
  `state` varchar(10) NOT NULL,
  PRIMARY KEY (`applicationID`),
  KEY `FK_Reference_45` (`studentID`),
  CONSTRAINT `FK_Reference_45` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "application"
#


#
# Structure for table "pay"
#

CREATE TABLE `pay` (
  `payID` bigint(20) NOT NULL AUTO_INCREMENT,
  `studentID` bigint(20) DEFAULT NULL,
  `feetypeID` bigint(20) DEFAULT NULL,
  `value` int(11) NOT NULL DEFAULT '0',
  `payDate` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`payID`),
  KEY `FK_Reference_56` (`studentID`),
  KEY `feetypeID` (`feetypeID`),
  CONSTRAINT `FK_Reference_56` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`),
  CONSTRAINT `pay_ibfk_1` FOREIGN KEY (`feetypeID`) REFERENCES `feetype` (`feetypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "pay"
#

INSERT INTO `pay` VALUES (1,1,1,1000,'2016-09-07');

#
# Structure for table "teacher"
#

CREATE TABLE `teacher` (
  `teacherID` bigint(20) NOT NULL AUTO_INCREMENT,
  `teacherName` varchar(30) NOT NULL,
  `teacherNumber` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL DEFAULT '000000',
  `identityID` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`teacherID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

#
# Data for table "teacher"
#

INSERT INTO `teacher` VALUES (1,'钱学森','000001','220001','22220001'),(2,'爱因斯坦','000002','000002','000002'),(3,'冯诺依曼','000003','000003','000003'),(4,'雅思','000004','220004','22220004'),(5,'托福','000005','220005','22220005'),(6,'樊志起','000006','220006','22220006'),(7,'颜行书','000007','220007','22220007'),(8,'朱玲玲','000008','220008','22220008'),(9,'吴小莉','000009','220009','22220009'),(10,'陈熙琼','000010','220010','22220010'),(11,'相马茜','000011','220011','22220011'),(12,'吴晴帆','000012','220012','22220012'),(13,'邢成国','000013','220013','22220013'),(14,'邓春华','000014','220014','22220014'),(15,'简易豪','000015','220015','22220015'),(16,'吴倩国','000016','220016','22220016');

#
# Structure for table "teach"
#

CREATE TABLE `teach` (
  `teachID` bigint(20) NOT NULL AUTO_INCREMENT,
  `courseID` bigint(20) DEFAULT NULL,
  `teacherID` bigint(20) DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`teachID`),
  KEY `FK_Reference_42` (`courseID`),
  KEY `FK_Reference_43` (`teacherID`),
  CONSTRAINT `FK_Reference_42` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`teacherID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

#
# Data for table "teach"
#

INSERT INTO `teach` VALUES (6,1,2,0),(7,12,2,0),(8,21,4,0),(9,21,5,0),(10,8,3,0),(11,7,3,0),(12,13,3,0),(14,37,6,0),(15,39,6,0),(16,40,7,0),(17,41,7,0),(18,42,8,0),(19,43,8,0),(20,44,9,0),(21,45,9,0),(22,46,10,0),(23,47,10,0),(24,48,2,0),(25,49,2,0),(26,56,2,0),(27,57,2,0),(28,40,2,0),(29,41,2,0),(32,48,11,0),(33,49,11,0),(34,50,12,0),(35,51,12,0),(36,52,13,0),(37,53,13,0),(38,54,14,0),(39,55,14,0),(40,56,15,0),(41,57,15,0);

#
# Structure for table "choosing"
#

CREATE TABLE `choosing` (
  `choosingID` bigint(20) NOT NULL AUTO_INCREMENT,
  `studentID` bigint(20) DEFAULT NULL,
  `teachID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`choosingID`),
  KEY `FK_Reference_32` (`studentID`),
  KEY `FK_Reference_44` (`teachID`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`),
  CONSTRAINT `FK_Reference_44` FOREIGN KEY (`teachID`) REFERENCES `teach` (`teachID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "choosing"
#


#
# Structure for table "specialtycourse"
#

CREATE TABLE `specialtycourse` (
  `specialtyCourseID` bigint(20) NOT NULL AUTO_INCREMENT,
  `teachID` bigint(20) DEFAULT NULL,
  `specialtyID` bigint(20) DEFAULT NULL,
  `term` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`specialtyCourseID`),
  KEY `FK_Reference_61` (`specialtyID`),
  KEY `teachID` (`teachID`),
  CONSTRAINT `FK_Reference_61` FOREIGN KEY (`specialtyID`) REFERENCES `specialty` (`specialtyID`),
  CONSTRAINT `specialtycourse_ibfk_1` FOREIGN KEY (`teachID`) REFERENCES `teach` (`teachID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "specialtycourse"
#

INSERT INTO `specialtycourse` VALUES (1,6,1,1),(2,7,1,1);

#
# Structure for table "achievement"
#

CREATE TABLE `achievement` (
  `achievementID` bigint(20) NOT NULL AUTO_INCREMENT,
  `studentID` bigint(20) DEFAULT NULL,
  `courseID` bigint(20) DEFAULT NULL,
  `teacherID` bigint(20) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `term` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`achievementID`),
  KEY `FK_Reference_62` (`studentID`),
  KEY `teacherID` (`teacherID`),
  KEY `courseID` (`courseID`),
  CONSTRAINT `FK_Reference_62` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`),
  CONSTRAINT `achievement_ibfk_1` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`teacherID`),
  CONSTRAINT `achievement_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "achievement"
#

INSERT INTO `achievement` VALUES (1,1,1,2,100,1),(2,2,1,2,60,1),(3,3,1,2,59,1),(4,4,1,2,99,1),(5,5,1,2,99,1),(6,6,1,2,88,1);
