
/**
 * @param divIDString  窗口内容div的id
 * @param alertString  需要弹出的内容
 * @return
 */
function ShowAlertWindowFromDiv(divIDString,alertString){
	//打开警告窗口
    ShowAlertWindow();
    //创建一个div标签
    var NewDivNode = "<div id='"+divIDString+"'>";
    var NewNode =  $(NewDivNode).html(alertString);
   //获取Div
   var AlertWindowDivNode = $("#AlertWindow");

   NewNode.appendTo(AlertWindowDivNode);
    //3秒后隐藏
    setTimeout("HideAlertWindow()",3000);
    //隐藏后把内容删掉标签
    setTimeout(function() {  	
    	NewNode.remove();
	},3000);
	
}


function ShowAlertWindow() {

	var windNode = $("#AlertWindow");
	windNode.show("Explode");

}

function HideAlertWindow() {
	var windNode = $("#AlertWindow");
	windNode.fadeOut("slow");
}

