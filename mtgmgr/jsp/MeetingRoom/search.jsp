<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set name="mtgRmName" value="#parameters['mtgRmName']"/>
<s:set name="view" value="#parameters['view'][0]"/>

<s:if test="results.size() == 0">
<div class="wrapCenter"><h3 class="noBold">No such meeting room '<b><s:property value="mtgRmName"/></b>' found.</h3></div>
</s:if>

<s:elseif test="results.size() == 1">
<div id="mtgRmNotAccessible" class="wrapCenter hidden"><h3 class="noBold">Meeting room '<b><s:property value="mtgRmName"/></b>' is not accessible.<br />(Reason: Meeting room is closed and you are not a member of it.)<br />Please contact the secretariat <span class="ui-icon icon-contact"></span> <a href='mailto:<s:property value="results[0].secretariat.emailAddress"/>' title="Contact secretariat"><s:property value="results[0].secretariat.username"/></a> for further assistance in this matter.</h3></div>
<SCRIPT type="text/javascript">
	var view = null, mtgRmId = null, name = null, category = null, role = null;
	view = "<s:property value='#view'/>";
	if ("<s:property value='results[0].role'/>".length > 0)
		role = "<s:property value='results[0].role'/>";
	mtgRmId = <s:property value="results[0].mtgRmId"/>;
	name = "<s:property value='results[0].name'/>";
	category = "<s:property value='results[0].category'/>";
//	console.log("view:",view," / mtgRmId:",mtgRmId," / category:",category," / role:",role);

	if (category == "Closed" && role == null)
	{
		// show error message
		if ($("div#mtgRmNotAccessible").length)
			$("div#mtgRmNotAccessible").show();
	} else {
		if ($("div#mtgRmNotAccessible").length)
			$("div#mtgRmNotAccessible").remove();
		// ajax post for meeting room
		var thisPane = $mtgRmLayout.panes.center;
		thisPane.load("MeetingRoom_display", {
			view: view,
			name: name,
			mtgRmId: mtgRmId,
			category: category,
			role: role
		}, function(response, status, xhr) {
			mtgmgr.layout.showContent(mtgmgr.views.mtgRm);
			return false;
		});
	}
</SCRIPT>
</s:elseif>

<s:elseif test="%{results.size() > 1 && #view neq null}">
<div id="allMeetingRoom">
<s:if test="%{mtgRmName neq null}">
<div class="ui-layout-north ui-layout-reset"><h3 class="noBold">Search results for '<b><s:property value="mtgRmName"/></b>'</h3></div>
</s:if>

<div class="ui-layout-center ui-layout-reset ui-jqgrid">
<table class="width100 ui-jqgrid-htable" cellspacing="0" cellpadding="0" style="padding-right: 1px;">
	<thead>
		<tr class="ui-jqgrid-labels">
			<th class="ui-state-default ui-jqgrid-rownum">&nbsp;</th>
			<th class="ui-state-default">Meeting room</th>
<s:if test="#view == 'personal'">
			<th class="ui-state-default" class="center" style="width:155px;">Actions</th>
</s:if>
		</tr>
	</thead>
</table>
<table class="width100 ui-jqgrid-btable" id="tblAllMeetingRoom" cellspacing="0" cellpadding="0" style="padding-right: 1px;">
	<tbody>
<c:set var="count" value="0" scope="page"/>
<s:iterator value="results" status="status">
<s:if test="#view == 'personal'">
	<c:set var="count" value="${count+1}" scope="page"/>
	<tr id='<c:out value="${count}"/>' class="ui-widget-content ui-row jqgrow ui-row-ltr">
		<td class="ui-state-default ui-jqgrid-rownum"><c:out value="${count}"/></td>
		<td><span class="ui-icon ui-icon-newwin"></span>
<s:if test="%{category == 'Closed' && role eq null}">
		<s:property value="name"/>
</s:if><s:else>
		<a class="clickable underline" mtgRmId='<s:property value="mtgRmId"/>'><s:property value="name"/></a>
</s:else>
		</td>
<td class="center" style="width:155px;">
	<s:if test="%{category == 'Closed' && role eq null}">
		<s:if test="%{secretariat neq null}">
		<span class="ui-icon icon-contact"></span> <a href='mailto:<s:property value="secretariat.emailAddress"/>' title="Contact secretariat"><s:property value="secretariat.username"/></a>
		</s:if><s:else>&nbsp;</s:else>
	</s:if>
	<s:else>
		<a details="members" mtgRmId='<s:property value="mtgRmId"/>' class="clickable"><span class="ui-icon ui-icon-person" title="Members"></span></a> 
		<s:if test="subscriptionStatus == 'unsubscribed'">
			&#166; <a action="subscribe" mtgRmId='<s:property value="mtgRmId"/>' class="clickable"><span class="ui-icon ui-icon-flag" title="Subscribe"></span></a>
		</s:if>
		<s:elseif test="subscriptionStatus == 'subscribed'">
			&#166; <a action="unsubscribe" mtgRmId='<s:property value="mtgRmId"/>' class="clickable"><span class="ui-icon ui-icon-closethick" title="Unsubscribe"></span></a>
		</s:elseif>
		<s:else>
			<span class="ui-icon ui-icon-blank-1px"></span> <span class="ui-icon ui-icon-blank"></span>
		</s:else>
	</s:else>
</td>
	</tr>
</s:if>
<s:elseif test='#view == "presentation"'>
	<s:if test="%{category == 'Open' || (category == 'Closed' && role neq null)}">
	<c:set var="count" value="${count+1}" scope="page"/>
	<tr id='<c:out value="${count}"/>' class="ui-widget-content ui-row jqgrow ui-row-ltr">
		<td class="ui-state-default ui-jqgrid-rownum"><c:out value="${count}"/></td>
		<td><span class="ui-icon ui-icon-newwin"></span><s:a cssClass="underline"><s:property value="name"/></s:a></td>
	</tr>
	</s:if>
</s:elseif>
</s:iterator>
	</tbody>
</table>
</div>
</s:elseif>