/**
 * 身份证校验 身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X
 */
function regIdcard(idcard) {
	if (idcard.length != 18) {
		return false;
	}
	var reg = /^[0-9]{17}[0-9xX]$/;
	if (!reg.test(idcard)) {
		return false;
	}
	idcard = idcard.toLowerCase();
	/*取身份证前两位,校验省份*/
	var city = [ 11, 12, 13, 14, 15, 21, 22, 23, 31, 32, 33, 34, 35, 36, 37, 41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54, 61, 62, 63, 64, 65, 71, 81, 82 ];
	if (city.indexOf(parseInt(idcard.substr(0, 2))) == -1) {
		return false;
	}
	/*检查出生年月日是否正确*/
	var year = idcard.slice(6, 10);
	var month = idcard.slice(11, 12);
	var day = idcard.slice(13, 14);
	//年月日是否合理
	if (month < 1 || month > 12) {
		return false;
	}
	if (day < 1 || day > 31) {
		return false;
	}
	if ((month == 4 || month == 6 || month == 9 || month == 11) &&
		(day == 31)) {
		return false;
	}
	if (month == 2) {
		var leap = (year % 4 == 0 &&
			(year % 100 != 0 || year % 400 == 0));
		if (day > 29 || (day == 29 && !leap)) {
			return false;
		}
	}
	//判断年份的范围（0岁到200岁之间)
	var now = new Date();
	var now_year = now.getFullYear();
	var time = now_year - year;
	if (time < 0 || time > 200) {
		return false;
	}
	/*校验位的检测*/
	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
	var arrCh = new Array('1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2');
	var cardTemp = 0;
	for (var i = 0; i < 17; i++) {
		cardTemp += idcard.substr(i, 1) * arrInt[i];
	}
	if (arrCh[cardTemp % 11] != idcard.substr(17, 1)) {
		return false;
	}
	return true;
}