jquery1_9_1(document).ready(function() {

	// 初始化菜单
	intSearchMenu();

	// 初始化菜单搜索按钮
	intSearchButton();

});

function intSearchButton() {

	var obj_showTextBox = jquery1_9_1(".showTextBox");
	obj_showTextBox.click(function() {

		var obj_text_search_div = jquery1_9_1(".text_search_div");

		// 点击之后，搜索栏出现，按钮消失
		obj_text_search_div.animate({
			left : getWidth()
		}, "slow");
		obj_showTextBox.animate({
			left : -200
		}, "slow");
		
		
		//显示搜索按钮
		var obj_btn_search = jquery1_9_1(".btn_Search");
		
		obj_btn_search.animate({
			left : getWidth()-750
		}, "slow");
	});

}

// 获取屏幕宽度
function getWidth() {

	return jquery1_9_1(window).width();

}
// 获取屏幕高度
function getHeight() {
	return jquery1_9_1(window).height();

}

var FirstPos = 45;
var ScrollPos = FirstPos;
var Scroll_speed = 80;
var MaxHeight = 205 * 19;
var MinHeight = 0;
// 滑动菜单的制作，定义几个变量
function scrollResultList(e) {
	ScrollPos = ScrollPos + 200 * e.detail;
	var obj_resultlist = jquery1_9_1(".result_list");
	if (ScrollPos > MaxHeight) {
		// 如果大于这个滚动的最大值那就不能再滑动了
		ScrollPos = MaxHeight;
	} else if (ScrollPos < MinHeight) {
		ScrollPos = MinHeight;
	}
	obj_resultlist.animate({
		scrollTop : ScrollPos
	}, Scroll_speed);

}

// 初始化搜索菜单
function intSearchMenu() {

	var search_nav = jquery1_9_1(".search_nav>ul>li");

	// 初始化样式
	search_nav.css("background", "rgba(255, 255, 255, 0.6)");

	search_nav.each(function() {

		var index = jquery1_9_1(this).index();
		var obj_li = jquery1_9_1(this);

		// 一开始全部是白色
		obj_li.css("background-color", "rgba(255, 255, 255, 0.6)");

		obj_li.mouseover(function() {
			// 改变颜色
			obj_li.css("background-color", "rgba(248, 177, 97, 0.9)");
			obj_li.siblings().css('background-color',
					'rgba(255, 255, 255, 0.6)');

		});

		// 设置点击事件
		obj_li.click(function() {
			obj_li.css("background-color", "rgba(248, 177, 97, 0.9)");
			obj_li.siblings().css('background-color',
					'rgba(255, 255, 255, 0.6)');

			// 获取点击的位置高度
			var click_top = obj_li.position().top;

			// 改变结果栏的位置，让他们对接起来
			var obj_Result = jquery1_9_1(".Result");

			obj_Result.css("top", click_top - 110);

			// 通过点击的索引来确定搜索类型
			if (index == 0) {
				// getResult(keyword, pageSize, currentPage, type);
			}

		});

	});

}
/**
 * 添加事件
 * 
 * @param obj
 *            对象
 * @param event
 *            事件名称
 * @param css
 *            修改的css
 */
function AddMouseListener(obj, event, css) {
	if (event == "click") {
		obj.click(function() {
			obj.css("background", css);

		});

	} else if (event == "mouseover") {
		obj.mouseover(function() {
			obj.css("background", css);
		});
	} else if (event == "mouseleave") {
		obj.mouseleave(function() {
			obj.css("background", css);

		});
	}

}

function RemoveMouseListener(obj, event) {
	obj.unbind(event);
}
