function closeSelf(formname){
	document.forms[formname].submit();
	window.onunload = refreshParent;
	function refreshParent() {
		window.opener.location.reload();
	}
	window.close();
}

