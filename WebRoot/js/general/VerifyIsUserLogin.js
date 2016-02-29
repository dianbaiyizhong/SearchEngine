
function isUserLogin() {

	var statu = ""; 
    $.ajax({
                type:"post",
                url:"/MicroClass/general/IsUserLoginServlet",
                dataType:"text/xml",
                async:false,
               
                
                success:function(data) {

                    if (data == "isNotLoginUser") {
                    	statu = false
                    	
                    } else if(data =="isLoginUser") {
                    	statu =  true;
                    }
                }
    
            }
    )
return statu;

}





