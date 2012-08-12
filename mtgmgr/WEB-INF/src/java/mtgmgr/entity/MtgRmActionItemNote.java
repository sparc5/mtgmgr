/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MtgRmActionItemNote.java
 * Created on: 03.09.2010
 */

// Generated Sep 3, 2010 12:56:47 PM by Hibernate Tools 3.3.0.GA

package mtgmgr.entity;


import java.util.Date;

/**
 * MtgRmActionItemNote generated by hbm2java
 */
public class MtgRmActionItemNote  implements java.io.Serializable {


     private MtgRmActionItemNoteId id;
     private MtgRmActionItem mtgRmActionItem;
     private UserDirectory userDirectory;
     private Date datetime;
     private String content;

    public MtgRmActionItemNote() {
    }

    public MtgRmActionItemNote(MtgRmActionItemNoteId id, MtgRmActionItem mtgRmActionItem, UserDirectory userDirectory, Date datetime, String content) {
       this.id = id;
       this.mtgRmActionItem = mtgRmActionItem;
       this.userDirectory = userDirectory;
       this.datetime = datetime;
       this.content = content;
    }
   
    public MtgRmActionItemNoteId getId() {
        return this.id;
    }
    
    public void setId(MtgRmActionItemNoteId id) {
        this.id = id;
    }
    public MtgRmActionItem getMtgRmActionItem() {
        return this.mtgRmActionItem;
    }
    
    public void setMtgRmActionItem(MtgRmActionItem mtgRmActionItem) {
        this.mtgRmActionItem = mtgRmActionItem;
    }
    public UserDirectory getUserDirectory() {
        return this.userDirectory;
    }
    
    public void setUserDirectory(UserDirectory userDirectory) {
        this.userDirectory = userDirectory;
    }
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }




}


