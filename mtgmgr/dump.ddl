
    create table meetingmanager.group_list (
        GROUPID integer not null unique comment '',
        NAME varchar(200) not null comment '',
        primary key (GROUPID),
        unique (GROUPID)
    ) comment='';

    create table meetingmanager.group_mtg_rm_membership (
        GROUPID integer not null comment '',
        MTG_RM_ID integer not null comment '',
        ROLE varchar(12) not null comment '',
        primary key (GROUPID, MTG_RM_ID),
        unique (GROUPID, MTG_RM_ID)
    ) comment='';

    create table meetingmanager.mtg_rm_action_item (
        ACTION_ITEM_ID integer not null unique comment '',
        MEETING_ID integer not null comment '',
        AGENDA_NO integer not null comment '',
        ITEM varchar(200) not null comment '',
        STATUS varchar(9) not null comment '',
        DUE_DATE date comment '',
        primary key (ACTION_ITEM_ID),
        unique (ACTION_ITEM_ID)
    ) comment='';

    create table meetingmanager.mtg_rm_action_item_assignee (
        ASSIGNEE_ID integer not null unique comment '',
        USERID integer not null comment '',
        ACTION_ITEM_ID integer not null comment '',
        primary key (ASSIGNEE_ID),
        unique (ASSIGNEE_ID)
    ) comment='';

    create table meetingmanager.mtg_rm_action_item_note (
        ACTION_ITEM_ID integer not null comment '',
        COMMENT_NO integer not null comment '',
        USERID integer not null comment '',
        DATETIME datetime not null comment '',
        CONTENT longtext not null comment '',
        primary key (ACTION_ITEM_ID, COMMENT_NO),
        unique (ACTION_ITEM_ID, COMMENT_NO)
    ) comment='';

    create table meetingmanager.mtg_rm_agenda_item (
        MEETING_ID integer not null comment '',
        AGENDA_NO integer not null comment '',
        ITEM varchar(200) not null comment '',
        PRESENTER_ID integer not null comment '',
        STAFF varchar(200) comment '',
        SYNOPSIS longtext not null comment '',
        DURATION time not null comment '',
        LOG longtext comment '',
        primary key (MEETING_ID, AGENDA_NO),
        unique (MEETING_ID, AGENDA_NO)
    ) comment='';

    create table meetingmanager.mtg_rm_agenda_item_attachment (
        ATTACHMENT_ID integer not null unique comment '',
        MEETING_ID integer not null comment '',
        AGENDA_NO integer not null comment '',
        FILENAME longtext not null comment '',
        primary key (ATTACHMENT_ID),
        unique (ATTACHMENT_ID)
    ) comment='';

    create table meetingmanager.mtg_rm_agenda_item_comment (
        MEETING_ID integer not null comment '',
        AGENDA_NO integer not null comment '',
        COMMENT_NO integer not null comment '',
        USERID integer not null comment '',
        DATETIME datetime not null comment '',
        CONTENT longtext not null comment '',
        primary key (MEETING_ID, AGENDA_NO, COMMENT_NO),
        unique (MEETING_ID, AGENDA_NO, COMMENT_NO)
    ) comment='';

    create table meetingmanager.mtg_rm_list (
        MTG_RM_ID integer not null unique comment '',
        NAME varchar(200) not null unique comment '',
        CATEGORY varchar(6) not null comment '',
        TOR longtext comment '',
        primary key (MTG_RM_ID),
        unique (NAME),
        unique (MTG_RM_ID)
    ) comment='';

    create table meetingmanager.mtg_rm_meeting (
        MEETING_ID integer not null unique comment '',
        MTG_RM_ID integer not null comment '',
        NAME varchar(200) not null comment '',
        DATE date not null comment '',
        START_TIME time not null comment '',
        END_TIME time not null comment '',
        VENUE varchar(50) not null comment '',
        LAST_UPDATED datetime not null comment '',
        primary key (MEETING_ID),
        unique (MEETING_ID)
    ) comment='';

    create table meetingmanager.mtg_rm_membership (
        USERID integer not null comment '',
        MTG_RM_ID integer not null comment '',
        ROLE varchar(12) not null comment '',
        primary key (USERID, MTG_RM_ID),
        unique (USERID, MTG_RM_ID)
    ) comment='';

    create table meetingmanager.user_directory (
        USERID integer not null unique comment '',
        USERNAME varchar(200) not null comment '',
        PASSWORD varchar(50) not null comment '',
        EMAIL_ADDRESS varchar(200) comment '',
        IS_ADMIN bit not null comment '',
        primary key (USERID),
        unique (USERID)
    ) comment='';

    create table meetingmanager.user_group_membership (
        USERID integer not null comment '',
        GROUPID integer not null comment '',
        primary key (USERID, GROUPID),
        unique (USERID, GROUPID)
    ) comment='';

    create index FK_mtg_rm_membership_GROUPID on meetingmanager.group_mtg_rm_membership (GROUPID);

    create index FK_mtg_rm_membership_MTG_RM_ID on meetingmanager.group_mtg_rm_membership (MTG_RM_ID);

    alter table meetingmanager.group_mtg_rm_membership 
        add index FK_group_mtg_rm_membership_MTG_RM_ID (MTG_RM_ID), 
        add constraint FK_group_mtg_rm_membership_MTG_RM_ID 
        foreign key (MTG_RM_ID) 
        references meetingmanager.mtg_rm_list (MTG_RM_ID);

    alter table meetingmanager.group_mtg_rm_membership 
        add index FKF672AC3BBD9603E2 (GROUPID), 
        add constraint FKF672AC3BBD9603E2 
        foreign key (GROUPID) 
        references meetingmanager.group_list (GROUPID);

    alter table meetingmanager.group_mtg_rm_membership 
        add index FKF672AC3B36C321C4 (MTG_RM_ID), 
        add constraint FKF672AC3B36C321C4 
        foreign key (MTG_RM_ID) 
        references meetingmanager.mtg_rm_list (MTG_RM_ID);

    alter table meetingmanager.group_mtg_rm_membership 
        add index FK_group_mtg_rm_membership_GROUPID (GROUPID), 
        add constraint FK_group_mtg_rm_membership_GROUPID 
        foreign key (GROUPID) 
        references meetingmanager.group_list (GROUPID);

    create index FK_mtg_rm_action_item_MEETING_ID on meetingmanager.mtg_rm_action_item (MEETING_ID);

    alter table meetingmanager.mtg_rm_action_item 
        add index FKB50DA5F79D4A6934 (MEETING_ID), 
        add constraint FKB50DA5F79D4A6934 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    alter table meetingmanager.mtg_rm_action_item 
        add index FK_mtg_rm_action_item_MEETING_ID (MEETING_ID), 
        add constraint FK_mtg_rm_action_item_MEETING_ID 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    create index FK_mtg_rm_action_item_assignee_ACTION_ITEM_ID on meetingmanager.mtg_rm_action_item_assignee (ACTION_ITEM_ID);

    create index FK_mtg_rm_action_item_assignee_USERID on meetingmanager.mtg_rm_action_item_assignee (USERID);

    alter table meetingmanager.mtg_rm_action_item_assignee 
        add index FK_mtg_rm_action_item_assignee_ACTION_ITEM_ID (ACTION_ITEM_ID), 
        add constraint FK_mtg_rm_action_item_assignee_ACTION_ITEM_ID 
        foreign key (ACTION_ITEM_ID) 
        references meetingmanager.mtg_rm_action_item (ACTION_ITEM_ID);

    alter table meetingmanager.mtg_rm_action_item_assignee 
        add index FKA1B15F17270D5BD3 (USERID), 
        add constraint FKA1B15F17270D5BD3 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_action_item_assignee 
        add index FK_mtg_rm_action_item_assignee_USERID (USERID), 
        add constraint FK_mtg_rm_action_item_assignee_USERID 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_action_item_assignee 
        add index FKA1B15F17F6D80F8D (ACTION_ITEM_ID), 
        add constraint FKA1B15F17F6D80F8D 
        foreign key (ACTION_ITEM_ID) 
        references meetingmanager.mtg_rm_action_item (ACTION_ITEM_ID);

    create index FK_mtg_rm_action_item_note_USERID on meetingmanager.mtg_rm_action_item_note (USERID);

    alter table meetingmanager.mtg_rm_action_item_note 
        add index FK_mtg_rm_action_item_note_ACTION_ITEM_ID (ACTION_ITEM_ID), 
        add constraint FK_mtg_rm_action_item_note_ACTION_ITEM_ID 
        foreign key (ACTION_ITEM_ID) 
        references meetingmanager.mtg_rm_action_item (ACTION_ITEM_ID);

    alter table meetingmanager.mtg_rm_action_item_note 
        add index FKA59CE53A270D5BD3 (USERID), 
        add constraint FKA59CE53A270D5BD3 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_action_item_note 
        add index FK_mtg_rm_action_item_note_USERID (USERID), 
        add constraint FK_mtg_rm_action_item_note_USERID 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_action_item_note 
        add index FKA59CE53AF6D80F8D (ACTION_ITEM_ID), 
        add constraint FKA59CE53AF6D80F8D 
        foreign key (ACTION_ITEM_ID) 
        references meetingmanager.mtg_rm_action_item (ACTION_ITEM_ID);

    create index FK_mtg_rm_agenda_item_MEETING_ID on meetingmanager.mtg_rm_agenda_item (MEETING_ID);

    create index FK_mtg_rm_agenda_item_PRESENTER_ID on meetingmanager.mtg_rm_agenda_item (PRESENTER_ID);

    alter table meetingmanager.mtg_rm_agenda_item 
        add index FK_mtg_rm_agenda_item_PRESENTER_ID (PRESENTER_ID), 
        add constraint FK_mtg_rm_agenda_item_PRESENTER_ID 
        foreign key (PRESENTER_ID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_agenda_item 
        add index FK81833DE19D4A6934 (MEETING_ID), 
        add constraint FK81833DE19D4A6934 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    alter table meetingmanager.mtg_rm_agenda_item 
        add index FK_mtg_rm_agenda_item_MEETING_ID (MEETING_ID), 
        add constraint FK_mtg_rm_agenda_item_MEETING_ID 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    alter table meetingmanager.mtg_rm_agenda_item 
        add index FK81833DE182A477BF (PRESENTER_ID), 
        add constraint FK81833DE182A477BF 
        foreign key (PRESENTER_ID) 
        references meetingmanager.user_directory (USERID);

    create index FK_mtg_rm_agenda_item_attachment_MEETING_ID_AGENDA_NO on meetingmanager.mtg_rm_agenda_item_attachment (MEETING_ID, AGENDA_NO);

    alter table meetingmanager.mtg_rm_agenda_item_attachment 
        add index FK61FCAA419D4A6934 (MEETING_ID), 
        add constraint FK61FCAA419D4A6934 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    alter table meetingmanager.mtg_rm_agenda_item_attachment 
        add index FK_mtg_rm_agenda_item_attachment_MEETING_ID (MEETING_ID), 
        add constraint FK_mtg_rm_agenda_item_attachment_MEETING_ID 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    create index FK_mtg_rm_agenda_item_comment_USERID on meetingmanager.mtg_rm_agenda_item_comment (USERID);

    create index FK_mtg_rm_agenda_item_comment_MEETING_ID on meetingmanager.mtg_rm_agenda_item_comment (MEETING_ID);

    alter table meetingmanager.mtg_rm_agenda_item_comment 
        add index FKB25162019D4A6934 (MEETING_ID), 
        add constraint FKB25162019D4A6934 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    alter table meetingmanager.mtg_rm_agenda_item_comment 
        add index FK_mtg_rm_agenda_item_comment_MEETING_ID (MEETING_ID), 
        add constraint FK_mtg_rm_agenda_item_comment_MEETING_ID 
        foreign key (MEETING_ID) 
        references meetingmanager.mtg_rm_meeting (MEETING_ID);

    alter table meetingmanager.mtg_rm_agenda_item_comment 
        add index FKB2516201270D5BD3 (USERID), 
        add constraint FKB2516201270D5BD3 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_agenda_item_comment 
        add index FK_mtg_rm_agenda_item_comment_USERID (USERID), 
        add constraint FK_mtg_rm_agenda_item_comment_USERID 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    create index FK_mtg_rm_meeting_MTG_RM_ID on meetingmanager.mtg_rm_meeting (MTG_RM_ID);

    alter table meetingmanager.mtg_rm_meeting 
        add index FK_mtg_rm_meeting_MTG_RM_ID (MTG_RM_ID), 
        add constraint FK_mtg_rm_meeting_MTG_RM_ID 
        foreign key (MTG_RM_ID) 
        references meetingmanager.mtg_rm_list (MTG_RM_ID);

    alter table meetingmanager.mtg_rm_meeting 
        add index FK46B33E3636C321C4 (MTG_RM_ID), 
        add constraint FK46B33E3636C321C4 
        foreign key (MTG_RM_ID) 
        references meetingmanager.mtg_rm_list (MTG_RM_ID);

    create index FK_mtg_rm_membership_USERID on meetingmanager.mtg_rm_membership (USERID);

    create index FK_mtg_rm_membership_MTG_RM_ID on meetingmanager.mtg_rm_membership (MTG_RM_ID);

    alter table meetingmanager.mtg_rm_membership 
        add index FK_mtg_rm_membership_MTG_RM_ID (MTG_RM_ID), 
        add constraint FK_mtg_rm_membership_MTG_RM_ID 
        foreign key (MTG_RM_ID) 
        references meetingmanager.mtg_rm_list (MTG_RM_ID);

    alter table meetingmanager.mtg_rm_membership 
        add index FKF4F9CDFB270D5BD3 (USERID), 
        add constraint FKF4F9CDFB270D5BD3 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_membership 
        add index FK_mtg_rm_membership_USERID (USERID), 
        add constraint FK_mtg_rm_membership_USERID 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.mtg_rm_membership 
        add index FKF4F9CDFB36C321C4 (MTG_RM_ID), 
        add constraint FKF4F9CDFB36C321C4 
        foreign key (MTG_RM_ID) 
        references meetingmanager.mtg_rm_list (MTG_RM_ID);

    create index FK_user_group_membership_GROUPID on meetingmanager.user_group_membership (GROUPID);

    create index FK_user_group_membership_USERID on meetingmanager.user_group_membership (USERID);

    alter table meetingmanager.user_group_membership 
        add index FK_user_group_membership_USERID (USERID), 
        add constraint FK_user_group_membership_USERID 
        foreign key (USERID) 
        references meetingmanager.user_directory (USERID);

    alter table meetingmanager.user_group_membership 
        add index FK_user_group_membership_GROUPID (GROUPID), 
        add constraint FK_user_group_membership_GROUPID 
        foreign key (GROUPID) 
        references meetingmanager.group_list (GROUPID);
