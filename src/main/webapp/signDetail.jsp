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
				//轮播
				$('#slide1').swipeSlide({
					autoSwipe: true, //自动切换默认是
					speed: 3000, //速度默认4000
					continuousScroll: true, //默认否
					transitionType: 'cubic-bezier(0.22, 0.69, 0.72, 0.88)', //过渡动画linear/ease/ease-in/ease-out/ease-in-out/cubic-bezier
					lazyLoad: true, //懒加载默认否
					firstCallback: function(i, sum, me) {
						me.find('.dot').children().first().addClass('cur');
					},
					callback: function(i, sum, me) {
						me.find('.dot').children().eq(i).addClass('cur').siblings().removeClass('cur');
					}
				});

				//
				$('.weui_tab').tab({
					defaultIndex: 2,
					activeClass: 'acitvity-tab-bottom',
					onToggle: function(index) {
						if(index == 0) {
							window.location.href = 'baby/index';
						} else if(index == 1) {
							window.location.href = 'baby/sortInfo';
						} else if(index == 3) {
							window.location.href = 'activity/query';
						}
					}
				});

			});
		</script>
	</head>

	<body ontouchstart style="background-color: #f8f8f8;">

		<div class="slide" id="slide1">
			<ul>
				<li>
					<a href="#">
						<img src="img/pic01.jpg" alt="">
					</a>
				</li>
				<li>
					<a href="#">
						<img src="img/pic02.jpg" />
					</a>
				</li>
				<li>
					<a href="#">
						<img src="img/pic03.jpg" />
					</a>
				</li>
			</ul>
			<div class="dot">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>

		<div class="pd10">

			<div class="rank-div-unit weui-flex">
				<div class="weui-flex-item">
					<img with="300", height="199" src="${fn:split(user.picUrl,',')[0] }" class="w100" />
				</div>
				<div class="weui-flex-item text-center">
					<div class="weui-flex">
						<div class="weui-flex-item">
							<div>${user.userName }</div>
						</div>
						<div class="weui-flex-item">
							<div>${user.orderCode }</div>
						</div>
					</div>
					<div class="weui-flex-item text-center pdt-2em">
						<div class="color-red">${user.examineState }</div>
					</div>
				</div>
				
			</div>

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
				<a href="javascript:;" class="weui_tabbar_item">
					<div class="weui_tabbar_icon">
						<img src="img/pic-rank.png" alt="">
					</div>
					<p class="weui_tabbar_label">排名</p>
				</a>
				<a href="javascript:;" class="weui_tabbar_item acitvity-tab-bottom">
					<div class="weui_tabbar_icon">
						<img src="img/pic-sign-sel.png" alt="">
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