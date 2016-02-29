function ClickLikeForNote(NoteID) {
	
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
		if (xmlhttp.overrideMimeType) {
			xmlhttp.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) {

		var activexName = [ "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];
		for ( var i = 0; i < activexName.length; i++) {
			try {
				xmlhttp = new ActiveXObject(activexName[i]);
				break;
			} catch (e) {
			}
		}
	}
	if (!xmlhttp) {
		alert("XMLHttpRequest对象创建失败!!");
		return;
	} else {
		// alert(xmlhttp.readyState);
	}


	xmlhttp.onreadystatechange = callbackFromClickLikeForNoteServlet;


	xmlhttp.open("POST", "/MicroClass/userFunction/ClickLikeForNoteServlet", true);
	xmlhttp.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");


	xmlhttp.send("NoteID=" + NoteID);
	
}



//回调函数
function callbackFromClickLikeForNoteServlet() {

    if (xmlhttp.readyState == 4) {
        if (xmlhttp.status == 200) {
            var domObj = xmlhttp.responseXML;
            if (domObj) {
                var messageNodes = domObj.getElementsByTagName("message");
                var textNode = messageNodes[0].firstChild;
                var responseMessage = textNode.nodeValue;
                if (responseMessage == "ModifyLikeCountForNoteSuccess") {
                	
                	alert("点赞成功");
                    
                } else if(responseMessage == "isNotLoginUser"){
                	alert("请先登录才能点赞");

                	
                }
            } else {
                alert("XML数据格式错误，原始文本内容为：" + xmlhttp.responseText);
            }
        } else {
            alert("出错了！！！");
        }
    }
}



