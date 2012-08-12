<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<font color='<s:if test="not result">red</s:if><s:else>green</s:else>'>Deletion of meeting is<s:if test="not result"> not</s:if> successful.</font>
