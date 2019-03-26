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
				
				
				 // 允许上传的图片类型
			    var allowTypes = ['image/jpg', 'image/jpeg', 'image/png', 'image/gif'];
			    // 3MB
			    var maxSize = 3 * 1024 * 1024;
			    // 图片最大宽度
			    var maxWidth = 300;
			    // 最大上传图片数量
			    var maxCount = 6;
			    
			    var imgUrls = [];
			    
			    $('#uploaderInput').on('change', function (event) {
			        var files = event.target.files;
			 
			        // 如果没有选中文件，直接返回
			        if (files.length === 0) {
			            return;
			        }
			 
			        for (var i = 0, len = files.length; i < len; i++) {
			            var file = files[i];
			            var reader = new FileReader();
			 
			            // 如果类型不在允许的类型范围内
			            if (allowTypes.indexOf(file.type) === -1) {
			               alert( '该类型不允许上传');
			                continue;
			            }
			 
			            if (file.size > maxSize) {
			                alert( '图片太大，不允许上传');
			                continue;
			            }
			 
			            if ($('.weui_uploader_file').length >= maxCount) {
			                alert('最多只能上传' + maxCount + '张图片');
			                return;
			            }
			 
			            reader.onload = function (e) {
			                var img = new Image();
			                img.onload = function () {
			                    // 不要超出最大宽度
			                    var w = Math.min(maxWidth, img.width);
			                    // 高度按比例计算
			                    var h = img.height * (w / img.width);
			                    var canvas = document.createElement('canvas');
			                    var ctx = canvas.getContext('2d');
			                    // 设置 canvas 的宽度和高度
			                    canvas.width = w;
			                    canvas.height = h;
			                    ctx.drawImage(img, 0, 0, w, h);
			                    var base64 = canvas.toDataURL('image/png');
			                    imgUrls.push(base64);
			                    // 插入到预览区
			                   /*  var $preview = $('<li class="weui_uploader_file weui_uploader_status" style="background-image:url(' + base64 + ')"><div class="weui_uploader_status_content">上传中</div></li>'); */
			                    var $preview = $('<li class="weui_uploader_file" style="background-image:url(' + base64 + ')"></li>');
			                    $('.weui_uploader_files').append($preview);
			                    var num = $('.weui_uploader_file').length;
			                    $('#uploaderInput').text(num + '/' + maxCount);
			                };
			 
			                img.src = e.target.result;
			               
			                
			                
			            };
			            reader.readAsDataURL(file);
			 
			        }
			    }); 
			     
			     $("#submitenter").on('click', function() {
			    	 var babyName = $.trim($('#babyName').val());
			    	 var linkmanName = $.trim($('#linkmanName').val());
			    	 var linkmanPhonNum = $.trim($('#linkmanPhonNum').val());
			    	 
			    	 if(babyName == "") {
			    		 alert('宝贝名称不能为空');
			    		 return false;
			    	 }
			    	 if(linkmanName == "") {
			    		 alert('联系人名称不能为空');
			    		 return false;
			    	 }
			    	 if(linkmanPhonNum == "") {
			    		 alert('联系人电话号码不能为空');
			    		 return false;
			    	 }
			    	 if(imgUrls.length == 0) {
			    		 alert('请上传图片');
			    		 return false;
			    	 }
			    	 if(imgUrls.length > 6) {
			    		 alert('上传的图片不能超过6张');
			    		 return false;
			    	 }
			    	 
			    	 var params = JSON.stringify({"base64":imgUrls.join('@@'),"babyName":babyName,"linkmanName":linkmanName,"linkmanPhonNum":linkmanPhonNum});
			    	 $.ajax({
			    		 	url:"baby/enter",
							contentType:'application/json;charset=utf-8',
							cache:false,
							data:params,
							dataType:'json',
							type: "POST",
							error:function(xhr,status,error) {
								alert('数据请求失败！');
							},
							success:function(result,status,xhr) {
								if(result.successful) {
									$('#lookResult').attr('href',"baby/lookEnterResult/" + result.data.id).css("display","block");
								} else {
									alert('上传失败');
								}
							}
							
		                })
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

			<div class="weui_cells weui_cells_form" style="margin-top: inherit;padding-bottom: 1em;">
				<div class="weui_cell">
					<div class="weui_cell_hd"><label class="weui_label">宝宝名字</label></div>
					<div class="weui_cell_bd weui_cell_primary">
						<input id="babyName" class="weui_input" />
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_bd weui_cell_primary">
						<div class="weui_uploader">
							<div class="weui_uploader_hd weui_cell">
								<div class="weui_cell_bd weui_cell_primary">上传图片，至少上传一张照片，每张小于3M！</div>
								<div class="weui_cell_ft"></div>
							</div>
							
							
							<div class="weui-gallery" id="gallery">  
					            <span class="weui-gallery__img" id="galleryImg"></span>  
					            <div class="weui-gallery__opr">  
					                <a href="javascript:" class="weui-gallery__del">  
					                    <i class="weui-icon-delete weui-icon_gallery-delete"></i>  
					                </a>  
					            </div>  
					        </div> 
							
							
							
							<div class="weui_uploader_bd">
								<ul class="weui_uploader_files" id="uploaderFiles">
									<!-- <li class="weui_uploader_file" style="background-image: url(img/people2.jpg)"></li> -->
	                                <!--<li class="weui_uploader_file weui_uploader_status" style="background-image:url(img/people2.jpg)">
	                                    <div class="weui_uploader_status_content">
	                                        <i class="weui_icon_cancel"></i>
	                                    </div>
	                                </li>-->
								</ul>
								<div class="weui_uploader_input_wrp">
									<input id="uploaderInput" class="weui_uploader_input" type="file" accept="image/*" multiple />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd"><label class="weui_label">联系人姓名</label></div>
					<div class="weui_cell_bd weui_cell_primary">
						<input id="linkmanName" class="weui_input" />
					</div>
				</div>
				<div class="weui_cell">
					<div class="weui_cell_hd"><label class="weui_label">联系人电话</label></div>
					<div class="weui_cell_bd weui_cell_primary">
						<input id="linkmanPhonNum" class="weui_input" />
					</div>
				</div>
				<div class="weui_btn_area">
		            <a id="submitenter" class="weui_btn weui_btn_primary" href="javascript:">提交报名</a>
		            <a id="lookResult" style="display:none" class="weui_btn weui_btn_primary" href="baby/lookEnterResult/">查看报名结果</a>
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