CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `real_name` varchar(30) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `d_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_department` (
  `did` int NOT NULL,
  `d_name` varchar(30) DEFAULT NULL,
  `d_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
