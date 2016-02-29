<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>搜索引擎</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<!-- jquert_1.9.1 -->
<script type="text/javascript" src="js/jquery-1.9.1.js">
	
</script>


<script>
	var jquery1_9_1 = jQuery.noConflict(true);
</script>

<!-- jquert_1.7.1 -->
<script type="text/javascript" src="js/jquery-1.7.1.js">
	
</script>
<script>
	var jquery1_7_1 = jQuery.noConflict(true);
</script>

<script type="text/javascript" src="js/jquery-1.4.2.js">
	
</script>
<script>
	var jquery1_4_2 = jQuery.noConflict(true);
</script>


<!-- 通用的功能包js -->
<script type="text/javascript" src="js/general/CreateLabel.js">
<script type="text/javascript" src="js/general/util.js">

</script>
<!-- 搜索结果js，采用刀剑神域样式 -->
<script type="text/javascript" src="js/index/index.js">
	
</script>
<script type="text/javascript" src="js/index/result.js">
	
</script>
<!-- 搜索结果css，采用刀剑神域样式 -->

<link rel="stylesheet" type="text/css"
	href="style/result/css/result.css">
<link rel="stylesheet" type="text/css" href="style/result/css/icon.css">
<!-- index界面的css -->
<link rel="stylesheet" type="text/css" href="css/index/style.css">



<!-- 搜索栏的css -->

<link rel="stylesheet" type="text/css"
	href="style/searchbox/css/style.css">













</head>

<body>

    <img src="image/bg1.jpg" alt=""> 

<div class="showTextBox"></div>

<!-- 	<button onclick="Search()">搜索</button> -->
	
<div class="btn_Search" onclick="Search()"></div>
	
	<div class="text_search_div">
	     <input type="text" id="KeyWord">
	
	
	
	
	</div>



	<div class="Search">

		<div class="search_nav">
			<ul>
				<li><span class="icon-pic"></span>图片</li>
				<li><span class="icon-news"></span>新闻</li>
				<li><span class="icon-page"></span>网页</li>
				<li><span class="icon-tieba"></span>贴吧</li>
			</ul>
		</div>

		<div class="Result">

			<div class="arrow"></div>

			<div class="result_list" id="result_list">
				<ul>



				</ul>
			</div>

		</div>
	</div>







</body>
</html>
