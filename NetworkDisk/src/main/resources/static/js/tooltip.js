/*普通提示框*/
(function($, window, document) {
	'use strict';
	var pluginName = 'tooltip',
		defaults = {
			fade : false,
			fallback : '',
			align : 'autoTop',
			html : false,
			attr : 'title',
			trigger : {
				show : 'ontouchend' in document ? 'touchstart' : 'mouseenter',
				hide : 'ontouchend' in document ? 'touchend' : 'mouseleave'
			},
			delay : {
				show : 0,
				hide : 0
			}
		};
	function Plugin(el, options) {
		options = $.extend({}, defaults, options);
		var elem = $(el),
			timeout;
		elem.on('tooltip:show ' + options.trigger.show, function() {
			$.data(this, 'cancel.tooltip', true);
			var tip = $.data(this, 'active.tooltip');
			if (!tip) {
				tip = $('<div class="tooltip"><div class="tooltip-inner"/></div>').css({
					position : 'absolute',
					zIndex : 100000
				});
				$.data(this, 'active.tooltip', tip);
			}
			if (elem.attr('title') || typeof (elem.attr('original-title')) !== 'string') {
				elem.attr('original-title', elem.attr('title') || '').removeAttr('title');
			}
			var title;
			if (typeof options.attr === 'string') {
				title = elem.attr(options.attr === 'title' ? 'original-title' : options.attr);
			} else if (typeof options.attr == 'function') {
				title = options.attr.call(this);
			}
			tip.find('.tooltip-inner')[options.html ? 'html' : 'text'](title || options.fallback);
			var pos = $.extend({}, elem.offset(), {
				width : this.offsetWidth,
				height : this.offsetHeight
			});
			tip[0].className = 'tooltip';
			tip.remove().css({
				top : 0,
				left : 0,
				opacity : 0
			}).appendTo(document.body);
			var actualWidth = tip[0].offsetWidth,
				actualHeight = tip[0].offsetHeight,
				dir = options.align === 'autoTop' ?
					pos.top > ($(document).scrollTop() + $(window).height() / 2) ? 't' : 'b' :
					pos.left > ($(document).scrollLeft() + $(window).width() / 2) ? 'l' : 'r';
			switch (
			options.align.charAt(0) === 'a' ? dir : options.align.charAt(0)) {
			case 'b':
				tip.css({
					top : pos.top + pos.height,
					left : pos.left + pos.width / 2 - actualWidth / 2
				}).addClass('tooltip-bottom');
				break;
			case 't':
				tip.css({
					top : pos.top - actualHeight,
					left : pos.left + pos.width / 2 - actualWidth / 2
				}).addClass('tooltip-top');
				break;
			case 'l':
				tip.css({
					top : pos.top + pos.height / 2 - actualHeight / 2,
					left : pos.left - actualWidth
				}).addClass('tooltip-left');
				break;
			case 'r':
				tip.css({
					top : pos.top + pos.height / 2 - actualHeight / 2,
					left : pos.left + pos.width
				}).addClass('tooltip-right');
				break;
			}
			clearTimeout(timeout);
			tip.stop().delay(options.delay.show).fadeTo(options.fade ? options.fade.duration : 0, options.fade.opacity || 0.8, options.fade.complete);
		});
		elem.on('tooltip:hide ' + options.trigger.hide, function() {
			$.data(this, 'cancel.tooltip', false);
			var self = this;
			timeout = setTimeout(function() {
				if ($.data(self, 'cancel.tooltip')) return;
				var tip = $.data(self, 'active.tooltip');
				if (options.fade) {
					tip.stop().fadeTo(options.fade.duration, 0, function() {
						tip.remove();
						if (options.fade.complete) options.fade.complete(true);
					});
				} else {
					tip.remove();
				}
			}, options.delay.hide);
		});
	}
	$.fn[pluginName] = function(options) {
		return this.each(function() {
			if (!$.data(this, "plugin_" + pluginName)) {
				$.data(this, "plugin_" + pluginName, new Plugin(this, options));
			}
		});
	};
}(jQuery, window, document));

