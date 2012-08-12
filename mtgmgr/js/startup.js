$(document).ready(function() {
	$.timeago.settings.allowFuture = true;
	$outerLayout = $("body").layout($outerLayoutOptions);
	$mtgRmLayout = $("div#meetingRoom").layout($mtgRmLayoutOptions);
	$personalInnerLayout = $("div#personalInner").layout($personalInnerLayoutOptions);
	mtgmgr.layout.showContent(mtgmgr.views.personal);

	$("div.inner-south div.navPanel :text[class*=search_box]").clearSearch();
	$("div#presentation :text[class*=search_box]").clearSearch({
		top: -3,
		backgroundClass: "search_box_bg"
	});
	$(":text.search_box").labelify();
});