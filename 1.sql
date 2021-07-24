CREATE DATABASE 机票售卖系统;

USE 机票售卖系统;

CREATE TABLE admin(
	azh varchar(50) NULL,
	apwd varchar(50) NULL
);

CREATE TABLE fly(
	jid int auto_increment NOT NULL PRIMARY KEY,
	jjg float NULL,
	jx varchar(50) NULL,
	wlx varchar(50) NULL,
	hbid int NULL,
	sj varchar(50) NULL,
	spd varchar(50) NULL,
	jtwz varchar(50) NULL
);

CREATE TABLE hb(
	hbid int auto_increment NOT NULL PRIMARY KEY,
	hbname varchar(50) NULL,
	dj varchar(50) NULL,
	gsmc varchar(50) NULL
);

CREATE TABLE ordera(
	oid int auto_increment NOT NULL PRIMARY KEY,
	uida int NULL,
	jid int NULL,
	gmsj varchar(50) NULL
);

CREATE TABLE usera(
	uida int auto_increment NOT NULL PRIMARY KEY,
	uname varchar(50) NULL,
	upwd varchar(50) NULL
);

alter table fly add constraint fk_hbid foreign key(hbid) references hb(hbid) on delete cascade;

alter table ordera add constraint fk_uida foreign key(uida) references usera(uida) on delete cascade;

alter table fly add constraint df_jg default(0) for jjg;

create procedure checkadmin(name varchar(50),pwd varchar(50))
begin
	select * from admin where azh=name and apwd=pwd;
end;

create procedure updateFly(jg float,jxa varchar(50),wlxa varchar(50),jtwza varchar(50),jida int)
begin
	update fly set jjg=jg,jx=jxa,wlx=wlxa,jtwz=jtwza where jid=jida;
end;

create procedure addFly(jg float,jxa varchar(50),wlxa varchar(50),hbida int,sja varchar(50),spda varchar(50),jtwza varchar(50))
begin
	insert into fly(jjg,jx,wlx,hbid,sj,spd,jtwz) values(jg,jxa,wlxa,hbida,sja,spda,jtwza);
end;

create procedure getFly(jida int)
begin
	select * from fly where jid=jida;
end;

create procedure deleteFly(jida int)
begin
	delete from fly where jid=jida;
end;

create procedure updateHb(hbnamea varchar(50),dja varchar(50),hbida int)
begin
	update hb set hbname=hbnamea,dj=dja where hbid=hbida;
end;

create procedure addHb(hbnamea varchar(50),dja varchar(50),gsmca varchar(50))
begin
	insert into hb(hbname,dj,gsmc) values(hbnamea,dja,gsmca);
end;

create procedure deleteHb(hbida int)
begin
	delete from hb where hbid=hbida;
end;

create procedure getHb(hbida int)
begin
	select * from hb where hbid=hbida;
end;

create procedure addOrder(uidaa int,jida int,gmsj varchar(50))
begin
	insert into ordera(uida,jid,gmsj) values(uidaa,jida,gmsj);
end;

create procedure getAllOrder()
begin
	select * from ordera;
end;

create procedure getAllOrderByuid(id int)
begin
	select * from ordera where uida=id;
end;

create procedure checkUser(name varchar(50),pwd varchar(50))
begin
	select * from usera where uname=name and upwd=pwd;
end;

create procedure addUser(unamea varchar(50),upwda varchar(50))
begin
	insert into usera(uname,upwd) values(unamea,upwda);
end;
