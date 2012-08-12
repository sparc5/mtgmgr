<%@ taglib prefix="s" uri="/struts-tags" %>
<DIV id="header">
	<!-- close north panel -->
	<div class="floatLeft"><a><span id="closeNorth" title="Close this panel..." class="ui-icon icon-clear"></span></a></div>
	<!-- right user admin panel -->
	<div class="floatRight"><s:property value="username"/> <s:if test="#session.user.isAdmin"> (admin) </s:if> &#166; <s:a action="logout"><span class="ui-icon ui-icon-key"></span><s:text name="link.main.logout"/></s:a></div>
	<!-- center title banner content -->
	<div><h3 style="display:inline;"><span class="ui-icon ui-icon-home"></span>Meeting Manager</h3><span id="mode"> (view: <a mode="personal" title="personal"><span class="ui-icon icon-personal"></span></a> &#166; <a mode="presentation" title="presentation"><span class="ui-icon icon-presentation-bw"></span></a>) </span></div>
</DIV>