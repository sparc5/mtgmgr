/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: MtgRmActionItemNoteId.java
 * Created on: 03.09.2010
 */

// Generated Sep 3, 2010 12:56:47 PM by Hibernate Tools 3.3.0.GA

package mtgmgr.entity;



/**
 * MtgRmActionItemNoteId generated by hbm2java
 */
public class MtgRmActionItemNoteId  implements java.io.Serializable {


     private int actionItemId;
     private int commentNo;

    public MtgRmActionItemNoteId() {
    }

    public MtgRmActionItemNoteId(int actionItemId, int commentNo) {
       this.actionItemId = actionItemId;
       this.commentNo = commentNo;
    }
   
    public int getActionItemId() {
        return this.actionItemId;
    }
    
    public void setActionItemId(int actionItemId) {
        this.actionItemId = actionItemId;
    }
    public int getCommentNo() {
        return this.commentNo;
    }
    
    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MtgRmActionItemNoteId) ) return false;
		 MtgRmActionItemNoteId castOther = ( MtgRmActionItemNoteId ) other; 
         
		 return (this.getActionItemId()==castOther.getActionItemId())
 && (this.getCommentNo()==castOther.getCommentNo());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getActionItemId();
         result = 37 * result + this.getCommentNo();
         return result;
   }   


}



