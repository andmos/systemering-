CREATE SCHEMA `healthy catering`;

CREATE TABLE `healthy catering`.role ( 
	role                 VARCHAR( 40 )  NOT NULL  ,
	CONSTRAINT pk_role PRIMARY KEY ( role )
 ) engine=InnoDB;

ALTER TABLE `healthy catering`.role COMMENT 'the role of a user, admin, user, salesperson etc';

CREATE TABLE `healthy catering`.inventory ( 
	inventory_id         INT  NOT NULL  AUTO_INCREMENT,
	CONSTRAINT pk_inventory PRIMARY KEY ( inventory_id )
 ) engine=InnoDB;

CREATE TABLE `healthy catering`.user ( 
	id                   INT  NOT NULL  ,
	address              VARCHAR( 100 )    ,
	CONSTRAINT pk_user PRIMARY KEY ( id )
 ) engine=InnoDB;

ALTER TABLE `healthy catering`.user MODIFY id INT  NOT NULL   COMMENT 'the id of a user';

ALTER TABLE `healthy catering`.user MODIFY address VARCHAR( 100 )     COMMENT 'the users address';

CREATE TABLE `healthy catering`.order ( 
	order_id             INT  NOT NULL  AUTO_INCREMENT,
	CONSTRAINT pk_order PRIMARY KEY ( order_id )
 ) engine=InnoDB;

ALTER TABLE `healthy catering`.order MODIFY order_id INT  NOT NULL  AUTO_INCREMENT COMMENT 'the id of an order';

CREATE TABLE `healthy catering`.course ( 
	course_id            INT  NOT NULL  AUTO_INCREMENT,
	name_course          VARCHAR( 100 )    ,
	price                DOUBLE    ,
	CONSTRAINT pk_course PRIMARY KEY ( course_id )
 ) engine=InnoDB;

CREATE TABLE `healthy catering`.ingridient ( 
	ingridient_id        INT  NOT NULL  ,
	price                DOUBLE    ,
	amount               INT    ,
	CONSTRAINT pk_ingridient PRIMARY KEY ( ingridient_id )
 ) engine=InnoDB;

CREATE TABLE `healthy catering`.menu_combination ( 
	menu_id              INT  NOT NULL  ,
	total_price          DOUBLE    ,
	name                 VARCHAR( 100 )    ,
	CONSTRAINT pk_menu_combination PRIMARY KEY ( menu_id )
 ) engine=InnoDB;

ALTER TABLE `healthy catering`.user ADD CONSTRAINT fk_user_role FOREIGN KEY ( id ) REFERENCES `healthy catering`.role( role ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.order ADD CONSTRAINT fk_order_user FOREIGN KEY ( order_id ) REFERENCES `healthy catering`.user( id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.order ADD CONSTRAINT fk_order_course FOREIGN KEY ( order_id ) REFERENCES `healthy catering`.course( course_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.course ADD CONSTRAINT fk_course_order FOREIGN KEY ( course_id ) REFERENCES `healthy catering`.order( order_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.course ADD CONSTRAINT fk_course FOREIGN KEY ( course_id ) REFERENCES `healthy catering`.menu_combination( menu_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.course ADD CONSTRAINT fk_course_0 FOREIGN KEY ( course_id ) REFERENCES `healthy catering`.ingridient( ingridient_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.ingridient ADD CONSTRAINT fk_ingridient_inventory FOREIGN KEY ( ingridient_id ) REFERENCES `healthy catering`.inventory( inventory_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.ingridient ADD CONSTRAINT fk_ingridient FOREIGN KEY ( ingridient_id ) REFERENCES `healthy catering`.course( course_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `healthy catering`.menu_combination ADD CONSTRAINT fk_menu_combination FOREIGN KEY ( menu_id ) REFERENCES `healthy catering`.course( course_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

