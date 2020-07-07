function upload(url, size) {
	$("#upload").append("<div id='uploadFileDiv'><div id='chooseFileDiv'><button class='btn btn-info' id='chooseFileBtn'>点击添加文件</button><br><span>或将文件拖拽到这个框内</span></div><div id='fileListDiv'><button class='btn btn-primary' id='addFileBtn'>添加文件</button>&emsp;<button class='btn btn-success' id='uploadFileBtn'>开始上传</button><div id='fileListTableDiv'><table id='fileListTable'></table></div></div><div id='fileMsg'></div></div>");
	$("#addFileBtn").click(function() {
		$("#chooseFileBtn").click();
	});
	var uploader = new plupload.Uploader({
		browse_button : "chooseFileBtn",
		url : url,
		drop_element : "uploadFileDiv",
		filters : {
			max_file_size : size,
			prevent_duplicates : true //不允许队列中存在重复文件
		}
	});
	uploader.init(); //初始化
	//文件添加进队列
	uploader.bind("FilesAdded", function(uploader, files) {
		$("#chooseFileDiv").hide();
		$("#fileListDiv").show();
		var maxLen = 100; //最大文件数量
		var len = files.length;
		var id,
			name,
			suffix,
			size,
			tr;
		for (var i = 0; i < (len < maxLen ? len : maxLen); i++) {
			id = files[i].id;
			size = files[i].size;
			if (!size) {
				uploader.removeFile(uploader.getFile(id));
				$("#fileMsg").html("不能上传空文件！");
			} else {
				name = files[i].name;
				suffix = getFileSuffix(name);
				tr = "<tr id='" + id + "'><td class='fileIco'><img class='ico ico-" + suffix + "'></td>";
				tr += "<td class='fileName'><div class='fileNameDiv'>" + name + "</div></td>";
				tr += "<td class='fileSize'>" + bytes2Size(size) + "</td>";
				tr += "<td class='fileDelete'><button class='fileDeleteBtn'>x</button></td></tr>";
				$("#fileListTable").append(tr);
				$("#" + id + " .fileDeleteBtn").click(function() {
					var tr = $(this).parent().parent();
					uploader.removeFile(uploader.getFile(tr.attr("id")));
					tr.remove();
				});
			}
		}
		if (len > maxLen) {
			$("#fileMsg").html("一次最多上传" + maxLen + "个文件，请分批上传！");
		}
	});
	//文件上传完成
	uploader.bind("FileUploaded", function(uploader, file, responseObject) {
		var response = JSON.parse(responseObject.response);
		var code = response.code;
		if (code == 0) { //成功
			var tr = $("#" + file.id);
			tr.remove();
			return false;
		}
		var msg = response.msg;
		reload(file.id); //重传
		$("#fileMsg").html(msg);
	});
	//发生错误
	uploader.bind("Error", function(uploader, errObject) {
		var code = errObject.code;
		if (code == -200) {
			var fileid = errObject.file.id;
			reload(fileid); //重传
			$("#fileMsg").html("文件上传失败，请重传！");
		} else if (code == -600) {
			$("#fileMsg").html("文件过大，不可上传！");
		} else if (code == -602) {
			$("#fileMsg").html("文件重复，不可上传！");
		}
	});
	//进度
	uploader.bind("UploadProgress", function(uploader, file) {
		$("#" + file.id).css("background", "linear-gradient(to right,#ddd " + file.percent + "%,#fff 0)");
	});
	//上传完成
	uploader.bind("UploadComplete", function(uploader, files) {
		$("#fileMsg").html("上传完成。请<a href='javascript:location.reload();'>刷新</a>。");
	});
	//上传按钮
	$("#uploadFileBtn").click(function() {
		uploader.start(); //开始上传
	});
	//重传
	function reload(fileid) {
		$("#" + fileid).css("background", "linear-gradient(to right,#f88 100%,#fff 0)");
		$("#" + fileid + " .fileDeleteBtn").html("重传");
		$("#" + fileid + " .fileDeleteBtn").unbind();
		$("#" + fileid + " .fileDeleteBtn").click(function() {
			var tr = $(this).parent().parent();
			var file = uploader.getFile(tr.attr("id"));
			var fileNative = file.getNative();
			uploader.removeFile(file);
			tr.remove();
			uploader.addFile(fileNative);
			uploader.start();
		});
	}
}