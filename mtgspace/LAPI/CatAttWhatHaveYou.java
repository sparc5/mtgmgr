/*******************http://www.nairkn,com*************************************
***The code albeit hard coded uses the livelink java api
***for creating a category and populating its contents
***Written for  Dr LAPI series in
***http://www.greggriffiths.org/livelink/development/lapi/drlapi/
***java version "1.4.2_05"
***Java(TM) 2 Runtime Environment, Standard Edition (build 1.4.2_05-b04)
***Java HotSpot(TM) Client VM (build 1.4.2_05-b04, mixed mode)
***Java(TM) 2 Runtime Environment, Standard Edition (build 1.3.1_02-b02)
***Oracle 32-bit Windows: Version 8.1.7.0.0
***Livelink 9.1.0 SP3
***@author K N Nair (appoos@hotmail.com) alias appnair
*** in tek-tips forum http://www.tek-tips.com/tipmasters.cfm?pid=862
***The published documentation of Opentext,livelink are proprietary
***software and given proper credits
*/

/*A word about the package create a hierarchy called com/nairkn
and put the source file there and after compilation
execute it by calling java com.nairkn.CatAttWhatHaveYou
Error traps at each stage should be done by looking at
the status of each LAPI call for clarity I have omitted those
*/


/***** Listing of CatAttWhatHaveYou.java ***************************/

package com.nairkn;
import com.opentext.api.*;//see changes if you are using the lapi jar files
import java.util.*;
//import java.util.GregorianCalendar;//or the above one will do too


public class CatAttWhatHaveYou
{
	private static String Server = "localhost";
	private static int Port = 2099;
	private static String DFT = "";
	private static String User = "Admin";
	private static String Pass = "Admin";

