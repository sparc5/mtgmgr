package mtgmgr.entity;
// Generated Aug 27, 2010 11:18:15 AM by Hibernate Tools 3.3.0.GA


import java.util.HashSet;
import java.util.Set;

/**
 * GroupList generated by hbm2java
 */
public class GroupList  implements java.io.Serializable {


     private int groupid;
     private String name;
     private Set<UserDirectory> userDirectories = new HashSet<UserDirectory>(0);
     private Set<GroupMtgRmMembership> groupMtgRmMemberships = new HashSet<GroupMtgRmMembership>(0);

    public GroupList() {
    }

	
    public GroupList(int groupid, String name) {
        this.groupid = groupid;
        this.name = name;
    }
    public GroupList(int groupid, String name, Set<UserDirectory> userDirectories, Set<GroupMtgRmMembership> groupMtgRmMemberships) {
       this.groupid = groupid;
       this.name = name;
       this.userDirectories = userDirectories;
       this.groupMtgRmMemberships = groupMtgRmMemberships;
    }
   
    public int getGroupid() {
        return this.groupid;
    }
    
    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<UserDirectory> getUserDirectories() {
        return this.userDirectories;
    }
    
    public void setUserDirectories(Set<UserDirectory> userDirectories) {
        this.userDirectories = userDirectories;
    }
    public Set<GroupMtgRmMembership> getGroupMtgRmMemberships() {
        return this.groupMtgRmMemberships;
    }
    
    public void setGroupMtgRmMemberships(Set<GroupMtgRmMembership> groupMtgRmMemberships) {
        this.groupMtgRmMemberships = groupMtgRmMemberships;
    }




}

