
$(document).ready(function($){
	// 登录链接事件
	$("#loginLink").click(function(){
		// 获取登录窗体代码
		var loginHtml = $("#loginHtml").html();
		showLayer(loginHtml,500,300,closeCallback);

		// 登录表单处理逻辑
		$("#loginSubmitBtn").click(function(){
			var username = $("#username").val().trim();
			var password = $("#password").val().trim();
			if(username ==="" || password === ""){
				$(".login p").html("亲,用户名或密码不能为空！");
				return;
			}
			else{
				$.ajax({
					url : "/xiaoyueyue/Login",
					type: "post",
					data:{
						username: username,
						password: password
					},
					success : function(data){
						var msg = data.msg;
						if(msg.indexOf("/")!=-1){
							//浏览器跳转
							$(location).attr('href', msg);
						}
						else{
							$("#username").val("");
							$("#password").val("");
							// 登录失败
							// $(".login p").html(msg);
							error_toast('登录状态提醒',msg)
						}
					}
				});
			}
		});
	});

	// 处理注册逻辑
	$("#regeLink").click(function(){
		// 获取注册窗体代码
		var regeHtml = $("#regeHtml").html();
		showLayer(regeHtml,500,400,closeCallback);

		// 注册表单校验
		$("#regeSubmitBtn").click(function(){
			var user = $("#user").val().trim();
			var pwd = $("#pwd").val().trim();
			var rpwd = $("#rpwd").val().trim();
			var email = $("#email").val().trim();
			var reg = /^\w+@\w+(\.\w)+/ ;
			if( user === "" || pwd === "" || rpwd === "" || email === ""){
				error_toast('账号格式校验',"亲,用户名,密码,邮箱不允许为空!");
				// $(".login p").html("亲,用户名,密码,邮箱为必填字段！");
				return;
			}
			else if(pwd != rpwd){
				// $(".login p").html("两次输入的密码不一致！");
				error_toast('账号格式校验','两次输入的密码不一致!');
				$("#pwd").val("");
				$("#rpwd").val("");
				return;
			}
			else if(!reg.test(email)){
				// $(".login p").html("电子邮箱的格式有误！");
				error_toast('登录状态提醒','电子邮箱格式有误!');
				return;
			}
			else{
				$.ajax(
						{
							url: "/xiaoyueyue/Register",
							type : "post",
							data :{
								username : user,
								password : pwd,
								email : email	
							},
							success : function(data){
								var msg = data.msg;
								// $(".login p").html(msg);
								if (msg.search('成功')!=-1){
									success_toast('注册状态提醒',msg);
								}
								else{
									error_toast('注册状态提醒',msg);
								}
							}
						}
						);
				
			}
		});
	});
    /**
     * 错误toast弹窗信息
     */
	function error_toast(title,content){
		$.toast({
			heading : title,
			icon : 'error',
			text: content,
			hideAfter : 3000, // 5秒后自动关闭
			allowToastClose : false,
			stack : false,
			position : 'mid-center',
			showHideTransition : 'fade'
		});
	}
	/**
	 * 登录成功弹窗提醒
	 */
	function success_toast(title,content){
		$.toast({
			heading : title,
			icon : 'success',
			text: [
		        content,
		        '注册信息已通过邮件发送到您的邮箱中',
		        '请注意查收'
		    ],
			hideAfter : 5000, // 5秒后自动关闭
			stack : false,
			position : 'mid-center', // 弹窗的位置 居中显示
			allowToastClose: false ,// 没有关闭的x
			showHideTransition: 'fade' //'slide'
		})	
	}
	// 弹出层关闭回调函数
	function closeCallback(){
		$(".error-msg").html("");
	}	

	// 显示弹出层
	function showLayer(html,width,height,closeCallback){
		// 显示弹出层遮罩
		$("#layer-mask").show();
		// 显示弹出层窗体
		$("#layer-pop").show();
		// 设置弹出层窗体样式
		$("#layer-pop").css({
			width : width,
			height : height
		});
		// 填充弹出层窗体内容
		$("#layer-content").html(html);
		// 弹出层关闭按钮绑定事件
		$("#layer-close").click(function(){
			// 弹出层关闭
			hideLayer();
			// 关闭的回调函数
			closeCallback();
		});
	}

	// 隐藏弹出层
	function hideLayer(){
		// 弹出层关闭
		$("#layer-mask").hide();
		$("#layer-pop").hide();
	}

});