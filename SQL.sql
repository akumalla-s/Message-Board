DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(200) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,  
  `email` varchar(500) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
);

insert into `user` values ('admin','srinivas','akumalla','1234567890',md5("pass"),"sriniadmin@neu.edu","admin");
insert into `user` values ('admin2','srinivas','akumalla','1234567890',md5("pass"),"sriniadmin@neu.edu","admin");
insert into `user` values ('srini','srinivas','akumalla','1234567890',md5("pass"),"sriniuser@neu.edu","user");
insert into `user` values ('test','srinivas','akumalla','1234567890',md5("pass"),"sriniuser@neu.edu","user");
insert into `user` values ('test2','srinivas','akumalla','1234567890',md5("pass"),"sriniuser@neu.edu","user");

select * from user;