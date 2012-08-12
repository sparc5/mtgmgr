<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="#parameters.action[0] == 'unsubscribe'">Uns</s:if><s:elseif test="#parameters.action[0] == 'subscribe'">S</s:elseif>ubscription to this meeting room is<s:if test="not result"> not</s:if> successful.