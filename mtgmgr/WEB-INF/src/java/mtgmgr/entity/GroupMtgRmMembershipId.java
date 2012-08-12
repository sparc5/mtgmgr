/*
 * =============================================================================
 * Copyright 2010 Defence Science Technology Agency
 * 1 Depot Road, Defence Technology Tower A, Singapore 109679
 * All rights reserved.
 * =============================================================================
 * 
 * Project: Meeting Manager
 * Filename: GroupMtgRmMembershipId.java
 * Created on: 03.09.2010
 */

// Generated Sep 3, 2010 12:56:47 PM by Hibernate Tools 3.3.0.GA

package mtgmgr.entity;



/**
 * GroupMtgRmMembershipId generated by hbm2java
 */
public class GroupMtgRmMembershipId  implements java.io.Serializable {


     private int groupid;
     private int mtgRmId;

    public GroupMtgRmMembershipId() {
    }

    public GroupMtgRmMembershipId(int groupid, int mtgRmId) {
       this.groupid = groupid;
       this.mtgRmId = mtgRmId;
    }
   
    public int getGroupid() {
        return this.groupid;
    }
    
    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
    public int getMtgRmId() {
        return this.mtgRmId;
    }
    
    public void setMtgRmId(int mtgRmId) {
        this.mtgRmId = mtgRmId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof GroupMtgRmMembershipId) ) return false;
		 GroupMtgRmMembershipId castOther = ( GroupMtgRmMembershipId ) other; 
         
		 return (this.getGroupid()==castOther.getGroupid())
 && (this.getMtgRmId()==castOther.getMtgRmId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getGroupid();
         result = 37 * result + this.getMtgRmId();
         return result;
   }   


}



