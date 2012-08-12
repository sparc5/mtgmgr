<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<s:form id="meetingRoomForm" name="meetingRoomForm" cssClass="myForm" action="createResult_MeetingRoom">
<fieldset>
	<legend>Meeting room detail</legend>
<div id="formResult" class="uiResult ui-widget-content ui-corner-all" style="display: none;"></div>
	<table cellspacing="0" cellpadding="0" border="0" style="width:100%;border-collapse:collapse;">
		<tr>
			<td class="formLabel">
				<label for="meetingRoomName"><s:text name="label.meetingroom.meetingRoomName"/>:</label>
			</td>
			<td class="formField">
				<s:textfield id="meetingRoomName" name="meetingRoomName" cssClass="text_input" key="label.meetingroom.meetingRoomName"/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="category"><s:text name="label.meetingroom.category"/>:</label>
			</td>
			<td class="formField">
				<s:select
					label="Category"
					id="category"
					name="category"
					headerKey="Open" headerValue="Open"
					list="#{'Closed':'Closed'}"
					value="selectedCategory"
				/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="tor"><s:text name="label.meetingroom.tor"/>:</label>
			</td>
			<td class="formField">
				<sjr:tinymce
					id="tor"
					name="tor"
					contentCss="css/custom_content.css"
					rows="8"
					cols="50"
					editorLocal="en"
					editorTheme="advanced"
					editorSkin="o2k7"
					toolbarAlign="center"
					toolbarLocation="top"
						toolbarButtonsRow1="bold,italic,underline,strikethrough,undo,redo,cleanup,bullist,numlist,link,unlink,anchor"
					toolbarButtonsRow2=' '
					toolbarButtonsRow3=' '
					toolbarButtonsRow4=' '
						plugins="safari,spellchecker,pagebreak,style,layer,table,save,advhr,advimage,advlink,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template"
					wrap="true"
					onSaveTopics="submitRichtextForm"
				/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="secretariatId"><s:text name="label.meetingroom.secretariat"/>:</label>
			</td>
			<td class="formField">
    			<sj:autocompleter
    				id="secretariatId"
    				name="secretariatId"
    				list="%{users}"
    				listKey="userid"
    				listValue="username"
    				selectBox="false"
    			/>
			</td>
		</tr><tr>
			<td align="center" valign="middle" colspan="2">
				<s:a cssClass="button no-underline" href="#" struts_action="null" onclick="$.publish('newDialogClick',[this])"><s:text name="button.meetingroom.members"/></s:a>
<sj:submit key="button.common.submit" align="center" cssClass="button" targets="formResult" effect="fade" effectMode="show" onEffectCompleteTopics="hideTarget"/>
<s:reset key="button.common.reset" align="center" cssClass="button"/>
			</td>
		</tr>
	</table>
</fieldset>
</s:form>