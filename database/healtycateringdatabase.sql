CREATE SCHEMA mariusnl;

CREATE TABLE mariusnl.inventory ( 
	inventory_id         INT  NOT NULL  ,
	inventory_type       VARCHAR( 100 )    ,
	CONSTRAINT pk_inventory PRIMARY KEY ( inventory_id ),
	CONSTRAINT pk_inventory_0 UNIQUE ( inventory_type ) 
 ) engine=InnoDB;

CREATE TABLE mariusnl.menu_combination ( 
	menu_id              INT  NOT NULL  ,
	name                 VARCHAR( 100 )    ,
	CONSTRAINT pk_menu PRIMARY KEY ( menu_id )
 ) engine=InnoDB;

CREATE TABLE mariusnl.persons ( 
	p_id                 INT  NOT NULL  AUTO_INCREMENT,
	name                 VARCHAR( 100 )  NOT NULL  ,
	address              VARCHAR( 100 )  NOT NULL  ,
	CONSTRAINT pk_persons PRIMARY KEY ( p_id ),
	CONSTRAINT pk_persons_0 UNIQUE ( name ) 
 ) engine=InnoDB;

CREATE TABLE mariusnl.role ( 
	role                 VARCHAR( 30 )  NOT NULL  ,
	p_id                 INT  NOT NULL  ,
	CONSTRAINT pk_role PRIMARY KEY ( role )
 ) engine=InnoDB;

CREATE INDEX idx_role ON mariusnl.role ( p_id );

CREATE TABLE mariusnl.ingridient ( 
	ingridient_id        INT  NOT NULL  ,
	price                DOUBLE    ,
	inventory_id         INT  NOT NULL  ,
	name                 VARCHAR( 100 )    ,
	type                 VARCHAR( 100 )    ,
	CONSTRAINT pk_ingridient PRIMARY KEY ( ingridient_id )
 ) engine=InnoDB;

CREATE INDEX idx_ingridient ON mariusnl.ingridient ( inventory_id );

CREATE INDEX idx_ingridient_0 ON mariusnl.ingridient ( type );

ALTER TABLE mariusnl.ingridient MODIFY type VARCHAR( 100 )     COMMENT 'frozen, dry, etc...';

CREATE TABLE mariusnl.orders ( 
	o_id                 INT  NOT NULL  ,
	p_id                 INT  NOT NULL  ,
	CONSTRAINT pk_orders PRIMARY KEY ( o_id )
 ) engine=InnoDB;

CREATE INDEX idx_orders ON mariusnl.orders ( p_id );

CREATE TABLE mariusnl.course ( 
	course_id            INT  NOT NULL  ,
	name                 VARCHAR( 100 )    ,
	ingridient_id        INT  NOT NULL  ,
	price                DOUBLE  NOT NULL  ,
	CONSTRAINT pk_course PRIMARY KEY ( course_id )
 ) engine=InnoDB;

CREATE INDEX idx_course ON mariusnl.course ( ingridient_id );

CREATE TABLE mariusnl.menu_combination_course ( 
	course_id            INT  NOT NULL  ,
	menu_id              INT  NOT NULL  
 ) engine=InnoDB;

CREATE INDEX idx_menu_combination_course ON mariusnl.menu_combination_course ( menu_id );

CREATE INDEX idx_menu_combination_course_0 ON mariusnl.menu_combination_course ( course_id );

CREATE TABLE mariusnl.order_course ( 
	o_id                 INT  NOT NULL  ,
	course_id            INT  NOT NULL  
 ) engine=InnoDB;

CREATE INDEX idx_order_course ON mariusnl.order_course ( course_id );

CREATE INDEX idx_order_course_0 ON mariusnl.order_course ( o_id );

null;

ALTER TABLE mariusnl.ingridient ADD CONSTRAINT fk_ingridient FOREIGN KEY ( inventory_id ) REFERENCES mariusnl.inventory( inventory_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.ingridient ADD CONSTRAINT fk_ingridient_0 FOREIGN KEY ( type ) REFERENCES mariusnl.inventory( inventory_type ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.orders ADD CONSTRAINT fk_orders_persons FOREIGN KEY ( p_id ) REFERENCES mariusnl.persons( p_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.role ADD CONSTRAINT fk_role_persons FOREIGN KEY ( p_id ) REFERENCES mariusnl.persons( p_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.course ADD CONSTRAINT fk_course FOREIGN KEY ( ingridient_id ) REFERENCES mariusnl.ingridient( ingridient_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.menu_combination_course ADD CONSTRAINT fk_menu_combination_course FOREIGN KEY ( menu_id ) REFERENCES mariusnl.menu_combination( menu_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.menu_combination_course ADD CONSTRAINT fk_menu_combination_course_0 FOREIGN KEY ( course_id ) REFERENCES mariusnl.course( course_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.order_course ADD CONSTRAINT fk_order_course FOREIGN KEY ( course_id ) REFERENCES mariusnl.course( course_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE mariusnl.order_course ADD CONSTRAINT fk_order_course_0 FOREIGN KEY ( o_id ) REFERENCES mariusnl.orders( o_id ) ON DELETE NO ACTION ON UPDATE NO ACTION;

