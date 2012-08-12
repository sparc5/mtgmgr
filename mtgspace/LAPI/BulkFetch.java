/**
 * The code albeit hard coded uses the livelink java api combined with the oracle
 * jdbc driver to go to a particular folder .You find that from the link on your folder
 * Once that is obtained it will query DTREE.This is the outer loop.The innerloop is made
 * up of passing a parameter to the  dversdata table that holds the version information
 * So when all said and done my program will go to a folder and fetch all the
 * available versions to a harddrive.Since this is informative all logic is hardcoded
 * you may re-design it for interactive purposes.I have not done any serious exception handling either
 * I will provide only the source file
 * since your enterprise folder,etc may be different.I use JDK 1.3.1_02    
 * @author K N Nair (appoos@hotmail.com) alias appnair/samalayali in tektips
 * The final ouput looks like <name of file>_<name of version>_<version num>_<create datae>.<filetype>
 */

import java.sql.*; //This better be on your classpath
import oracle.jdbc.driver.*;//This better be on your classpath
import com.opentext.api.*;//This better be on your classpath	
class BulkFetch
{
      private static String Server = "localhost"; //livelink host
	private static int Port = 6667; //livelink server port see opentext.ini
	private static String DFT = ""; //default database file or schema
	private static String User = "Admin"; //username
	private static String Pass = "123admin"; //passwd

public static void main (String args [])
throws SQLException
{
// Load the Oracle JDBC driver

Statement stmt1,stmt2;
ResultSet rs1,rs2;
DatabaseMetaData dbmd1;
String sqlQuery1,sqlQuery2,sqlInner;
String dbURL,dbUser,dbPwd;
int finalCount=0; //simple counter to show user how many docs we fetched
int volID, objID,versionID;//needed for livelink stuff
LLSession session;
LAPI_DOCUMENTS doc;
String FilePath,addFilePath,versionName;
Date versionDate;//for our manifest
/**************User configuration section********************/
volID = -2001;//Parent VolumeID usually enterprise workspace became 2001 in prod version
objID = 0;	
versionID=0;
dbURL="jdbc:oracle:thin:@localhost:1521:SID";//
dbUser="livelink";//User for livelink schema SID
dbPwd="ctl4docs";//Password for livelink schema SID
FilePath = "D:\\fr_KOKUBU\\Enterprise\\5 inch Line\\Backgrind\\";
//Replace parenid with the folder you are searching for or read it off an external file
sqlQuery1="select dataid,parentid,name,versionnum from dtree where parentid=6242946";
//Skeleton query to dversdata
sqlQuery2="select docid,filename,filetype,datasize,version,versionname,filecdate from dversdata where docid=";

//Get into livelink        	
session = new LLSession (Server, Port, DFT, User, Pass);
doc = new LAPI_DOCUMENTS (session);
//Get into livelink_oracle database                  
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection conn = DriverManager.getConnection(dbURL,dbUser,dbPwd);
//Create our sql here
stmt1=conn.createStatement();
System.out.println("Retreiving document info");
rs1=stmt1.executeQuery(sqlQuery1);
System.out.println(sqlQuery1);
while (rs1.next())
{
System.out.println(rs1.getString("name")+" has docid "+rs1.getInt("dataid")+ " is at version: "+rs1.getInt("versionnum"));
//version 2 find datasize and filetype for dataid from dversdata
  stmt2=conn.createStatement();
//append dataid as docid in our query
    sqlInner=sqlQuery2+ rs1.getInt("dataid");
//uncomment for debugging       
//System.out.println(sqlInner);
       rs2=stmt2.executeQuery(sqlInner);
        while (rs2.next())
           {
          System.out.println(rs2.getString("filename")+" "+rs2.getString("filetype"));
   //do livelink fetch nowAdd the Document
objID=rs2.getInt("docid");
versionID=rs2.getInt("version");
//versionname and version id are the same hence we differentiate by what it is called in GUI when
//it shows up in the versions tab
versionName=rs2.getString("filename");
versionDate=rs2.getDate("filecdate");
//System.out.println("This version is called"+versionName);
//use rs1.filename if you want document called in repository and append file type
//rs1.filename is the hyperlink visible for all versions
addFilePath=FilePath+rs1.getString("name")+"_"+versionName+"_"+versionID+"_"+versionDate+"."+rs2.getString("filetype");		
 System.out.println( "Adding"+objID+"version: "+ versionID+  " to" + addFilePath);
//addFilePath=FilePath+rs1.getString("filename");
//System.out.println( "VolID:="+volID+"objID="+objID+"VersionID="+versionID+" to this path"+addFilePath);		
	if (doc.FetchVersion(volID, objID, versionID, addFilePath) == 0)
			{
				System.out.println("Document Fetched Successfully");
                        finalCount ++;
			}
    else
{
				System.out.println("I'll be damned");

}



 }       
      sqlInner="";  
      addFilePath= "";

}




//All metadata information regarding database
dbmd1=conn.getMetaData();
ResultSetMetaData rsmd1=rs1.getMetaData();
System.out.println("You are connected to :" +dbmd1.getDatabaseProductName());
System.out.println("Your database version is :" +dbmd1.getDatabaseProductVersion());
System.out.println("You logged in as  :" +dbmd1.getUserName());
System.out.println("You should have about "+finalCount+ "docs now" );





//avoid memory leaks
stmt1.close();
conn.close();

}//main ends


}//class ends
