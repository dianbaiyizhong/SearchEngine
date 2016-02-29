/**
 * 通过查询条件获取结果
 * 
 * @param keyword
 * @param pageSize
 * @param currentPage
 * @param type
 */
function getResult(keyword, pageSize, currentPage, type) {

	jquery1_4_2.ajax({
		type : "post",
		dataType : 'json',
		// url : "search/getResult.json",
		url : "SearchServlet",
		data : "keyWord=" + keyword + "&pageSize=" + pageSize + "&currentPage="
				+ currentPage + "&type=" + type,
		success : function(json) {

			ShowResult(json);
		}
	});

}

function Search() {
	var keyword = jquery1_4_2("#KeyWord").val();

	// 点击搜索之后，隐藏搜索框
	var obj_text_search_div = jquery1_9_1(".text_search_div");
	obj_text_search_div.animate({
		left : -getWidth()
	}, "slow");

	// 隐藏搜索按钮
	var obj_btn_search = jquery1_9_1(".btn_Search");

	obj_btn_search.animate({
		left : getWidth()
	}, "slow");

	// 显示 “显示搜索栏”按钮
	var obj_showTextBox = jquery1_9_1(".showTextBox");

	obj_showTextBox.animate({
		left : 0
	}, "slow");
	getResult(keyword, 20, 1, "picture");

}

function ShowResult(data) {
	// var obj_json_result = jquery1_4_2.parseJSON(data);
	var obj_json_result = data;
	var node_div = jquery1_9_1(".result_list");
	node_div.mouseover(function() {

		document.getElementById("result_list").addEventListener(
				'DOMMouseScroll', scrollResultList, false);
	});
	node_div.mouseleave(function() {

		document.getElementById("result_list").removeEventListener(
				'DOMMouseScroll', scrollResultList);

	});

	// 获取ul标签
	var node_ul = jquery1_9_1(".result_list>ul");

	// 先删除所有标签
	node_ul.html("");
	jquery1_4_2.each(obj_json_result, function(key, value) {

		// 创建好标签li
		CreateLabelAppentToAnother("li", "result_list_li_" + key, "", node_ul);

		var obj_result_list_li = jquery1_9_1("#result_list_li_" + key);

		CreateLabelAppentToAnother("a", "", "", jquery1_9_1("#result_list_li_"
				+ key));
		CreateLabelAppentToAnother("p", "", "", jquery1_9_1("#result_list_li_"
				+ key));

		// 再创建一个div来放另外一个菜单,该菜单有访问链接等功能

		// 创建主result_tool div 第一环绕
		CreateLabelAppentToAnother("div", "result_tool_" + key, "result_tool",
				"#result_list_li_" + key);

		// 为每一个结果层设置一个点击事件，一点击就出现tool
		obj_result_list_li.click(function() {
			jquery1_9_1("#result_tool_" + key).show();
		});
		obj_result_list_li.mouseleave(function() {
			jquery1_9_1(".result_tool").hide();

		});

		// 创建指针 第二环绕
		CreateLabelAppentToAnother("div", "", "arrow_small", "#result_tool_"
				+ key);

		// 创建n个功能标签 第三环绕
		CreateLabelAppentToAnother("span", "", "result_tool_function_a",
				"#result_tool_" + key);

		// 为第一个标签设置跳转链接功能1
		var url = "window.open('" + value.url + "','_parent')"
		jquery1_9_1(".result_tool_function_a").attr("onclick", url);

		CreateLabelAppentToAnother("span", "", "result_tool_function_b",
				"#result_tool_" + key);

		CreateLabelAppentToAnother("span", "", "result_tool_function_c",
				"#result_tool_" + key);

		CreateLabelAppentToAnother("span", "", "result_tool_function_d",
				"#result_tool_" + key);

		jquery1_9_1("#result_list_li_" + key + ">a").html(value.title);
		jquery1_9_1("#result_list_li_" + key + ">p").html("<br>" + value.text);

	})
	var speed = 1000;

	jquery1_9_1("#result_list>ul>li").slideToggle(speed);
	jquery1_9_1(".result_list").show();
	jquery1_9_1(".Result").show();

}