/*测试提示框是否是当前按钮的*/
function tipsTest(newTipsBtn) {
	var tipsDiv = $("#tipsDiv"); //提示框
	var oldTipsBtn = $("[tipsBtn]"); //旧的提示按钮
	if (tipsDiv.length != 0) { //提示框已存在
		oldTipsBtn.removeAttr("tipsBtn"); //移除属性
		if (oldTipsBtn.is(newTipsBtn)) { //是当前按钮
			return true;
		} else { //不是当前按钮
			return false;
		}
	}
	return false;
}

/*定位提示框*/
function tips(newTipsBtn, html) {
	var tipsDiv = $("#tipsDiv"); //提示框
	var oldTipsBtn = $("[tipsBtn]"); //旧的提示按钮
	if (tipsDiv.length != 0) { //提示框已存在
		oldTipsBtn.removeAttr("tipsBtn"); //移除属性
		if (oldTipsBtn.is(newTipsBtn)) { //是当前按钮
			tipsDiv.remove(); //移除提示框
			return false; //结束
		} else { //不是当前按钮
			tipsDiv.remove(); //移除提示框
		}
	}
	if (newTipsBtn == undefined) { //无参调用 结束
		return false;
	}
	var t = "<div id='tipsDiv'>" + html + "<button id='tipsClose'>×</button></div>"; //设置提示框
	$("body").append(t); //把提示框追加到尾部
	newTipsBtn.attr("tipsBtn", "tipsBtn"); //设置新按钮的属性
	tipsDiv = $("#tipsDiv"); //重新获取提示框
	var bl = newTipsBtn.offset().left; //按钮左坐标
	var bt = newTipsBtn.offset().top; //按钮上坐标
	var bw = newTipsBtn.outerWidth(); //按钮宽
	var bh = newTipsBtn.outerHeight(); //按钮高
	var tw = tipsDiv.outerWidth(); //提示框宽
	var dw = $(document).width(); //屏幕宽
	var ll = (bl + bw / 2 - tw / 2); //提示框左实际坐标
	var tt = bt + bh + 10; //提示框上实际坐标
	var tal = -8; //提示框箭头左偏移(位置 按钮正中央)与#tips::after中border-width的属性为相反数
	if (ll < 0) { //提示框左坐标超出屏幕左侧
		tal += ll; //提示框箭头到达按钮正中央
		ll = 0; //提示框左实际坐标到达左屏幕边缘
	}
	if (ll + tw > dw) { //提示框右坐标超出屏幕右侧
		tipsDiv.outerWidth(tw); //恢复提示宽
		tal += (bl - dw + bw / 2 + tw / 2); ////提示框箭头到达按钮正中央
		ll = (dw - tw); //提示框右实际坐标到达右屏幕边缘
	}
	tipsDiv.css({
		"top" : tt,
		"left" : ll
	}); //设置提示框实际坐标
	tipsDiv.append("<style>#tipsDiv::after{margin-left:" + tal + "px}</style>"); //设置提示框箭头实际左偏移(按钮正中央)
	$("#tipsClose").click(function() { //点击关闭按钮
		tipsDiv.remove(); //移除提示框
		$("[tipsBtn]").removeAttr("tipsBtn"); //移除属性
	});
}

/*遮罩提示框*/
function shadeTips(html) {
	var t = "<div id='shadeBg'><span id='shadeSpan'>双击背景也可以关闭提示。</span><div id='shadeDiv'>" + html + "<button id='shadeClose'>×</button></div></div>";
	$("body").append(t);
	var shadeBg = $("#shadeBg");
	var shadeDiv = $("#shadeDiv");
	var tw = shadeDiv.outerWidth(); //提示框宽
	var th = shadeDiv.outerHeight(); //提示框高
	var dw = $(document).width(); //屏幕宽
	var dh = $(document).height(); //屏幕高
	var tt = (dh - th) / 2;
	var ll = (dw - tw) / 2;
	shadeDiv.css({
		"top" : tt,
		"left" : ll
	});
	$("#shadeClose").click(function() { //点击关闭按钮
		shadeBg.remove(); //移除提示框
	});
	shadeBg.dblclick(function(e) { //双击背景关闭
		if (e.target.id == "shadeBg") {
			shadeBg.remove(); //移除提示框
		}
	});
}