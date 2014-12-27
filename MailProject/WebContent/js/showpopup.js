function showpopup(url, width, height) {
	var left = (screen.width - width) / 2;
	var top = (screen.height - height) / 2;
	
	newwindow = window.open(url, "Browse files to upload", 'width='+width+',height='+height+',left='+left+',top='+top);
}