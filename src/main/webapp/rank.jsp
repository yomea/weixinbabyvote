<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp" %>
<!doctype html>
<html>
	<base href="<%=basePath%>">
	<head>
		<meta charset="utf-8">
		<title>长安宝贝--微信投票</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<link rel="stylesheet" href="css/weui.css" />
		<link rel="stylesheet" href="css/weui2.css" />
		<link rel="stylesheet" href="css/weui3.css" />
		<script src="js/zepto.min.js"></script>
		<script src="js/swipe.js"></script>
			
		<script>
			$(function() {
				
				//
				$('.weui_tab').tab({
				    defaultIndex: 1,
				    activeClass:'acitvity-tab-bottom',
				    onToggle:function(index){
				    	if(index == 0){
				    		window.location.href='baby/index';
				    	}else if(index == 2){
				    		window.location.href='sign.jsp';
				    	}else if(index == 3){
				    		window.location.href='activity/query';
				    	}
				    }
				});

			});
		</script>
	</head>

	<body ontouchstart style="background-color: #f8f8f8;">
		
		<div class="rank-list-div pd10">
			<c:forEach items="${users.data }" var="user">
				<div class="rank-div-unit weui-flex">
					<div class="weui-flex-item">
						<a href="baby/sortInfo/${user.id}">
							<img with="200" height="199" src="${fn:split(user.picUrl,',')[0] }" class="w100" />
						</a>
					</div>
					<div class="weui-flex-item text-center">
						<div>${user.userName }</div>
						<div>${user.voteNum }票</div>
					</div>
					<div class="weui-flex-item text-center">
						<div>${user.orderCode }</div>
						<div><a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_warn">给TA投票</a></div>
					</div>
				</div>
			</c:forEach>
			<%-- <div class="rank-div-unit weui-flex">
				<div class="weui-flex-item">
					<a href="rankDetail.html">
						<img src="img/people2.jpg" class="w100" />
					</a>
				</div>
				<div class="weui-flex-item text-center">
					<div>琪琪</div>
					<div>1218票</div>
				</div>
				<div class="weui-flex-item text-center">
					<div>001</div>
					<div><a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_warn">给TA投票</a></div>
				</div>
			</div> --%>
			
		</div>
		
		<div class="weui_cells_title" style="height:45px;">&nbsp;</div>
		<div class="weui_tab tab-bottom">
			<div class="weui_tabbar ">
				<a href="javascript:;" class="weui_tabbar_item">
					<div class="weui_tabbar_icon">
						<img src="img/pic-home.png" alt="">
					</div>
					<p class="weui_tabbar_label">首页</p>
				</a>
				<a href="javascript:;" class="weui_tabbar_item acitvity-tab-bottom">
					<div class="weui_tabbar_icon">
						<img src="img/pic-rank-sel.png" alt="">
					</div>
					<p class="weui_tabbar_label">排名</p>
				</a>
				<a href="javascript:;" class="weui_tabbar_item">
					<div class="weui_tabbar_icon">
						<img src="img/pic-sign.png" alt="">
					</div>
					<p class="weui_tabbar_label">我要报名</p>
				</a>
				<a href="javascript:;" class="weui_tabbar_item">
					<div class="weui_tabbar_icon">
						<img src="img/pic-activity.png" alt="">
					</div>
					<p class="weui_tabbar_label">活动说明</p>
				</a>
			</div>
		</div>

	</body>

</html>