	public static void main (String [] args)
	{
		try
		{
			/*LAPI Vars*/
			LLSession session= new LLSession (Server, Port, DFT, User, Pass);;
			LAPI_DOCUMENTS doc=new LAPI_DOCUMENTS (session);;
			LAPI_ATTRIBUTES	attributes=	new LAPI_ATTRIBUTES (session);
			LLValue entInfo= new LLValue();
			LLValue objInfo = new LLValue();
			/*when doing an unknown return value for createInfo
			make sure you make it known to lapi as an assoc
			change the call to a simple LLValue object and see
			the confusing errors for yourself
			*/
			LLValue createInfo = new LLValue().setAssocNotSet();
			LLValue catID =new LLValue().setAssoc();


            /*Java Vars*/
			int status=0;
            int  catObjID=0;
            int volumeID,objectID,objID;
            //Our category will be called thus
            String catName="MyCategory";

/* the string array was throwing array index out of bounds
when I used the sample.Anyways http://www.janeg.ca/scjp/lang/arrays.html
says that it is illegal to do the way OT wants it done according
to Jav purists*/
          /*String[] ttlvalidvalues  = { "Dr.", "Mr.", "Ms."};*/

            String[] ttlvalidvalues= new String[] {"Dr.", "Mr.", "Ms." };

/*this explains why we need the import java.util.GregorianCalendar*/
            Calendar   t  = new GregorianCalendar();



  		 if(doc.AccessEnterpriseWS(entInfo) != 0)
  	  	{
  			System.out.println("Houston Houston AccessEnterpriseWS Failed.");
  		  	System.out.println("Houston Houston our error Number is "+session.getApiError());
  		  	System.out.println("Houston Houston our Error Message is "+session.getErrMsg());
  		  	System.out.println("Houston Houston our Six Digit Error Code is "+session.getStatus());
  		  	System.out.println("Houston Houston our Status Message is "+session.getStatusMessage());
  			return;
  		}
           else
            {
            volumeID = entInfo.toInteger("VolumeID");
  			objectID = entInfo.toInteger("ID");
  			System.out.println("Enterprise Volume"+volumeID+" Enterprise ObjID"+objectID);

            }


/*Let's start messing with creating a category called MyCategory.This is available
in the LAPI documentation itself just wouldnot work as a cut and paste job*/

/*Step 1 :Create the category object on the Livelink server*/


if (status == 0)
{
status =doc.CreateObjectEx( volumeID, objectID, LAPI_DOCUMENTS.OBJECTTYPE, LAPI_DOCUMENTS.CATEGORYSUBTYPE, catName, createInfo, objInfo );
catObjID = objInfo.toInteger("ID");
System.out.println("catObjID"+catObjID);
}
else
{
System.out.println("status"+status);
System.out.println("Houston Houston our Six Digit Error Code is "+session.getStatus());
System.out.println("Houston Houston our Status Message is "+session.getStatusMessage());

}

/*Step 2 :Create and initialize the category version data structure*/
LLValue catVersion = new LLValue().setAssocNotSet();
status = attributes.AttrInitCatVersion(catName, catVersion);
System.out.println("Initialized status"+status);
System.out.println("Houston Houston our Six Digit Error Code is "+session.getStatus());
System.out.println("Houston Houston our Status Message is "+session.getStatusMessage());

/*Step 3 Add attribute definitions to the category version*/
LLValue attrInfo  = new LLValue();
LLValue attrInfoValues  = new LLValue();
LLValue defaultValues  = new LLValue();
LLValue validValues  = new LLValue();
LLValue attrPath  = new LLValue();

//Place Holders
attrPath.setList();
defaultValues.setList();
validValues.setList();
attrInfo.setAssoc();



/* We by this example are trying to define a category called
 MyCategory
     Type                            Display Name  Default Values
     Text: MultiLine   1 (locked)    Goal:         World Domination!
     Date:              1 (locked)   Date_Started: Sep 3 2000
     Flag: Checkbox   1 (locked)     Complete:
     Set   1 (locked)                Employee
                                     Title:    <None> Dr. Mr. Ms.(POPUP)
      					             Name:
       					             Job Title:
       	                             Phone Number: 3 rows    */
/*LAPI docs are wrong in this calling example they uses LL_TRUE/FALSE for
turning on/off the required button  for the text line attrib the right values are 'on'
and if you don't need it don't include the attribute  when you send it
also Fixed Rows is a boolean and not a number and for some reason I cannot
seem to get it to work.In case you are wondering how I debugged this I had
builder handy with the api call.I created the category from the GUI and I changed
the parameters on and off a couple of times and the string 'on' showed in the
assoc
*/

attrInfo.add("Rows", 3);
attrInfo.add("Columns", 32);
attrInfo.add("Type", LAPI_ATTRIBUTES.ATTR_TYPE_STRMULTI);
attrInfo.add("Required","on" );
//I could never understand this part this simply does not compile
//according to docs if FixedRows is true then the other two needs to be 1
//also.Ask Christopher Meyer in OT how to make this work if it really
//is an issue .I am willing to give up at this time
//attrInfo.add("FixedRows", 1);
attrInfo.add("MaxRows", 1);
attrInfo.add("NumRows", 1);

//Let us see if we succeeded here
status = attributes.AttrSet("Goal", attrInfo, null, catVersion);
defaultValues.setSize(1);
defaultValues.setString(0, "World Domination!");

status = attributes.AttrSetValues(catVersion, "Goal", LAPI_ATTRIBUTES.ATTR_DEFAULTVALUES, null, defaultValues);
System.out.println("AttrSet status"+status);
System.out.println("Houston Houston our Six Digit Error Code is "+session.getStatus());
System.out.println("Houston Houston our Status Message is "+session.getStatusMessage());
attrInfo.add("Type", LAPI_ATTRIBUTES.ATTR_TYPE_DATE);
attrInfo.add("Required", LLValue.LL_FALSE);


/*null and NULL made a difference here at least in my compiling.I do not know why*/
status = attributes.AttrSet("Date_Started", attrInfo, null, catVersion);
t.set(2000,8,3,0,0,0);
defaultValues.setList();
defaultValues.setSize(1);
defaultValues.setDate(0, t.getTime());
status = attributes.AttrSetValues(catVersion, "Date_Started", LAPI_ATTRIBUTES.ATTR_DEFAULTVALUES, null, defaultValues);
attrInfo.add("Type", LAPI_ATTRIBUTES.ATTR_TYPE_BOOL);
status = attributes.AttrSet("Complete", attrInfo, null, catVersion );
attrInfo.add("Type", LAPI_ATTRIBUTES.ATTR_TYPE_SET);
attrInfo.add("MaxRows", 3);
attrInfo.add("NumRows", 1);
status = attributes.AttrSet("Employee", attrInfo, null, catVersion );
attrPath.setSize(2);
attrPath.setString(0, "Employee");
attrInfo.add("Type", LAPI_ATTRIBUTES.ATTR_TYPE_STRPOPUP);
attrInfo.add("NumRows", 1);

validValues.setSize(3);

for(int  i = 0; i < ttlvalidvalues.length ; i++ )
  {
    validValues.setString(i, ttlvalidvalues[i]);
  }
attrInfo.add("ValidValues", validValues);

status = attributes.AttrSet("Title", attrInfo, attrPath, catVersion );
attrInfo.add("Type", LAPI_ATTRIBUTES.ATTR_TYPE_STRFIELD);
attrInfo.add("Length", 32);
attrInfo.add("DisplayLen", 32);
status = attributes.AttrSet("Name", attrInfo, attrPath, catVersion );
status = attributes.AttrSet("Job Title", attrInfo, attrPath, catVersion );
attrInfo.add("MaxRows", 3);
attrInfo.add("NumRows", 3);
status = attributes.AttrSet ("Phone Number", attrInfo, attrPath, catVersion );
attrPath.setInteger(1, 0);
defaultValues.setList();
defaultValues.setSize(1);
defaultValues.setString(0, "Manager");
status = attributes.AttrSetValues ( catVersion, "Job Title", LAPI_ATTRIBUTES.ATTR_DEFAULTVALUES, attrPath, defaultValues );
System.out.println("AttrSetValue status"+status);
System.out.println("Houston Houston our Six Digit Error Code is "+session.getStatus());
System.out.println("Houston Houston our Status Message is "+session.getStatusMessage());


//Step 4 Set the Category ID
catID.add("ID", catObjID);
catID.add("Type", LAPI_ATTRIBUTES.CATEGORY_TYPE_LIBRARY);
System.out.println("catObjID"+catObjID);

//Step 5 Create the category version

status=doc.CreateCategoryVersion( catID, catVersion);

System.out.println("status"+status);
System.out.println("Houston Houston our Six Digit Error Code is "+session.getStatus());
System.out.println("Houston Houston our Status Message is "+session.getStatusMessage());
System.out.println("Houston Houston feel free to write me at appoos@hotmail.com ");
System.out.println("about your experiences,improvements et al");

}









	catch (Throwable e)
		{

			System.out.println("Catch 22:Caught an Exception is LLserver running or what? " );
			System.out.println("Catch 22:You could also be running this a second time ! "   );
			System.out.println("Catch 22:Java Verbose Follows this ******************** ! " );

			System.err.println(e.getMessage() );
			e.printStackTrace (System.err);
		}
	}









}

