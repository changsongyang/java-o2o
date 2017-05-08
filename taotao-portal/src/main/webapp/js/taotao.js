var TT = TAOTAO = {
	checkLogin : function() {
		var _ticket = $.cookie("TT_TOKEN");
		if (!_ticket) {
			return;
		}
		$.ajax({
			url : "http://localhost:9990/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data) {
				if (data.status == 200) {
					var username = data.data.username;
					var html = username
							+ "，欢迎来到淘淘！<a href=\"javascript:;\" class=\"link-logout\" id=\"token\" onclick=\"TT.delTokenEvent()\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	},
	delToken : function() {
		var _ticket = $.cookie("TT_TOKEN");
		if (!_ticket) {
			return;
		}
		$.ajax({
			url : "http://localhost:9990/user/delToken/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data) {
				if (data.status == 200) {
					location.href = "/";
				}
			}
		});
	},
	delTokenEvent:function(){
		var esc = confirm("是否退出登录？");
		if (esc) {
			TT.delToken();
		}
	}
}

$(function() {
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});