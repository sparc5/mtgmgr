<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
<div class="commonErrors"><p>The following errors have occurred:</p><s:fielderror/><s:actionerror/><s:actionmessage/></div>
</s:if>
<s:else><font color='<s:if test="not result">red</s:if><s:else>green</s:else>'>Creation of new meeting room is<s:if test="not result"> not</s:if> successful.</font></s:else>
<s:if test="result"><SCRIPT>$(":reset",$.meetingRoomForm.elemId).click();</SCRIPT></s:if>