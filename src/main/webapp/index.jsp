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
					defaultIndex: 0,
					activeClass: 'acitvity-tab-bottom',
					onToggle: function(index) {
						if(index == 1) {
							window.location.href = 'baby/sortInfo';
						} else if(index == 2) {
							window.location.href = 'sign.jsp';
						} else if(index == 3) {
							window.location.href = 'activity/query';
						}
					}
				});
				
				$("#searchButton").on('click', function() {
					var val = $("#search").val();
					query(val);
				});
				
				$(".voteTAOneTick").on('click', function() {
					var id = $(this).attr("id");
					voteToBaby(id);
				});
				
			});
			
			
			function voteToBaby(id) {
				$.ajax({
					contentType:'application/x-www-form-urlencoded',
					cache:false,
					dataType:'json',
					error:function(xhr,status,error) {
						alert('数据请求失败！');
					},
					success:function(result,status,xhr) {
						if(result.successful) {
							var voteNum = parseInt($("#voteNumBox"+id).text());
							voteNum += 1;
							$("#voteNumBox"+id).text(voteNum);
						} else {
							alert(result.msg)
						}
					},
					url:'vote/' + id
				});
			}
			
			
			function query(val) {
				var currentPage = ${babyList.currentPageNum};
				var total = ${babyList.total};
				var pageSize = ${babyList.pageSize};
				
				$.ajax({
					contentType:'application/x-www-form-urlencoded',
					cache:false,
					data:{username:val},
					dataType:'json',
					error:function(xhr,status,error) {
						alert('数据请求失败！');
					},
					success:function(result,status,xhr) {
						//如果请求不成功
						if(!result.successful) {
							//如果是未授权
							if("0001" == result.code) {
								var authorUrl = result.data;
								location.href=authorUrl;
							} else {
								alert(result.data);
							}
						} else {
							//清空原来的数据
							$('#babyList1').empty();
							$('#babyList2').empty();
							var data = result.data.data;
							var index = 0;
							$.each(data, function(index, eobj) {
								var box = $('#babyList1');
								if(index % 2 == 1) {
									box = $('#babyList2');
								}
								var picUrl = eobj.picUrl;
								var picUrls = picUrl.split(",");
								generateWxopBox(picUrls[0], eobj.userName,eobj.orderCode,eobj.voteNum, box,eobj.id);
							});
							
						}
					},
					url:'weixinbabyvote/baby/query'
				});
			}
			
			function generateWxopBox(src, username,code,voteNum,box,id) {
				var wxopbox = '<div class="wxop"><div class="wxopimg"><a href="${pageContext.request.contextPath}/baby/sortInfo/' + id +'">'+
				'<img with="246" height="329" src="' + src + '"></a></div><div class="wxoptxt weui-flex"><div class='+
				'"weui-flex-item text-center">' + username + '</div><div class="weui-flex-item text-center">' + code + '</div>'+
				'</div><div class="wxopvotediv weui-flex"><div  class="weui-flex-item text-center"><span id="voteNumBox"' + id + '>' + voteNum + '</span>票</div>'+
				'<div class="weui-flex-item text-center"><a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_warn" id="' + id + '">'+
				'投TA一票</a></div></div></div>';
				$(wxopbox).appendTo(box);
			}
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
		<!-- 搜索 -->
		<div>
			<div class="weui_search_bar">
				<input type="search" class="search-input" id='search' placeholder='关键字' style="box-sizing:content-box" />
				<button id="searchButton" class="weui_btn weui_btn_mini weui_btn_primary"><i class="icon icon-4"></i></button>
			</div>
		</div>

		<div class="home-list-div">
			
						<div class="plistleft leftoptions" id="babyList1">
						
						<c:forEach items="${babyList.data}" var="baby" varStatus="status">
						<c:if test="${status.index % 2 == 0 }">
							<div class="wxop">
								<div class="wxopimg">
									<a href="${pageContext.request.contextPath }/baby/sortInfo/${baby.id}">
										<img with="246" height="329" src="${fn:split(baby.picUrl,',')[0] }">
									</a>
								</div>
								<div class="wxoptxt weui-flex">
									<div class="weui-flex-item text-center">${baby.userName }</div>
									<div class="weui-flex-item text-center">${baby.orderCode }</div>
								</div>
								<div class="wxopvotediv weui-flex">
									<div class="weui-flex-item text-center"><span id="voteNumBox${baby.id}">${baby.voteNum }</span>票</div>
									<div class="weui-flex-item text-center">
										<a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_warn voteTAOneTick" id="${baby.id}">投TA一票</a>
									</div>
								</div>
							</div>
							</c:if>
							
							</c:forEach>
						</div>
					
						<div class="plistleft rightoptions" id="babyList2">
						<c:forEach items="${babyList.data}" var="baby" varStatus="status">
						<c:if test="${status.index % 2 == 1 }">
							<div class="wxop">
								<div class="wxopimg">
									<a href="${pageContext.request.contextPath }/baby/sortInfo/${baby.id}">
										<img with="246" height="329" src="${fn:split(baby.picUrl,',')[0] }">
									</a>
								</div>
								<div class="wxoptxt weui-flex">
									<div class="weui-flex-item text-center">${baby.userName }</div>
									<div class="weui-flex-item text-center">${baby.orderCode }</div>
								</div>
								<div class="wxopvotediv weui-flex">
									<div class="weui-flex-item text-center"><span id="voteNumBox${baby.id}" >${baby.voteNum }</span>票</div>
									<div class="weui-flex-item text-center">
										<a href="javascript:;" class="weui_btn weui_btn_mini weui_btn_warn voteTAOneTick" id="${baby.id}">投TA一票</a>
									</div>
								</div>
							</div>
							
							</c:if>
							</c:forEach>
						</div>
			
			<div class="clear"></div>
		</div>

		<!--  -->
		<div class="weui_cells_title" style="height:45px;">&nbsp;</div>
		<div class="weui_tab tab-bottom">
			<div class="weui_tabbar ">
				<a href="javascript:;" class="weui_tabbar_item acitvity-tab-bottom">
					<div class="weui_tabbar_icon">
						<img src="img/pic-home-sel.png" alt="">
					</div>
					<p class="weui_tabbar_label">首页</p>
				</a>
				<a href="javascript:;" class="weui_tabbar_item">
					<div class="weui_tabbar_icon">
						<img src="img/pic-rank.png" alt="">
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