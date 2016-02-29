

/**这里的参数分别是：1.总数量 2.总页数 3.当前页 4.关键参数(指定分页的板块)**/
function JudgeShowSplitPage(RowCount, PageCount, ShowPage,parameter){

    //如果ShowPage>=2时候，开启"上一页"的点击事件
    if(ShowPage>=2){
         $("input[name="+parameter+"PreviousPage]").removeAttr("disabled");
         $("input[name="+parameter+"PreviousPage]").attr("page",ShowPage-1);
         //同时首页按钮开启
         $("input[name="+parameter+"FirstPage]").removeAttr("disabled");

    }
    else{
    	
         $("input[name="+parameter+"PreviousPage]").attr("disabled","disabled");
         $("input[name="+parameter+"FirstPage]").attr("disabled","disabled");

    }
    //如果ShowPage<PageCount，那么就开启"下一页"的点击事件
    if(ShowPage<PageCount){
        $("input[name="+parameter+"NextPage]").removeAttr("disabled");
        $("input[name="+parameter+"NextPage]").attr("page",ShowPage*1+1*1);

    } else{
        $("input[name="+parameter+"NextPage]").attr("disabled","disabled");

    }

    if(ShowPage!=PageCount){
        //尾页按钮开启
    	
        $("input[name="+parameter+"LastPage]").removeAttr("disabled");


    }else{
    	
        $("input[name="+parameter+"LastPage]").attr("disabled","disabled");


    }

    $("input[name="+parameter+"LastPage]").attr("page",PageCount);




}