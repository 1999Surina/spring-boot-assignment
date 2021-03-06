DROP TABLE IF EXISTS `review`;

create table review(
id int primary key AUTO_INCREMENT,
hotels_id int,
username varchar(50),
feedback varchar(300),
foreign key (hotels_id)
	references hotels (id) on delete cascade,
foreign key (username)
	references users (username) on delete cascade
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

