create table exam (
	id char(5) not null,
	examname varchar(6) not null,
	primary key (id)
);

create table ampm (
	ampmid int(1) not null,
	ampmname varchar(2),
	primary key (ampmid)
);

create table user (
	userID char(16) not null,
	password char(64) not null,
	email varchar(255) not null,
	username varchar(8) not null,
	name varchar(15) not null,
	phoneticname varchar(30) not null,
	birthday int(8) not null,
	sq varchar(20) not null,
	primary key(userID)
);

create table score(
	id char(5) not null,
	userID char(16) not null,
	examname varchar(6) not null,
	amscore int(3),
	pmscore int(3),
	totalscore int as (amscore + pmscore),
	primary key (id, userID),
	foreign key (id)
	references exam (id),
	foreign key (userID)
	references user (userID)
);

create table examansweram (
	id char(5) not null,
	ampmid int(1) not null,
	answer char(80) not null,
	examname varchar(6) not null,
	primary key (id, ampmid),
	foreign key (id)
	references exam (id),
	foreign key (ampmid)
	references ampm (ampmid)
);

create table examanswerpm (
	id char(5) not null,
	ampmid int(1) not null,
	examname varchar(6) not null,
	exam1 char(20) not null,
	exam2 char(20) not null,
	exam3 char(20) not null,
	exam4 char(20) not null,
	exam5 char(20) not null,
	exam6 char(20) not null,
	exam7 char(20) not null,
	exam8 char(20) not null,
	exam9 char(20) not null,
	exam10 char(20) not null,
	exam11 char(20) not null,
	exam12 char(20) not null,
	exam13 char(20) not null,
	primary key (id, ampmid),
	foreign key (id)
	references exam (id),
	foreign key (ampmid)
	references ampm (ampmid)
);

create table exampm(
	id char(5) not null,
	exam1 varchar(30) not null,
	exam2 varchar(30) not null,
	exam3 varchar(30) not null,
	exam4 varchar(30) not null,
	exam5 varchar(30) not null,
	exam6 varchar(30) not null,
	exam7 varchar(30) not null,
	exam8 varchar(30) not null,
	exam9 varchar(30) not null,
	exam10 varchar(30) not null,
	exam11 varchar(30) not null,
	exam12 varchar(30) not null,
	exam13 varchar(30) not null,
	primary key (id),
	foreign key (id)
	references exam (id)
);

create table answerpm (
	id char(5) not null,
	exam1 char(20) not null,
	exam2 char(20) not null,
	exam3 char(20) not null,
	exam4 char(20) not null,
	exam5 char(20) not null,
	exam6 char(20) not null,
	exam7 char(20) not null,
	exam8 char(20) not null,
	exam9 char(20) not null,
	exam10 char(20) not null,
	exam11 char(20) not null,
	exam12 char(20) not null,
	exam13 char(20) not null,
	primary key (id),
	foreign key (id)
	references exam (id)
);

create table historyofexam (
	no int auto_increment,
	userID char(16) not null,
	id char(5) not null,
	ampmid int(1) not null,
	score int(3),
	time datetime not null,
	sorf varchar(3) not null,
	primary key (no),
	foreign key (userID)
	references user (userID),
	foreign key (id)
	references exam (id),
	foreign key (ampmid)
	references ampm (ampmid)
);
