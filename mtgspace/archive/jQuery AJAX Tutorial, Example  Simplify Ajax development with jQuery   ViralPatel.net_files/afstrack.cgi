var T=Math.random();
var Ref=document.referrer;
if (typeof(parent.document)!="unknown")
{
var F=parent.document.URL;
if (document.referrer==F) Ref=parent.document.referrer;
}
var S="usr="+AFS_Account+"P"+AFS_Tracker+"&js=1";
if (typeof AFS_Page == "undefined") var AFS_Page="unknown";
if (typeof AFS_Url == "undefined") var AFS_Url="unknown";
if (AFS_Page=="DetectName") {AFS_Page=document.title;}
if (AFS_Url=="DetectUrl") {AFS_Url=window.document.URL;}
S+="&title="+escape(AFS_Page);
S+="&url="+escape(AFS_Url);
S+="&refer="+escape(Ref);
if(typeof(screen)=="object")
{
S+="&resolution="+screen.width+"x"+screen.height;
S+="&color="+screen.colorDepth;
}
S+="&Tips="+T;
document.write("<a href='http://new.addfreestats.com/?usr="+AFS_Account+"' >");
document.write("<img border=0 src='http://"+AFS_Server+".addfreestats.com/cgi-bin/connect.cgi?");
document.write(S);
document.write("' border=0 alt='' title='Free Web Stats'></a>");
function stloga()
{
 window.focus();
 if (window.status)
 {
 bug = new Image();
 bug.src = 'http://' + AFS_Server + '.addfreestats.com/cgi-bin/click.cgi?usr=' + AFS_Account + '&exit=Adsense->' + escape(window.status);
 }
else{
var click='Ad Clicked';
 bug = new Image();
 bug.src = 'http://' + AFS_Server + '.addfreestats.com/cgi-bin/click.cgi?usr=' + AFS_Account + '&exit=Adsense->' + escape(click);
}
}
function stlogc()
{
var temp=window.location.href.split("/");
var temp1=temp[2].split(".");
var domain=temp1[1];
var click=this.href;
 if (click.indexOf(domain) == -1)
 {
  bug = new Image();
  bug.src = 'http://' +AFS_Server + '.addfreestats.com/cgi-bin/click.cgi?usr='+ AFS_Account + '&exit='+escape(click);
 }
}
function stlogb()
{
if (this.src)
{
if(this.src.indexOf('googleads') > -1)
{
 bug = new Image();
var click='Ad Clicked';
 bug.src = 'http://' + AFS_Server + '.addfreestats.com/cgi-bin/click.cgi?usr=' + AFS_Account + '&exit=Adsense->' + escape(click);
}
 }
}
var elements;
if(document.getElementsByTagName)
{
elements = document.body.getElementsByTagName("iframe");
}
else if (document.body.all)
{
elements = document.body.all.tags("iframe");
}
else
{
elements = array();
}
for (var i = 0; i < elements.length; i++)
{
 if(elements[i].src.indexOf('googlesyndication.com') > -1)
 {
 elements[i].onfocus = stloga;
 }
 else
 {
 elements[i].onfocus = stlogb;
 }
}
if(document.getElementsByTagName)
{
elements = document.body.getElementsByTagName("a");
}
else if (document.body.all)
{
elements = document.body.all.tags("a");
}
else
{
elements = array();
}
for (var i = 0; i < elements.length; i++)
{
     elements[i].onfocus = stlogc;
}
