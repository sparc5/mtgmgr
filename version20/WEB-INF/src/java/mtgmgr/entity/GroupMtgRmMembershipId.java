package mtgmgr.entity;
// Generated Aug 27, 2010 11:18:15 AM by Hibernate Tools 3.3.0.GA



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


