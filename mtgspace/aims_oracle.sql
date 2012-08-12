
CREATE TABLE GROUP_HIERARCHY ( 
  PARENT_GROUP_ID  NUMBER (15)   NOT NULL, 
  CHILD_GROUP_ID   NUMBER (15)   NOT NULL, 
  CREATION_DATE    DATE          DEFAULT SYSDATE   NOT NULL, 
  MODIFIED_DATE    DATE          DEFAULT SYSDATE   NOT NULL, 
  CONSTRAINT PK_GROUP_HRCHY
  PRIMARY KEY ( PARENT_GROUP_ID, CHILD_GROUP_ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 


CREATE UNIQUE INDEX IX1_GROUP_HRCHY ON 
  GROUP_HIERARCHY(CHILD_GROUP_ID, PARENT_GROUP_ID) 
  TABLESPACE MGTPORTAL_DATA PCTFREE 10
  STORAGE(INITIAL 64K NEXT K PCTINCREASE  ) ; 


CREATE OR REPLACE TRIGGER TU_GROUP_HRCHY
  BEFORE UPDATE
  on GROUP_HIERARCHY
  for each row
BEGIN
 :NEW.MODIFIED_DATE := SYSDATE;
 END;


CREATE TABLE GROUP_SECURITY ( 
  GROUP_ID       NUMBER (15)   NOT NULL, 
  GROUP_NAME     VARCHAR2 (200)  NOT NULL, 
  CREATION_DATE  DATE          DEFAULT SYSDATE   NOT NULL, 
  MODIFIED_DATE  DATE          DEFAULT SYSDATE   NOT NULL, 
  CONSTRAINT PK_GROUP_SEC
  PRIMARY KEY ( GROUP_ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 


CREATE UNIQUE INDEX AX1_GROUP_SEC ON 
  GROUP_SECURITY(GROUP_NAME) 
  TABLESPACE MGTPORTAL_DATA PCTFREE 10
  STORAGE(INITIAL 64K NEXT K PCTINCREASE  ) ; 


CREATE OR REPLACE TRIGGER TU_GROUP_SEC
  BEFORE UPDATE
  on GROUP_SECURITY
  for each row
BEGIN
 :NEW.MODIFIED_DATE := SYSDATE;
 END;


CREATE TABLE MTG_ACCESS_CONTROL ( 
  USER_NO                NUMBER (20)   NOT NULL, 
  USER_ID                VARCHAR2 (200), 
  USER_NAME              VARCHAR2 (200), 
  USER_ACCESS_LEVEL      VARCHAR2 (200), 
  USER_COMMITTEE_ACCESS  VARCHAR2 (400), 
  PRIMARY KEY ( USER_NO ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE OR REPLACE TRIGGER admin_trigger 
before insert on MTG_Access_control 
for each row 
begin 
select meeting_seq.nextval into :new.user_no from dual; 
end;


CREATE TABLE MTG_ACTIONITEM ( 
  ID             NUMBER ( 38 ) NOT NULL, 
  NAME           VARCHAR2 (255)  NOT NULL, 
  PROGRESS       VARCHAR2 (4000), 
  STATUS         VARCHAR2 (10), 
  COMMENTS       VARCHAR2 (4000), 
  DATEUPDATED    DATE, 
  UPDATEDBY      VARCHAR2 (80), 
  MEETINGID      NUMBER ( 38 ) NOT NULL, 
  DATECREATED    DATE, 
  CREATEDBY      VARCHAR2 (80), 
  SEC_IN_CHARGE  VARCHAR2 (4000), 
  CONSTRAINT PK_MTG_ACTIONITEM
  PRIMARY KEY ( ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_AGENDAITEM ( 
  ID                 NUMBER ( 38 ) NOT NULL, 
  NAME               VARCHAR2 (200)  NOT NULL, 
  ITEMORDER          VARCHAR2 (5), 
  STATUS             VARCHAR2 (20), 
  DEPARTMENT         VARCHAR2 (50), 
  REMARKS            VARCHAR2 (4000), 
  SUBMITTEDBY        VARCHAR2 (80), 
  DATESUBMITTED      DATE, 
  MEETINGID          NUMBER ( 38 ), 
  STARTTIME          VARCHAR2 (5), 
  UPDATEDBY          VARCHAR2 (80), 
  DATEUPDATED        DATE, 
  SUBMITTEDBYUSERID  NUMBER ( 38 ), 
  FORUMID            NUMBER ( 38 ), 
  MINUTES            VARCHAR2 (4000), 
  REMARK2            VARCHAR2 (4000), 
  PURPOSE            VARCHAR2 (1500), 
  CONSTRAINT PK_MTG_AGENDAITEM
  PRIMARY KEY ( ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_AGENDA_ACTION_LINK ( 
  AGENDAITEMID  NUMBER (38)   NOT NULL, 
  ACTIONITEMID  NUMBER (38)   NOT NULL) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_ATTENDANCE ( 
  USERID         NUMBER ( 38 ) NOT NULL, 
  MEETINGID      NUMBER ( 38 ) NOT NULL, 
  STATUS         VARCHAR2 (1), 
  REPRESENTEDBY  VARCHAR2 (80), 
  CONSTRAINT PK_MTG_ATTENDANCE
  PRIMARY KEY ( USERID, MEETINGID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_ATTENDANCE_GROUP ( 
  MEETINGID    NUMBER ( 38 ) NOT NULL, 
  USERGROUPID  NUMBER ( 38 ) NOT NULL, 
  CONSTRAINT PK_ATTENDANCE_GRP
  PRIMARY KEY ( MEETINGID, USERGROUPID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_DOMAIN ( 
  DOMAIN_ID    NUMBER (11)   NOT NULL, 
  DOMAIN_NAME  VARCHAR2 (4000), 
  DOMAIN_DESC  VARCHAR2 (4000), 
  DOMAIN_URL   VARCHAR2 (4000), 
  PRIMARY KEY ( DOMAIN_ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

GRANT SELECT ON MTG_DOMAIN TO KMCAPPL_SEARCH;

CREATE OR REPLACE TRIGGER domains_trigger 
before insert on MTG_DOMAIN 
for each row 
begin 
select meeting_seq.nextval into :new.domain_id from dual; 
end;


CREATE TABLE MTG_FORUM ( 
  ID                         NUMBER ( 38 ) NOT NULL, 
  NAME                       VARCHAR2 (100)  NOT NULL, 
  DESCRIPTION                VARCHAR2 (4000), 
  DATEUPDATED                DATE, 
  UPDATEDBY                  VARCHAR2 (80), 
  TOR                        VARCHAR2 (4000), 
  FOLDERID                   NUMBER ( 38 ), 
  SECRETARIATCONTACTGROUPID  NUMBER ( 38 ), 
  OPENFORUM                  VARCHAR2 (1), 
  DATECREATED                DATE, 
  CREATEDBY                  VARCHAR2 (80), 
  CONTACT                    VARCHAR2 (4000), 
  MEMBERSHIP_URL             VARCHAR2 (4000), 
  DOMAIN_ID                  NUMBER (11), 
  EROOM_URL                  VARCHAR2 (1000), 
  FLAG1                      CHAR (1)      DEFAULT 'N'
 NOT NULL, 
  WIKI_URL                   VARCHAR2 (4000), 
  GLOSSARY_URL               VARCHAR2 (4000), 
  CDMSFOLDERID               VARCHAR2 (50), 
  SENDNOTIFYISSUEEMAIL       VARCHAR2 (4), 
  CONSTRAINT PK_MTG_FORUM
  PRIMARY KEY ( ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

GRANT SELECT ON MTG_FORUM TO KMCAPPL_SEARCH;

CREATE TABLE MTG_FORUM_BK ( 
  ID                         NUMBER ( 38 ) NOT NULL, 
  NAME                       VARCHAR2 (100)  NOT NULL, 
  DESCRIPTION                VARCHAR2 (4000), 
  DATEUPDATED                DATE, 
  UPDATEDBY                  VARCHAR2 (80), 
  TOR                        VARCHAR2 (4000), 
  FOLDERID                   NUMBER ( 38 ), 
  SECRETARIATCONTACTGROUPID  NUMBER ( 38 ), 
  OPENFORUM                  VARCHAR2 (1), 
  DATECREATED                DATE, 
  CREATEDBY                  VARCHAR2 (80), 
  CONTACT                    VARCHAR2 (4000), 
  MEMBERSHIP_URL             VARCHAR2 (4000), 
  DOMAIN_ID                  NUMBER (11), 
  EROOM_URL                  VARCHAR2 (1000), 
  FLAG1                      CHAR (1)      NOT NULL, 
  WIKI_URL                   VARCHAR2 (4000), 
  GLOSSARY_URL               VARCHAR2 (4000), 
  CDMSFOLDERID               VARCHAR2 (50)) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_LOGBOOK ( 
  ID            NUMBER ( 38 ) NOT NULL, 
  TITLE         VARCHAR2 (4000), 
  ACTIVITYLOG   VARCHAR2 (4000), 
  CREATOR       VARCHAR2 (500), 
  CREATIONDATE  DATE, 
  UPDATEDBY     VARCHAR2 (500), 
  LASTUPDATED   DATE, 
  EXTRA         VARCHAR2 (4000), 
  FORUMID       NUMBER ( 38 ), 
  CREATORID     NUMBER ( 38 )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_MEETING ( 
  ID                       NUMBER ( 38 ) NOT NULL, 
  NAME                     VARCHAR2 (100)  NOT NULL, 
  MEETINGDATE              DATE, 
  STARTTIME                VARCHAR2 (5), 
  ENDTIME                  VARCHAR2 (5), 
  VENUE                    VARCHAR2 (100), 
  ANNOUNCEMENTS            VARCHAR2 (4000), 
  FORUMID                  NUMBER ( 38 ) NOT NULL, 
  DATEUPDATED              DATE, 
  FOLDERID                 NUMBER ( 38 ), 
  HTMLDOCID                NUMBER ( 38 ), 
  UPDATEDBY                VARCHAR2 (80), 
  CREATEDBY                VARCHAR2 (80), 
  DATECREATED              DATE, 
  AGENDA_TITLE             VARCHAR2 (4000), 
  AGENDA_URL               VARCHAR2 (4000), 
  MEETING_MATERIALS_TITLE  VARCHAR2 (4000), 
  MEETING_MATERIALS_URL    VARCHAR2 (4000), 
  MINUTES_TITLE            VARCHAR2 (4000), 
  MINUTES_URL              VARCHAR2 (4000), 
  PREVIOUS_MINUTES_TITLE   VARCHAR2 (4000), 
  PREVIOUS_MINUTES_URL     VARCHAR2 (4000), 
  DOMAIN_ID                NUMBER (11), 
  CONSTRAINT PK_MTG_MEETING
  PRIMARY KEY ( ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

GRANT SELECT ON MTG_MEETING TO KMCAPPL_SEARCH;

CREATE TABLE MTG_MEMBERSHIP ( 
  FORUMID        NUMBER ( 38 ), 
  USERGROUPID    NUMBER ( 38 ), 
  ROLE           VARCHAR2 (100), 
  TOR            VARCHAR2 (1), 
  TYPE           VARCHAR2 (1), 
  USERGROUPNAME  VARCHAR2 (150)) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_MYACTIONITEM ( 
  ACTIONITEMID  NUMBER ( 38 ) NOT NULL, 
  USERID        NUMBER ( 38 ) NOT NULL, 
  USERNAME      VARCHAR2 (150)) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_MYSUBSCRIPTION ( 
  FORUMID  NUMBER ( 38 ) NOT NULL, 
  USERID   NUMBER ( 38 ) NOT NULL) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_PAPER ( 
  ID                NUMBER ( 38 ) NOT NULL, 
  NAME              VARCHAR2 (200)  NOT NULL, 
  URL               VARCHAR2 (400)  NOT NULL, 
  TYPE              VARCHAR2 (1)  NOT NULL, 
  AGENDAITEMID      NUMBER ( 38 ), 
  MEETINGID         NUMBER ( 38 ), 
  DOCTYPEIMAGEUUID  VARCHAR2 (50), 
  PARENTID          NUMBER ( 38 ), 
  PARENTTYPE        VARCHAR2 (50), 
  CONSTRAINT PK_MTG_PAPER
  PRIMARY KEY ( ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_SECONDARYASSIGNEE ( 
  ACTIONITEMID  NUMBER ( 38 ) NOT NULL, 
  USERID        NUMBER ( 38 ) NOT NULL) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_STATS ( 
  ENTITYID  NUMBER (38)   NOT NULL, 
  TYPE      VARCHAR2 (4000)  NOT NULL, 
  MONTH     NUMBER (38)   NOT NULL, 
  YEAR      NUMBER (38)   NOT NULL, 
  HITS      NUMBER (38)) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_STATS_2 ( 
  ENTITYID  NUMBER ( 38 ) NOT NULL, 
  TYPE      VARCHAR2 (4000)  NOT NULL, 
  HITS      NUMBER ( 38 )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_UPDATES ( 
  ITEM_TYPE       VARCHAR2 (500)  NOT NULL, 
  ITEM_ID         NUMBER (38)   NOT NULL, 
  UPDATE_CONTENT  VARCHAR2 (4000), 
  UPDATE_DATE     DATE          NOT NULL, 
  UPDATE_OWNER    VARCHAR2 (50)  NOT NULL, 
  UPDATE_TYPE     VARCHAR2 (4000)  NOT NULL) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_USER_PROFILE ( 
  USERID        NUMBER ( 38 ) NOT NULL, 
  USERLOGIN     VARCHAR2 (50)  NOT NULL, 
  USERFULLNAME  VARCHAR2 (100), 
  CONSTRAINT PK_MTG_USER_PROFILE
  PRIMARY KEY ( USERID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_WORKSPACE ( 
  ID              NUMBER (38)   NOT NULL, 
  MESSAGE         VARCHAR2 (4000), 
  CREATEDBY       VARCHAR2 (500), 
  DATECREATED     DATE, 
  FORUMID         NUMBER (38), 
  TYPE            VARCHAR2 (500), 
  TYPEID          NUMBER (38), 
  SUBJECT         VARCHAR2 (4000), 
  PRIVATECOMMENT  VARCHAR2 (4)) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE MTG_WORKSPACE_BK ( 
  ID           NUMBER ( 38 ) NOT NULL, 
  MESSAGE      VARCHAR2 (4000), 
  CREATEDBY    VARCHAR2 (500), 
  DATECREATED  DATE, 
  FORUMID      NUMBER ( 38 ), 
  TYPE         VARCHAR2 (500), 
  TYPEID       NUMBER ( 38 ), 
  SUBJECT      VARCHAR2 (4000)) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE USER_GROUP_CACHE ( 
  USER_NAME   VARCHAR2 (200)  NOT NULL, 
  GROUP_NAME  VARCHAR2 (200)  NOT NULL, 
  CONSTRAINT PK_USER_GR_CACHE
  PRIMARY KEY ( USER_NAME, GROUP_NAME ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE USER_GROUP_HIERARCHY ( 
  GROUP_ID       NUMBER (15)   NOT NULL, 
  USER_ID        NUMBER (15)   NOT NULL, 
  CREATION_DATE  DATE          DEFAULT SYSDATE   NOT NULL, 
  MODIFIED_DATE  DATE          DEFAULT SYSDATE   NOT NULL, 
  CONSTRAINT PK_USER_G_HRCHY
  PRIMARY KEY ( GROUP_ID, USER_ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 


CREATE UNIQUE INDEX IX1_USER_G_HRCHY ON 
  USER_GROUP_HIERARCHY(USER_ID, GROUP_ID) 
  TABLESPACE MGTPORTAL_DATA PCTFREE 10
  STORAGE(INITIAL 64K NEXT K PCTINCREASE  ) ; 


CREATE OR REPLACE TRIGGER TU_USER_G_HRCHY
  BEFORE UPDATE
  on USER_GROUP_HIERARCHY
  for each row
BEGIN
 :NEW.MODIFIED_DATE := SYSDATE;
 END;


CREATE TABLE USER_PROFILE ( 
  USER_NAME      VARCHAR2 (200)  NOT NULL, 
  PROFILE_TYPE   VARCHAR2 (100)  NOT NULL, 
  CREATION_DATE  DATE          DEFAULT SYSDATE   NOT NULL, 
  CONSTRAINT PK_USER_PROFILE
  PRIMARY KEY ( USER_NAME ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 

CREATE TABLE USER_SECURITY ( 
  USER_ID        NUMBER (15)   NOT NULL, 
  PASSWORD       VARCHAR2 (50), 
  USER_NAME      VARCHAR2 (200)  NOT NULL, 
  CREATION_DATE  DATE          DEFAULT SYSDATE   NOT NULL, 
  MODIFIED_DATE  DATE          DEFAULT SYSDATE   NOT NULL, 
  CONSTRAINT PK_USER_SEC
  PRIMARY KEY ( USER_ID ) 
    USING INDEX 
     TABLESPACE MGTPORTAL_DATA PCTFREE 10
     STORAGE ( INITIAL 64K NEXT K PCTINCREASE  )) 
 TABLESPACE MGTPORTAL_DATA
   PCTFREE 10   PCTUSED 40
   INITRANS 1   MAXTRANS 255
 STORAGE ( 
   INITIAL 64K NEXT K PCTINCREASE 
   MINEXTENTS 1 MAXEXTENTS 2147483645 )
   NOCACHE; 


CREATE UNIQUE INDEX AX1_USER_SEC ON 
  USER_SECURITY(USER_NAME) 
  TABLESPACE MGTPORTAL_DATA PCTFREE 10
  STORAGE(INITIAL 64K NEXT K PCTINCREASE  ) ; 


CREATE OR REPLACE TRIGGER TU_USER_SEC
  BEFORE UPDATE
  on USER_SECURITY
  for each row
BEGIN
 :NEW.MODIFIED_DATE := SYSDATE;
 END;


ALTER TABLE GROUP_HIERARCHY ADD  CONSTRAINT FK1_GROUP_HRCHY
 FOREIGN KEY (PARENT_GROUP_ID) 
  REFERENCES MGTPORTAL.GROUP_SECURITY (GROUP_ID) 
 ON DELETE CASCADE;

ALTER TABLE GROUP_HIERARCHY ADD  CONSTRAINT FK2_GROUP_HRCHY
 FOREIGN KEY (CHILD_GROUP_ID) 
  REFERENCES MGTPORTAL.GROUP_SECURITY (GROUP_ID) ;

ALTER TABLE MTG_AGENDA_ACTION_LINK ADD  CONSTRAINT FK_LINK_ACTIONITEM
 FOREIGN KEY (ACTIONITEMID) 
  REFERENCES MGTPORTAL.MTG_ACTIONITEM (ID) 
 ON DELETE CASCADE;

ALTER TABLE MTG_AGENDA_ACTION_LINK ADD  CONSTRAINT FK_LINK_AGENDAITEM
 FOREIGN KEY (AGENDAITEMID) 
  REFERENCES MGTPORTAL.MTG_AGENDAITEM (ID) 
 ON DELETE CASCADE;

ALTER TABLE MTG_ATTENDANCE ADD  CONSTRAINT FK_MTG_ATTENDANCE_MEETINGID
 FOREIGN KEY (MEETINGID) 
  REFERENCES MGTPORTAL.MTG_MEETING (ID) 
 ON DELETE CASCADE;

ALTER TABLE MTG_ATTENDANCE ADD  CONSTRAINT FK_MTG_ATTENDANCE_USERID
 FOREIGN KEY (USERID) 
  REFERENCES MGTPORTAL.MTG_USER_PROFILE (USERID) 
 ON DELETE CASCADE;

ALTER TABLE MTG_ATTENDANCE_GROUP ADD  CONSTRAINT FK_ATTENDANCE_GRP_MEETINGID
 FOREIGN KEY (MEETINGID) 
  REFERENCES MGTPORTAL.MTG_MEETING (ID) 
 ON DELETE CASCADE;

ALTER TABLE MTG_ATTENDANCE_GROUP ADD  CONSTRAINT FK_ATTENDANCE_GRP_USERGROUPID
 FOREIGN KEY (USERGROUPID) 
  REFERENCES MGTPORTAL.GROUP_SECURITY (GROUP_ID) 
 ON DELETE CASCADE;

ALTER TABLE USER_GROUP_HIERARCHY ADD  CONSTRAINT FK1_USER_G_HRCHY
 FOREIGN KEY (USER_ID) 
  REFERENCES MGTPORTAL.USER_SECURITY (USER_ID) 
 ON DELETE CASCADE;

ALTER TABLE USER_GROUP_HIERARCHY ADD  CONSTRAINT FK2_USER_G_HRCHY
 FOREIGN KEY (GROUP_ID) 
  REFERENCES MGTPORTAL.GROUP_SECURITY (GROUP_ID) 
 ON DELETE CASCADE;

