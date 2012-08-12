<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags" %>
<s:form id="meetingRoomForm" name="meetingRoomForm" cssClass="myForm ui-widget-header height100 noBorder" action="_MeetingRoom_createResult">
<fieldset class="noBorder">
	<legend style="padding-top: 5px; padding-left: 5px;">Meeting room detail</legend>
<div id="formResult" class="uiResult ui-widget-content ui-corner-all" style="display: none;"></div>
	<table cellspacing="12" cellpadding="0" border="0" class="width100">
		<tr>
			<td class="formLabel">
				<label for="meetingRoomName"><s:text name="label.meetingroom.meetingRoomName"/>:</label>
			</td>
			<td class="formField">
				<s:textfield id="meetingRoomName" name="meetingRoomName" key="label.meetingroom.meetingRoomName" size="82" cssClass="text_input ui-widget-content" cssStyle="padding:4px"/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="category"><s:text name="label.meetingroom.category"/>:</label>
			</td>
			<td class="formField">
				<s:select
					cssClass="ui-widget-content ui-state-default"
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
					rows="10"
					cols="80"
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
				<s:textfield id="secretariatId" name="secretariatId" key="label.meetingroom.secretariat" size="82" cssClass="text_input ui-widget-content" cssStyle="padding:4px"/>
			</td>
		</tr><tr>
			<td class="formLabel">
				<label for="members"><s:text name="label.meetingroom.members"/>:</label>
			</td>
			<td class="formField">
				<s:select
					cssClass="ui-widget-content ui-state-default"
					label="Users"
					id="users"
					name="users"
					list="#{'userA':'UserA'}"
				/>&nbsp;&nbsp;&nbsp;
				<s:select
					cssClass="ui-widget-content ui-state-default"
					label="Groups"
					id="groups"
					name="groups"
					list="#{'group01':'Group01'}"
				/>
			</td>
		</tr><tr>
			<td align="center" valign="middle" colspan="2" style="padding-top:12px;">
			<span class="ui-button-text"><s:submit cssClass="button ui-button ui-widget ui-state-default ui-button-text-only" key="button.common.submit" align="center" targets="formResult" effect="fade" effectMode="show" onEffectCompleteTopics="hideTarget"/></span><span class="ui-button-text"><s:reset cssClass="button ui-button ui-widget ui-state-default ui-button-text-only" key="button.common.reset" align="center"/></span>
			</td>
		</tr>
	</table>
</fieldset>
</s:form>