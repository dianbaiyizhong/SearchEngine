function AddMouseOverCss(ID, ImgUrl, Color) {
	if (ImgUrl == "") {
		document.getElementById(ID).style.backgroundColor = Color;
	} else {
		document.getElementById(ID).style.backgroundImage = 'url("picture/index/SearchWindow/SearchFloorBG.png")';
	}
}


function AddMouseOutCss(ID, ImgUrl, Color) {
	if (ImgUrl == "") {
		document.getElementById(ID).style.backgroundColor = Color;
	} else {
		document.getElementById(ID).style.backgroundImage = 'url("picture/index/SearchWindow/SearchFloorBG.png")';
	}
	if(Color==""){
		document.getElementById(ID).style.backgroundColor = "";

	}
}