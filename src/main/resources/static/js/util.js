/*62进制转为日期*/
function _62_to_date(str) {
	var scale = 62;
	var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	var num = 0;
	for (var i = 0; i < str.length; i++)
		num += chars.indexOf(str.charAt(i)) * (Math.pow(scale, str.length - i - 1));
	return new Date(num).toLocaleString();
}
/*随机文件密码*/
function randomFilePwd() {
	var chars = "0123456789abcdefghijklmnopqrstuvwxyz";
	var result = "";
	for (var i = 0; i < 4; i++) {
		result += chars[Math.floor(Math.random() * chars.length)]
	}
	return result;
}
/*时间戳转日期*/
function time2Date(time) {
	var now = new Date(time * 1000);
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	month = month < 10 ? ("0" + month) : month;
	day = day < 10 ? ("0" + day) : day;
	hour = hour < 10 ? ("0" + hour) : hour;
	minute = minute < 10 ? ("0" + minute) : minute;
	second = second < 10 ? ("0" + second) : second;
	return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
}
/*日期转时间戳*/
function date2Time(date) {
	var reg = /^\d{4,14}$/;
	if (!reg.test(date)) {
		return -1;
	}
	var len = date.length;
	if (len == 4) {
		date += "-01-01 00:00:00";
	} else if (len == 6) {
		date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-01 00:00:00";
	} else if (len == 8) {
		date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6.8) + " 00:00:00";
	} else if (len == 10) {
		date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":00:00";
	} else if (len == 12) {
		date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":00";
	} else if (len == 14) {
		date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
	} else {
		return -1;
	}
	date += " GMT+8";
	var d = new Date(date);
	return d.getTime() / 1000;
}
/*字节转大小*/
function bytes2Size(size) {
	if (size < (1 << 10))
		return size + "B";
	if (size < (1 << 20))
		return (size / (1 << 10)).toFixed(2) + "KB";
	if (size < (1 << 30))
		return (size / (1 << 20)).toFixed(2) + "MB";
	return (size / (1 << 30)).toFixed(2) + "GB";
}
/*大小转字节*/
function size2Bytes(size) {
	size = size.toLocaleLowerCase();
	var reg = /^((\d*[b|\d])|(\d*\.?\d*[k|m|g]))$/;
	if (!reg.test(size)) {
		return -1;
	}
	var len = size.length;
	var end = size.substring(len - 1, len);
	if (end == "b") {
		size = size.substring(0, len - 1);
	} else if (end == "k") {
		size = (size.substring(0, len - 1)) * (1 << 10);
	} else if (end == "m") {
		size = (size.substring(0, len - 1)) * (1 << 20);
	} else if (end == "g") {
		size = (size.substring(0, len - 1)) * (1 << 30);
	}
	size = parseInt(size);
	return size;
}
/*是正整数*/
function isIntNum(num) {
	var reg = /^\d{1,}$/;
	return reg.test(num);
}
/*是文件密码*/
function isFilePwd(pwd) {
	var reg = /^[0-9a-z]{4}$/;
	return reg.test(pwd);
}
/*是文件密码*/
function isIP(ip) {
	var reg = /((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})(\.((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})){3}/g;
	return reg.test(ip);
}
/*获取文件后缀名*/
function getFileSuffix(name) {
	if (name == undefined) {
		return "";
	}
	var a = name.lastIndexOf(".") + 1;
	return a == 0 ? "" : name.substr(a).toLowerCase();
}
/*post跳转*/
function StandardPost(url, args) {
	var body = $(document.body),
		form = $("<form method='post'></form>"),
		input;
	form.attr({
		"action" : url
	});
	$.each(args, function(key, value) {
		input = $("<input type='hidden'>");
		input.attr({
			"name" : key
		});
		input.val(value);
		form.append(input);
	});
	form.appendTo(document.body);
	form.submit();
	document.body.removeChild(form[0]);
}
/*预览文件*/
function previewFile() {
	var e = window.event;
	var t = $(e.target || e.srcElement);
	var tr = t.parent().parent();
	var name = tr.attr("name");
	var uuid = tr.attr("uuid");
	if ($("#tipsDiv").attr("previewId") == uuid) {
		tips(); //关闭
		return false;
	}
	var suffix = getFileSuffix(name);
	var text = [ "txt", "text", "ini", "inf", "java", "php", "c", "cpp", "py", "html", "htm", "css", "js", "vbs", "jsp", "bat", "sql" ];
	var image = [ "png", "jpg", "jpeg", "bmp", "gif" ];
	var office = [ "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf", "zip", "rar", "gz" ];
	/* 文本预览 */
	if (text.includes(suffix)) {
		$.ajax({
			url : "/file/preview/" + uuid,
			type : "get",
			contentType : "application/json;charset=UTF-8",
			success : function(data) {
				var reg = new RegExp("<", "g");
				var h = "<pre id='previewText'>" + data.replace(reg, "&lt;") + "</pre>";
				tips(t, h);
			},
			error : function(e) {
				tips(t, "网络异常，请重试！");
			}
		});
		$("#tipsDiv").attr("previewId", uuid);
		return false;
	}
	/* office预览 */
	if (office.includes(suffix)) {
		var h = "<a href='http://ow365.cn/?i=20566&furl=http://404z.cn:8080/file/download/" + uuid + "' target='_blank'>点击预览文件</a>";
		tips(t, h);
		$("#tipsDiv").attr("previewId", uuid);
		return false;
	}
	/* 图片预览 */
	if (image.includes(suffix)) {
		var h = "<div id='previewImg'><img src='/file/preview/" + uuid + "' onClick='clickImg(\"/file/preview/" + uuid + "\")'></div>";
		tips(t, h);
		$("#tipsDiv").attr("previewId", uuid);
		return false;
	}
	/* 无法预览 */
	tips(t, "文件无法预览，请下载。");
	$("#tipsDiv").attr("previewId", uuid);
}
/*点击图片全屏预览*/
function clickImg(src) {
	var h = "<div id='bigImgShade'><img id='bigImg' src='" + src + "'></div>";
	$("body").append(h);
	$("#bigImgShade").click(function() {
		$("#bigImgShade").remove();
	});
}
/*分享文件 */
function shareFile() {
	var e = window.event;
	var t = $(e.target || e.srcElement);
	if (tipsTest(t) == true) {
		tips();
		return false;
	}
	var tr = t.parent().parent();
	var isfolder = (tr.attr("isfolder") == 1 || tr.attr("isfolder") == "Y") ? true : false;
	var timestamp = tr.attr("timestamp");
	var name = tr.attr("name");
	var pwd = tr.attr("pwd");
	var url = window.location.protocol + "//" + window.location.host + (isfolder ? "/f/" : "/z/") + timestamp;
	var link = url;
	if (pwd == 1) {
		link += " 密码：未知";
	} else if (pwd.length == 4) {
		link += " 密码：" + pwd;
	}
	var text = "分享文件" + (isfolder ? "夹" : "") + "【" + name + "】 链接地址： " + link;
	var h = "<table><tr><td>分享链接&ensp;</td><td><label class='form-control'>" + link
		+ "</label></td><td>&ensp;<button class='btn btn-info' id='copyLinkBtn' onClick='copyLink(\"#copyLinkBtn\",\"" + text + "\")'>复制</button></td></tr></table>";
	tips(t, h);
}
/*分享用户 */
function shareUser() {
	var e = window.event;
	var t = $(e.target || e.srcElement);
	if (tipsTest(t) == true) {
		tips();
		return false;
	}
	var tr = t.parent().parent();
	var id = tr.attr("userid");
	var name = tr.attr("username");
	var link = window.location.protocol + "//" + window.location.host + "/u/" + id;
	var text = "分享用户【" + name + "】 链接地址： " + link;
	var h = "<table><tr><td>分享链接&ensp;</td><td><label class='form-control'>" + link
		+ "</label></td><td>&ensp;<button class='btn btn-info' id='copyLinkBtn' onClick='copyLink(\"#copyLinkBtn\",\"" + text + "\")'>复制</button></td></tr></table>";
	tips(t, h);
}
/*复制链接*/
function copyLink(btnId, str) {
	var clipboard = new ClipboardJS(btnId, {
		text : function() {
			return str;
		}
	});
	clipboard.on("success", function(e) {
		$(btnId).text("成功");
		clipboard.destroy();
	});
	clipboard.on("error", function(e) {
		$(btnId).text("失败");
	});
}
/*下载文件夹*/
function downloadFolder() {
	var e = window.event;
	var t = $(e.target || e.srcElement);
	var tr = t.parent().parent();
	var uuid = tr.attr("uuid");
	if ($("#tipsDiv").attr("downloadFolderId") == uuid) {
		tips(); //关闭
		return false;
	}
	$.ajax({
		url : "/file/downloadFolder/" + uuid,
		type : "post",
		contentType : "application/json;charset=UTF-8",
		dataType : "json",
		success : function(data) {
			var text = data.data;
			if (data.code == 0) {
				var url = "/file/downloadPackage/" + text;
				var h = "<a href='" + url + "' target='_blank'>如果没有弹出下载页面，请点击此处下载。</a>";
				tips(t, h);
				window.open(url);
			} else if (data.code == 2003) { //文件过大
				var h = "提示：文件过大，无法打包下载！<br>如果没有弹出页面，请复制本页内容。<br>" + text;
				tips(t, h);
				var newwindow = window.open(url);
				newwindow.document.write(text);
			} else {
				tips(t, data.msg);
			}
		},
		error : function(e) {
			tips(t, "网络异常，请重试！");
		},
		complete : function(e) {
			$("#tipsDiv").attr("downloadFolderId", uuid);
		}
	});
}
/*打包下载*/
function downloadPackage() {
	var list = getCheckBoxValue();
	if (list.length == 0) {
		showTips(null, "提示：文件夹不可打包下载！如需下载整个文件夹请点击文件夹右侧的下载按钮！");
		return false;
	}
	$.ajax({
		url : "/file/downloadPackage",
		type : "post",
		contentType : "application/json;charset=UTF-8",
		dataType : "json",
		data : JSON.stringify(list),
		success : function(data) {
			var text = data.data;
			if (data.code == 0) {
				var url = "/file/downloadPackage/" + text;
				showTips(null, "提示：文件夹不可打包下载！如需下载整个文件夹请点击文件夹右侧的下载按钮！<br><a href='" + url + "' target='_blank'>如果没有弹出下载页面，请点击此处下载。</a>", "success");
				window.open(url);
			} else if (data.code == 2003) { //文件过大
				showTips(null, "提示：文件过大，无法打包下载！<br>如果没有弹出页面，请复制本页内容。<br>" + text, "success");
				var newwindow = window.open(url);
				newwindow.document.write(text);
			} else {
				showTips(null, data.msg);
			}
		},
		error : function(e) {
			showTips(null, "网络异常，请重试！", "warning");
		}
	});
}
/*获取选中的checkbox */
function getCheckBoxValue(name) {
	var list = [];
	$.each($("input[name='idcb']:checked"), function() {
		var val = $(this).val();
		if (val.length != 0) {
			list.push(val);
		}
	});
	return list;
}
/*显示提示信息 */
function showTips(name, msg, color = "danger") {
	$("#showTips").remove();
	var div = "<div id=\"showTips\" class=\"alert alert-dismissable alert-" + color + "\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">×</button><span>" + msg + "</span></div>";
	$("body").prepend(div);
	$(".form-control").css("background", "#fff");
	$("#" + name).css("background", "#faa");
	$("#" + name).focus();
}
/*获取用户类型*/
function userType2Str(type) {
	if (type == 0) {
		return "已删除";
	} else if (type == 1) {
		return "未注册";
	} else if (type == 2) {
		return "管理员";
	} else if (type == 3) {
		return "教师";
	} else if (type == 4) {
		return "学生";
	}
}
/*IP转十进制*/
function ip2Long(str) {
	var s = str.split(".");
	return parseInt(s[0] * 256 * 256 * 256) + parseInt(s[1] * 256 * 256) + parseInt(s[2] * 256) + parseInt(s[3]);
}
/*十进制转IP*/
function long2Ip(n) {
	return (n >> 24 & 0xFF) + "." + (n >> 16 & 0xFF) + "." + (n >> 8 & 0xFF) + "." + (n & 0xFF);
}
/*IP地址转地址*/
function ip2Addr(id, ip) {
	$.ajax({
		url : "http://restapi.amap.com/v3/ip?key=2e6e2d6aa617a95d625f7c6333c1ddf8&ip=" + ip,
		type : "get",
		contentType : "application/json;charset=UTF-8",
		dataType : "json",
		success : function(data) {
			var addr = data.province + data.city;
			if (addr.length == 0) {
				$("#" + id).text("保留地址");
			} else {
				$("#" + id).text(addr);
			}
		}
	});
}