package com.airchina.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by IntelliJ IDEA. User: Jero Date: 2009-5-27 Time: 17:45:31
 */
public class TimeHelper {
	// 日期格式
	private synchronized static String timeFormate(String format) {
		SimpleDateFormat _dateFormat = new SimpleDateFormat(format);
		// 获得当前的时间
		Date _currentDate = Calendar.getInstance().getTime();
		return _dateFormat.format(_currentDate);
	}

	// 日期格式
	private synchronized static String dateFormate(Date date, String format) {
		SimpleDateFormat _dateFormat = new SimpleDateFormat(format);
		return _dateFormat.format(date);
	}

	// 含有时区的时间格式化
	private synchronized static String timeFormate(String date, String format) throws ParseException {
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		Date dd = sdf3.parse(date);
		sdf3 = new SimpleDateFormat(format);
		return sdf3.format(dd);
	}

	public static String formatDateTime(String date) throws ParseException {
		return timeFormate(date, "yyyyMMdd");
	}

	public static String formatTime(String date) throws ParseException {
		return timeFormate(date, "HHmm");
	}

	/**
	 * 获取两个日期间隔天数
	 * 
	 * @param startDate
	 *            开始日期 YYYYMMDD
	 * @param endDate
	 *            结束日期 YYYYMMDD
	 * @return 间隔天数:有正，有负
	 */
	public static int getDateBetween(int startDate, int endDate) {
		int interval = 0;
		int tempDate = 0;

		int orgStartDate = startDate;
		int orgEndDate = endDate;

		if (startDate > endDate) {
			tempDate = startDate;
			startDate = endDate;
			endDate = tempDate;
		}

		// 平年月的天数
		int _monthDays[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		String _startDate = Integer.toString(startDate);
		String _endDate = Integer.toString(endDate);

		int _startYear = Integer.parseInt(_startDate.substring(0, 4));
		int _startMonth = Integer.parseInt(_startDate.substring(4, 6));
		int _startDay = Integer.parseInt(_startDate.substring(6, 8));

		int _endYear = Integer.parseInt(_endDate.substring(0, 4));
		int _endMonth = Integer.parseInt(_endDate.substring(4, 6));
		int _endDay = Integer.parseInt(_endDate.substring(6, 8));

		// 计算开始年度到当年底的天数
		int _passDay = 0; // 当年已过的天数
		if (_startMonth < 3) {
			for (int index = 0; index < _startMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _startDay - 1;

			if (isLeapYear(_startYear)) {
				interval = 366 - _passDay;
			} else {
				interval = 365 - _passDay;
			}
		} else {
			for (int index = 0; index < _startMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _startDay - 1;
			interval = 365 - _passDay;
		}

		// 计算开始下一年度到结束前年度的天数
		if (_startYear == _endYear) { // 同一年
			if (isLeapYear(_startYear)) {
				interval -= 366;
			} else {
				interval = interval - 365;
			}
		}
		_startYear++;
		while (_startYear < _endYear) {
			if (isLeapYear(_startYear)) {
				interval += 366;
			} else {
				interval += 365;
			}
			_startYear++;
		}

		// 计算结束年度的天数
		_passDay = 0;
		if (_endMonth > 2) {
			for (int index = 0; index < _endMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _endDay;

			if (isLeapYear(_endYear)) {
				interval += (_passDay + 1);
			} else {
				interval += _passDay;
			}
		} else {
			for (int index = 0; index < _endMonth - 1; index++) {
				_passDay += _monthDays[index];
			}
			_passDay += _endDay;

			interval += _passDay;
		}
		if (orgStartDate > orgEndDate) {
			interval = -interval;
		}

		return interval;
	}

	/**
	 * 获得系统年份，以字符串"YYYY"返回
	 * 
	 * @return 返回"YYYY"字符串
	 */
	public static String getStrCurrentYear() {
		return timeFormate("yyyy");
	}

	/**
	 * 获得系统月份，以字符串"MM"返回
	 * 
	 * @return 返回"MM"字符串
	 */
	public static String getStrCurrentMonth() {
		return timeFormate("MM");
	}

	/**
	 * 获得系统天数，以字符串"DD"返回
	 * 
	 * @return 返回"DD"字符串
	 */
	public static String getStrCurrentDay() {
		return timeFormate("dd");
	}

	/**
	 * 获得系统年份，以整数"YYYY"返回
	 * 
	 * @return 返回"YYYY"整数
	 */
	public static int getCurrentYear() {
		return Integer.parseInt(timeFormate("yyyy"));
	}

	/**
	 * 获得系统月份，以整数"MM"返回
	 * 
	 * @return 返回"MM"整数
	 */
	public static int getCurrentMonth() {
		return Integer.parseInt(timeFormate("MM"));
	}

	/**
	 * 获得系统天数，以整数"DD"返回
	 * 
	 * @return 返回"DD"整数
	 */
	public static int getCurrentDay() {
		return Integer.parseInt(timeFormate("dd"));
	}

	/**
	 * 获得系统年月，以字符串"YYYY-MM"返回
	 * 
	 * @return 返回"YYYY-MM"字符串
	 */
	public static String getStrCurYearMonth() {
		return timeFormate("yyyy年MM月");
	}

	/**
	 * 获得系统年月日，以字符串"YYYY-MM-DD"返回
	 * 
	 * @return 返回"YYYY-MM-DD"字符串
	 */
	public static String getStrCurrentDate() {
		return timeFormate("yyyy-MM-dd");
	}

	/**
	 * 获得系统年月日时分秒，以字符串"YYYY-MM-DD hh:mm:ss"返回
	 * 
	 * @return 返回"YYYY-MM-DD hh:mm:ss"字符串
	 */
	public static String getStrCurrentTime() {
		return timeFormate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得系统年月日时分秒毫秒，以字符串"YYYY-MM-DD hh:mm:ss.SSS"返回
	 * 
	 * @return 返回"YYYY-MM-DD hh:mm:ss.SSS"字符串
	 */
	public static String getStrCurrentDetailTime() {
		return timeFormate("yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获得系统年月，返回"YYYYMM"整型
	 * 
	 * @return 返回"YYYYMM"整型
	 */
	public static int getCurrentYearMonth() {
		return Integer.parseInt(timeFormate("yyyyMM"));
	}

	/**
	 * 获得系统年月日，返回"YYYYMMDD"整型
	 * 
	 * @return 返回"YYYYMMDD"整型
	 */
	public static Integer getCurrentDate() {
		return Integer.parseInt(timeFormate("yyyyMMdd"));
	}

	public static String getCurrentDateTime() {
		return timeFormate("yyyyMMddHHmm");
	}

	/**
	 * 获取系统星期几
	 * 
	 * @return 返回“XXX”
	 */
	public static String getCurrenWeekday() {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获得系统年月日时分秒，以整型"YYYYMMDDhhmmss"返回
	 * 
	 * @return 返回"YYYYMMDDhhmmss"整型
	 */
	public static Long getCurrentTime() {
		return Long.parseLong(timeFormate("yyyyMMddHHmmss"));
	}

	/**
	 * 获得系统年月日时分秒毫秒，以长整型"YYYYMMDDhhmmssSSS"返回
	 * 
	 * @return 返回"YYYYMMDDhhmmssSSS"整型
	 */
	public static long getCurrentDetailTime() {
		return Long.parseLong(timeFormate("yyyyMMddHHmmssSSS"));
	}

	/**
	 * 获得系统时分秒毫秒，以整型"hhmmssSSS"返回
	 * 
	 * @return 返回"hhmmssSSS"整型
	 */
	public static int getCurrentHmsSTime() {
		return Integer.parseInt(timeFormate("HHmmssSSS"));
	}

	/**
	 * 获得系统时分秒，以整型"hhmmss"返回
	 * 
	 * @return 返回"hhmmssSSS"整型
	 */
	public static int getCurrentHmsTime() {
		return Integer.parseInt(timeFormate("HHmmss"));
	}

	/**
	 * 获得系统时分，以整型"hhmm"返回
	 * 
	 * @return 返回"hhmm"整型
	 */
	public static Integer getCurrentHmTime() {
		return Integer.parseInt(timeFormate("HHmm"));
	}

	/**
	 * 对于数字类型的时间
	 * 
	 * @param time
	 * @return
	 */
	public static String parseTime(Integer time) {
		String t = time.toString();
		if (t.length() == 3) {
			return "0" + t.substring(0, 1) + ":" + t.substring(1);
		} else {
			return t.substring(0, 2) + ":" + t.substring(2);
		}
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY-MM-DD
	 */
	public static String date2Str(int iDate) {
		String _strDate = null;
		_strDate = String.valueOf(iDate);
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY年MM月DD日串
			_strDate = _strYear + "-" + _strMonth + "-" + _strDay;
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY年MM月串
			_strDate = _strYear + "-" + _strMonth;

		}
		return _strDate;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY-MM-DD
	 */
	public static String date2Str(String iDate) {
		String _strDate = null;
		_strDate = iDate;
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY年MM月DD日串
			_strDate = _strYear + "-" + _strMonth + "-" + _strDay;
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY年MM月串
			_strDate = _strYear + "-" + _strMonth;

		}
		return _strDate;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY/MM/DD
	 */
	public static String date2Str2(int iDate) {
		String _strDate = null;
		_strDate = String.valueOf(iDate);
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY/MM/DD串
			_strDate = _strYear + "/" + _strMonth + "/" + _strDay;
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY/MM串
			_strDate = _strYear + "/" + _strMonth;

		}
		return _strDate;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY/MM/DD
	 */
	public static String date2Str2(String iDate) {
		// String _strDate = null;
		// _strDate = String.valueOf(iDate);
		if (iDate.length() == 8) {
			String _strYear = iDate.substring(0, 4);
			String _strMonth = iDate.substring(4, 6);
			String _strDay = iDate.substring(6, 8);
			// 整合成YYYY/MM/DD串
			iDate = _strYear + "/" + _strMonth + "/" + _strDay;
		} else if (iDate.length() == 6) {
			String _strYear = iDate.substring(0, 4);
			String _strMonth = iDate.substring(4, 6);
			// 整合成YYYY/MM串
			iDate = _strYear + "/" + _strMonth;

		}
		return iDate;
	}

	/**
	 * 转整型日期为字符串日期
	 * 
	 * @param iDate
	 *            整型YYYYMMDD
	 * @return 字符串YYYY年MM月DD日
	 */
	public static String date2StrCN(int iDate) {
		String _strDate = null;
		_strDate = String.valueOf(iDate);
		if (_strDate.length() == 8) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			String _strDay = _strDate.substring(6, 8);
			// 整合成YYYY年MM月DD日串
			_strDate = _strYear + "年" + _strMonth + "月" + _strDay + "日";
		} else if (_strDate.length() == 6) {
			String _strYear = _strDate.substring(0, 4);
			String _strMonth = _strDate.substring(4, 6);
			// 整合成YYYY年MM月串
			_strDate = _strYear + "年" + _strMonth + "月";

		}
		return _strDate;
	}

	/**
	 * 转整型日期时间为字符串日期时间
	 * 
	 * @param iDateTime
	 *            整型YYMMDDhhmmss
	 * @return 字符串 YY-MM-DD hh:mm:ss
	 */
	public static String dateTime2Str(long iDateTime) {
		String _strDateTimeTemp = iDateTime + "";
		// 调用date2Str(int iDate)方法获得YY-MM-DD串
		String _strDate = date2Str(Integer.parseInt(_strDateTimeTemp.substring(0, 8)));
		String _strTimeTemp = _strDateTimeTemp.substring(8, 14) + "";
		String _strHour = _strTimeTemp.substring(0, 2);
		String _strMinute = _strTimeTemp.substring(2, 4);
		String _strSecond = _strTimeTemp.substring(4, 6);
		// 整合成 hh:ss:mm 串
		String _strTime = _strHour + ":" + _strMinute + ":" + _strSecond;
		return _strDate + " " + _strTime;
	}

	/**
	 * 校验时间是否合法
	 * 
	 * @param curTime
	 *            录入时间 HHMMSS 或 HMMSS
	 * @return 时间合法则返回true，不合法返回false
	 */
	public static boolean checkTime(int curTime) {
		// 时间的范围
		if ((curTime > 235959) || (curTime < 0)) {
			return false;
		}

		// 获得不同段的时间
		int iHour = curTime / 10000; // 小时
		int iMinute = (curTime - (iHour * 10000)) / 100; // 分钟
		int iSecond = curTime % 100; // 秒

		// 对小时进行校验
		if ((iHour < 0) || (iHour > 23)) {
			return false;
		}
		// 对分钟进行校验
		if ((iMinute < 0) || (iMinute > 59)) {
			return false;
		}
		// 对秒钟进行校验
		if ((iSecond < 0) || (iSecond > 59)) {
			return false;
		}

		return true;
	}

	/**
	 * 校验时间或者日期是否合法
	 * 
	 * @param strDate
	 *            "YYYYMMDD" OR "YYYYMMDDhhmmss"
	 * @return true - 合法； false - 非法
	 */
	public static boolean checkTimeStr(String strDate) {
		// 时间参数:年、月、日、时、分、秒
		int _iYear = 0;
		int _iMonth = 0;
		int _iDay = 0;
		int _iHour = 0;
		int _iMinute = 0;
		int _iSecond = 0;
		boolean _bFlag = false;
		String _strDate = strDate.trim();

		// 合法性的判断
		try {
			if (Long.parseLong(_strDate) > 0) {
				_bFlag = true;
			} else {
				_bFlag = false;
			}
		} catch (NumberFormatException ex2) {
			_bFlag = false;
		}
		if (!_bFlag) {
			return _bFlag;
		}

		// 将年月日转换为整型
		try {
			_iYear = Integer.parseInt(_strDate.substring(0, 4));
			_iMonth = Integer.parseInt(_strDate.substring(4, 6));
			_iDay = Integer.parseInt(_strDate.substring(6, 8));
		} catch (Exception ex) {
			_bFlag = false;
			return _bFlag;
		}

		// 判断年月是否合法
		if ((_iYear > 1900) && (_iDay > 0) && ((_iMonth > 0) && (_iMonth < 13))) {
			_bFlag = true;
		} else {
			_bFlag = false;
		}

		// 判定日期是否合法
		if (_bFlag) {
			// 平年时每月的天数
			int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			int _maxDay = months[_iMonth];
			// 如果是闰年，二月再增加一天
			if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear % 400 == 0)) {
				if (_iMonth == 2) {
					_maxDay++;
				}
			}
			_bFlag = (_iDay <= _maxDay);
		}

		if ((_bFlag) && (_strDate.length() == 14)) {
			// 当字符串中含有时间串(hh:mm:ss)
			try {
				_iHour = Integer.parseInt(_strDate.substring(8, 10));
				_iMinute = Integer.parseInt(_strDate.substring(10, 12));
				_iSecond = Integer.parseInt(_strDate.substring(12, 14));
			} catch (Exception ex1) {
				_bFlag = false;
			}
			if (!_bFlag) {
				return _bFlag;
			}

			// 检测时分秒
			if (((_iHour >= 0) && (_iHour <= 23)) && ((_iMinute >= 0) && (_iMinute <= 59)) && ((_iSecond >= 0) && (_iSecond <= 59))) {
				_bFlag = true;
			} else {
				_bFlag = false;
			}
		}

		return _bFlag;
	}

	/**
	 * 返回指定天数后的日期
	 * 
	 * @param curDate
	 *            时间起点 YYYYMMDD
	 * @param i
	 *            数天前 (Days < 0); Days 数天后 (Days > 0)
	 * @return 数天后的日期 YYYYMMDD
	 */
	public static int getDateLater(int curDate, int i) {
		int _iCurYear = curDate / 10000; // 获得年度
		int _iCurMonth = (curDate - _iCurYear * 10000) / 100; // 获得月份
		int _iCurDay = (curDate) % 100; // 获得日期
		int _iNextDate = 0;
		if (i >= 0) {
			_iNextDate = after(_iCurYear, _iCurMonth, _iCurDay, i);
		} else {
			_iNextDate = before(_iCurYear, _iCurMonth, _iCurDay, i);
		}

		return _iNextDate;

	}

	// 计算多少天前的时间

	private static int before(int curYear, int curMonth, int curDay, int i) {
		int _iCurYear = curYear;
		int _iCurMonth = curMonth;
		int _iCurDay = curDay;
		int _iDays = i;
		_iDays = -_iDays;
		// 平年月的天数
		int _aNormMonthDays[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		// 闰年月的天数
		int _aLeapMonthDays[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] _aMonthDays = new int[13];
		// 计算下个年度的月份
		while (_iDays != 0) {
			// 是否在当前月中
			int _iDescDays = _iCurDay - 1;
			if (_iDays > _iDescDays) {
				_iDays = _iDays - _iDescDays - 1;
				// 设定上月的年月日
				if (_iCurMonth > 1) {
					_iCurMonth--;
				} else {
					_iCurMonth = 12;
					_iCurYear--;
				}
				// 是否闰年
				if (isLeapYear(_iCurYear)) {
					_aMonthDays = _aLeapMonthDays;
				} else {
					_aMonthDays = _aNormMonthDays;
				}
				// 上月的月末日期
				_iCurDay = _aMonthDays[_iCurMonth];
			} else {
				_iCurDay = _iCurDay - _iDays;
				_iDays = 0;
			}
		}

		return _iCurYear * 10000 + _iCurMonth * 100 + _iCurDay;
	}

	// 计算多少天后的时间

	private static int after(int curYear, int curMonth, int curDay, int i) {
		int _iCurYear = curYear;
		int _iCurMonth = curMonth;
		int _iCurDay = curDay;
		int _iDays = i;
		// 平年月的天数
		int _aNormMonthDays[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		// 闰年月的天数
		int _aLeapMonthDays[] = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] _aMonthDays = new int[13];
		// 计算下个年度的月份
		while (_iDays != 0) {
			// 是否闰年
			if (isLeapYear(_iCurYear)) {
				_aMonthDays = _aLeapMonthDays;
			} else {
				_aMonthDays = _aNormMonthDays;
			}

			// 是否在当前月中
			int _iDescDays = _aMonthDays[_iCurMonth] - _iCurDay;
			if (_iDays > _iDescDays) {
				_iDays = _iDays - _iDescDays - 1;
				// 设定下月的年月日
				_iCurDay = 1;
				if (_iCurMonth < 12) {
					_iCurMonth++;
				} else {
					_iCurMonth = 1;
					_iCurYear++;
				}
			} else {
				_iCurDay = _iCurDay + _iDays;
				_iDays = 0;
			}
		}

		return _iCurYear * 10000 + _iCurMonth * 100 + _iCurDay;
	}

	/**
	 * 是否闰年
	 * 
	 * @param CurYear
	 *            年度
	 * @return True - 是闰年； false - 平年
	 */
	public static boolean isLeapYear(int CurYear) {
		boolean _bIsLeap = false;
		// 判定平年闰年
		if (((CurYear % 4 == 0) && (CurYear % 100 != 0)) || (CurYear % 400 == 0)) {
			_bIsLeap = true;
		} else {
			_bIsLeap = false;
		}
		return _bIsLeap;
	}

	/**
	 * 计算两个年月之间相隔的月份
	 * 
	 * @param startMonth
	 *            开始的年月(int YYYYMM)
	 * @param endMonth
	 *            结束的年月(int YYYYMM)
	 * @return 计算日期相隔的月份
	 */
	public static int getMonthBetween(int startMonth, int endMonth) {
		int _iYearS = startMonth / 100;
		int _iYearE = endMonth / 100;
		int _iMonthS = startMonth - _iYearS * 100;
		int _iMonthE = endMonth - _iYearE * 100;
		int _iMonthSum = 0;
		int _iYearSum = _iYearE - _iYearS;
		if (_iYearSum == 0) {
			_iMonthSum = _iMonthE - _iMonthS;
		}
		if (_iYearSum >= 1) {
			_iMonthSum = (12 - _iMonthS) + _iMonthE + 12 * (_iYearSum - 1);
		}
		return _iMonthSum;
	}

	/**
	 * 月末的最后时刻
	 * 
	 * @param yearMonth
	 *            年月 YYYYMM
	 * @return 月末的最后时刻 YYYYMMdd2359999
	 */
	public static long getDateTimeOfMonthEnd(int yearMonth) {
		long _lMonthEndDetailTime = 0L;
		int _year = yearMonth % 100;
		int _month = yearMonth - (yearMonth % 100) * 100;
		int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int _endDay = months[_month];
		// 如果是闰年，二月则多一天
		if (isLeapYear(_year) && _month == 2) {
			_endDay++;
		}
		_lMonthEndDetailTime = (_year * 10000 + _month * 100 + _endDay) * 10000000 + 2359999;
		return _lMonthEndDetailTime;
	}

	/**
	 * 取月末最后一天的日期
	 * 
	 * @param YearMonth
	 *            指定的年月
	 * @return 指定年月的最后一天的日期，格式为YYYYMMDD
	 */
	public static Integer getDateOfMonthEnd(int YearMonth) {
		int _year = YearMonth / 100;
		int _month = YearMonth % 100;
		// 判断年月是否合法
		if ((_month < 1) || (_month > 12)) {
			return 0;
		}
		int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int _endDay = months[_month];
		// 如果是闰年，二月则多一天
		if (isLeapYear(_year) && _month == 2) {
			_endDay++;
		}
		return YearMonth * 100 + _endDay;
	}

	/**
	 * 计算年龄月数
	 * 
	 * @param birthday
	 *            出生日期
	 * @param calcDate
	 *            计算日期
	 * @return
	 */
	public static int getMonthOfAge(int birthday, int calcDate) {
		return TimeHelper.getMonthBetween(birthday / 100, calcDate / 100);
	}

	/**
	 * 计算若干个月之后的年月
	 * 
	 * @param beginYearMonth
	 *            开始年月
	 * @param month
	 *            经过月数
	 * @return
	 */
	public static int getMonthLater(int beginYearMonth, int month) {
		int _year = beginYearMonth / 100;
		int _month = beginYearMonth - _year * 100;

		if (month > 0) {
			int _tempMon = _month + month; // - 1;
			if (_tempMon > 12) {
				_year = _year + _tempMon / 12;
				_tempMon = _tempMon % 12;
				if (_tempMon == 0) {
					_year = _year - 1;
					_tempMon = 12; // _month;
				}
			}
			return _year * 100 + _tempMon;
		} else if (month < 0) {
			_year = _year - Math.abs(month) / 12;
			int _tempMon = _month - Math.abs(month) % 12;
			if (_tempMon <= 0) {
				_year = _year - 1;
				_tempMon = 12 - Math.abs(_tempMon);
			}
			return _year * 100 + _tempMon;
		} else {
			return beginYearMonth;
		}
	}

	/**
	 * 得到某年月的最大天数
	 * 
	 * @param strYearMonth
	 *            "YYYYMM"
	 * @return 天数
	 */
	public static int getDaysMaxOfMonth(String strYearMonth) {
		int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int _iMonth = new Integer(strYearMonth.substring(4, 6));
		int _iYear = new Integer(strYearMonth.substring(0, 4));
		int _maxDay = months[_iMonth];
		// 如果是闰年，二月再增加一天
		if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear % 400 == 0)) {
			if (_iMonth == 2) {
				_maxDay++;
			}
		}
		return _maxDay;
	}

	/**
	 * 转换字符串日期为数字型日期
	 * 
	 * @param strDate
	 *            "YYYY-MM-DD"
	 * @return 数字日期
	 */
	public static int dateFromString(String strDate) {
		int intYear = Integer.parseInt(strDate.substring(0, 4));
		int intMonth = Integer.parseInt(strDate.substring(5, 7));
		int intDay = Integer.parseInt(strDate.substring(8, 10));
		return intYear * 10000 + intMonth * 100 + intDay;
	}

	/**
	 * 将时间格式精简成日期格式
	 * 
	 * @param datetime
	 * @return
	 */
	public static int dateTime2Date(long datetime) {
		int length = String.valueOf(datetime).length();
		long date = datetime;
		if (length == 14 && datetime != 0) {
			date = datetime / 1000000;
		} else if (length == 17 && datetime != 0) {
			date = datetime / 1000000000;
		} else if (datetime > Integer.MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		return (int) date;
	}

	/**
	 * 将时间格式精简成年月格式
	 * 
	 * @param datetime
	 * @return
	 */
	public static int dateTime2Month(long datetime) {
		return dateTime2Date(datetime) / 100;
	}

	/**
	 * 将日期格式精简成年月格式
	 * 
	 * @param date
	 * @return
	 */
	public static int date2Month(int date) {
		int length = String.valueOf(date).length();
		int month = date;
		if (length == 8 && date != 0) {
			month = date / 100;
		}
		return month;
	}

	/**
	 * 传入一个int的日期转换成一个中文的日期 例如20050328 输出二OO五年三月二十八日
	 * 
	 * @param iDate
	 *            int
	 * @return String
	 */
	public static String date2StrCHS(int iDate) {
		String result = "";
		String[] chineseNumber = { "0", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] cDate = String.valueOf(iDate).toCharArray();
		StringBuffer tempDate = new StringBuffer("");
		for (char aCDate : cDate) {
			int chatat = Integer.parseInt(String.valueOf(aCDate));
			tempDate.append(chineseNumber[chatat]);
		}
		result = tempDate.substring(0, 4) + "年";
		int chatM = Integer.parseInt(String.valueOf(cDate[4]));
		if (chatM != 0) {
			if ("0".equals(tempDate.substring(5, 6))) {
				result += "十月";
			} else {
				result += "十" + tempDate.substring(5, 6) + "月";
			}
		} else {
			result += tempDate.substring(5, 6) + "月";
		}
		int chatd = Integer.parseInt(String.valueOf(cDate[6]));
		if (chatd == 0) {
			result += tempDate.substring(7, 8) + "日";
		} else if (chatd == 1) {
			if ("0".equals(tempDate.substring(7, 8))) {
				result += "十日";
			} else {
				result += "十" + tempDate.substring(7, 8) + "日";
			}
		} else {
			if ("0".equals(tempDate.substring(7, 8))) {
				result += tempDate.substring(6, 7) + "十日";
			} else {
				result += tempDate.substring(6, 7) + "十" + tempDate.substring(7, 8) + "日";
			}
		}
		return result;
	}

	/**
	 * 将XLM通用时间日期型转换为日期 2010-01-12T13:00:00+08:00 -> 20100112
	 * 
	 * @param dateTime
	 *            String
	 * @return int
	 */
	public static String xmlDateTime2Date(String dateTime) {
		// 2010-01-12T13:00:00+08:00
		return dateTime.replaceFirst("T.*", "").replace("-", "");
	}

	/**
	 * 返回两个年月之间的年月数组
	 * 
	 * @param begin
	 *            开始年月
	 * @param end
	 *            结束年月
	 * @return
	 */
	public static int[] getMonthPeriod(int begin, int end) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int _tmpYearMonth = begin; _tmpYearMonth <= end; _tmpYearMonth = getMonthLater(_tmpYearMonth, 1)) {
			list.add(_tmpYearMonth);
		}
		int _ary[] = new int[list.size()];
		for (int i = 0; i < _ary.length; i++) {
			_ary[i] = list.get(i);
		}
		return _ary;
	}

	/**
	 * 把整数型日期转换成字符串形式返回
	 * 
	 * @param dateint
	 *            日期
	 * @return
	 */
	public static String spliteWith(int dateint, char spliter) {
		String dateStr = dateint + "";
		return dateStr.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1" + spliter + "$2" + spliter + "$3");
	}

	/**
	 * 返回当前日期的前(后)时间
	 * 
	 * @param minute
	 *            15 分钟
	 * @return
	 */
	public static long dateTimeBeforeMinute(long minute) {
		SimpleDateFormat longDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date currentTime = new Date();
		long lDate = currentTime.getTime() + (1000L * 60L * minute);
		currentTime = new Date(lDate);
		return Long.parseLong(longDateFormat.format(currentTime));
	}

	/**
	 * 校验时间或者日期是否合法:不需要判断2月是否是润年的天数
	 * 
	 * @param strDate
	 *            "YYYYMMDD" OR "YYYYMMDDhhmmss"
	 * @return true - 合法； false - 非法
	 */
	public static boolean isValidate(String strDate) {
		// 时间参数:年、月、日、时、分、秒
		int _iYear = 0;
		int _iMonth = 0;
		int _iDay = 0;
		int _iHour = 0;
		int _iMinute = 0;
		int _iSecond = 0;
		boolean _bFlag;
		String _strDate = strDate.trim();

		// 合法性的判断
		try {
			_bFlag = Long.parseLong(_strDate) > 0;
		} catch (NumberFormatException ex2) {
			_bFlag = false;
		}
		if (!_bFlag) {
			return _bFlag;
		}

		if (_bFlag) {
			// 将年月日转换为整型
			try {
				_iYear = Integer.parseInt(_strDate.substring(0, 4));
				_iMonth = Integer.parseInt(_strDate.substring(4, 6));
				_iDay = Integer.parseInt(_strDate.substring(6, 8));
			} catch (Exception ex) {
				_bFlag = false;
			}
			if (!_bFlag) {
				return _bFlag;
			}
			// 判断年月是否合法
			_bFlag = (_iYear > 1900) && (_iDay > 0) && ((_iMonth > 0) && (_iMonth < 13));

			// 判定日期是否合法
			if (_bFlag) {
				// 平年时每月的天数
				int[] months = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
				int _maxDay = months[_iMonth];
				// 如果是闰年，二月再增加一天
				// if (((_iYear % 4 == 0) && (_iYear % 100 != 0)) || (_iYear %
				// 400 == 0)) {
				if (_iMonth == 2) {
					_maxDay++;
				}
				// }
				_bFlag = (_iDay <= _maxDay);
			}

			if (_bFlag && _strDate.length() == 14) {
				// 当字符串中含有时间串(hh:mm:ss)
				try {
					_iHour = Integer.parseInt(_strDate.substring(8, 10));
					_iMinute = Integer.parseInt(_strDate.substring(10, 12));
					_iSecond = Integer.parseInt(_strDate.substring(12, 14));
				} catch (Exception ex1) {
					_bFlag = false;
				}
				if (!_bFlag) {
					return _bFlag;
				}

				// 检测时分秒
				_bFlag = ((_iHour >= 0) && (_iHour <= 23)) && ((_iMinute >= 0) && (_iMinute <= 59)) && ((_iSecond >= 0) && (_iSecond <= 59));
			}
		}
		return _bFlag;
	}

	/**
	 * 将时间转换为hh:mm格式
	 * 
	 * @param time
	 *            "yyyMMdd/hhmm"
	 * @return
	 */
	public static String time2Str(String time) {
		String tempStr = "";
		if (time != null && !"".equals(time)) {
			tempStr = time.substring(time.indexOf('/') + 1, time.length());
			tempStr = tempStr.substring(0, 2) + ":" + tempStr.substring(2, 4);
		}
		return tempStr;
	}

	/**
	 * 将UTC-second类型时间转化为hh:mm:ss格式
	 * 
	 * @return
	 */
	public String getStringTime(int nowtime) {
		int hour = (nowtime % 86400) / 3600;
		int min = (nowtime % 3600) / 60;
		int second = nowtime % 60;
		return hour + ":" + (min < 10 ? "0" + min : min) + ":" + (second < 10 ? "0" + second : second);
	}

	/**
	 * 把YYYY/MM/DD 转换为YYYYMMDD格式
	 * 
	 * @param date
	 *            YYYYDDMM
	 * @return YYYYDDMM
	 */
	public static String getDate(String date) {
		StringBuffer str = new StringBuffer();
		if (date != null && !date.equals("")) {
			str.append(date.substring(0, 4));
			str.append(date.substring(5, 7));
			str.append(date.substring(8, 10));
		}
		return str.toString();
	}

	/**
	 * 把HH:mm 转换为HHmm格式
	 * 
	 * @param time
	 *            HHmm
	 * @return HHmm
	 */
	public static String getTime(String time) {
		StringBuffer str = new StringBuffer();
		str.append(time.substring(0, 2));
		str.append(time.substring(3, 5));
		return str.toString();
	}

	/**
	 * 把HHmm时间转换为时间HH:mm
	 * 
	 * @param time
	 *            HH:mm
	 * @return HH:mm
	 */
	public static String getTime1(String time) {
		StringBuffer str = new StringBuffer();
		if (time.length() > 3) {
			str.append(time.substring(0, 2)).append(":");
			str.append(time.substring(2));
		} else {
			str.append("0");
			str.append(time.substring(0, 1)).append(":");
			str.append(time.substring(1));
		}
		return str.toString();
	}

	/**
	 * 把MM/DD/YYYY格式日期转化成为YYYYMMDD格式
	 */
	public static String getPhneoixDate(String date) {
		if (date != null && !date.equals(""))
			return date.substring(6, 10) + date.substring(0, 2) + date.substring(3, 5);
		else
			return date;
	}

	/**
	 * 把MM/DD/YYYY HH:mm:ss格式日期转化成为YYYYMMDD HH:mm:ss格式
	 */
	public static String getPhneoixDate2(String date) {
		if (date != null && !date.equals("")) {
			if (date.endsWith("00:00:00"))
				return date.substring(6, 10) + date.substring(0, 2) + date.substring(3, 5);
			else
				return date.substring(6, 10) + date.substring(0, 2) + date.substring(3, 5) + date.substring(10);
		} else
			return date;
	}

	/**
	 * 把YYYYMMDD格式日期转化成为YYYY-MM-DD格式
	 * 
	 * @param date
	 *            传入日期
	 * @return YYYY-MM-DD格式日期
	 */
	public static String getDate2(String date) {
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
	}

	/**
	 * 把YYYY-MM-DD格式日期转化为YYYYMMDD
	 * 
	 * @param date
	 *            传入日期
	 * @return YYYYMMDD格式日期
	 */
	public static String getDate3(String date) {
		return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
	}

	/**
	 * 转整型日期时间为字符串日期时间
	 * 
	 * @param iDateTime
	 *            整型YYMMDDhhmmss
	 * @return 字符串 YY/MM/DD hh:mm:ss
	 */
	public static String dateTime2Str2(long iDateTime) {
		String _strDateTimeTemp = iDateTime + "";
		// 调用date2Str(int iDate)方法获得YY-MM-DD串
		String _strDate = date2Str2(Integer.parseInt(_strDateTimeTemp.substring(0, 8)));
		String _strTimeTemp = _strDateTimeTemp.substring(8, 14) + "";
		String _strHour = _strTimeTemp.substring(0, 2);
		String _strMinute = _strTimeTemp.substring(2, 4);
		String _strSecond = _strTimeTemp.substring(4, 6);
		// 整合成 hh:ss:mm 串
		String _strTime = _strHour + ":" + _strMinute + ":" + _strSecond;
		return _strDate + " " + _strTime;
	}

	/**
	 * 对日期增加或减少 单位(分钟)
	 * 
	 * @param datatime
	 *            需要修改的日期 yyyyMMddHHmmss
	 * @param minute
	 *            需要增加(减少)的时间 (分钟)
	 * @return
	 * @throws ParseException
	 */
	public static Long dateTime2BeforeMinute(String datatime, int minute) throws ParseException {
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = sdf.parse(datatime);
		long lDate = date.getTime() + (1000L * 60L * minute);
		currentTime = new Date(lDate);
		return Long.parseLong(sdf.format(currentTime));
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期2010-9-1
	 * @return 20100901
	 */
	public static String formatDate(String date) {
		String[] splitDate = date.split("-");
		String month = splitDate[1];
		if (month.length() == 1) {
			month = "0" + month;
		}
		String day = splitDate[2];
		if (day.length() == 1) {
			day = "0" + day;
		}
		return splitDate[0] + month + day;
	}

	/**
	 * 得到今天之前或之后的日期
	 * 
	 * @param date
	 *            要加或减得天数
	 * @return
	 */
	public static String getBeforeLaterDate(int date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, date);
		Date time = cal.getTime();
		return sdf.format(time);
	}

	/**
	 * 得到当前星期几，以数字方式返回
	 * 
	 * @return
	 */
	public static int getCurrenIntWeekday() {
		int[] weekDays = { 1, 2, 3, 4, 5, 6, 7 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 2;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 通过日期获取周几
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getDateWeekday(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(date));
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

	/**
	 * 返回指定日期是星期几
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getCurrenIntWeekday(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(date));
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

	/**
	 * XMLGregorianCalendar转为字符串
	 * 
	 * @param cal
	 * @return
	 */
	public static String xmlGregorianCalendarTostr(XMLGregorianCalendar cal) {
		String strDate = "";
		if (cal != null) {
			GregorianCalendar ca = cal.toGregorianCalendar();
			Date date = ca.getTime();
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmss");
			strDate = dateFormater.format(date);
		}
		return strDate;
	}

	/**
	 * 字符串转为XMLGregorianCalendar
	 * 
	 * @param strDate
	 * @return
	 */
	public static XMLGregorianCalendar strToXMLGregorianCalendar(String strDate) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6));
		int day = Integer.parseInt(strDate.substring(6, 8));
		int hour = Integer.parseInt(strDate.substring(8, 10));
		int minute = Integer.parseInt(strDate.substring(10, 12));
		int sec = Integer.parseInt(strDate.substring(12, 14));

		XMLGregorianCalendar cal = null;
		try {
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			cal.setYear(year);
			cal.setMonth(month);
			cal.setDay(day);
			cal.setHour(hour);
			cal.setMinute(minute);
			cal.setSecond(sec);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return cal;
	}

	public static String formatDates(String date) throws ParseException {
		SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
		Date dd = sdf3.parse(date);
		sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf3.format(dd);
	}

	// 计算时间间隔(返回分钟)
	public static Long getDifferTime(String beginTime, String endTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = df.parse(beginTime);
		Date date = df.parse(endTime);
		long l = date.getTime() - now.getTime();
		return l / (1000 * 60);
	}

	/**
	 * 计算时间间隔，返回dd:HH:mm:ss
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static Long getDifferMinutes(String beginTime, String endTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = df.parse(beginTime);
		Date date = df.parse(endTime);
		long l = date.getTime() - now.getTime();
		return l / 1000;
		// return minutes/(24*60*60) + ":" + (minutes%(24*60*60))/(60*60) + ":"
		// + ((minutes%(24*60*60))%(60*60))/60 + ":" +
		// ((minutes%(24*60*60))%(60*60))%60;
	}

	public static String formatDate1(String date) throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(d);
	}

	public static String getStrCurrentDate1() {
		return timeFormate("yyyy-M-d");
	}

	public static String changeDateFormat(String dateStr) throws ParseException {
		Date d = new SimpleDateFormat("ddMMMyy", Locale.US).parse(dateStr);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(d);
	}

	/**
	 * 获取当前日期前less分钟的时间 eg:当前日期：20110628170040，返回：20110628163040
	 * 
	 * @author RobotJi
	 * @param less
	 *            比当前时间少的分钟
	 * @return
	 */
	public static long getLessCurrentTime(int less) {
		Calendar cc = Calendar.getInstance();
		cc.set(Calendar.MINUTE, cc.get(Calendar.MINUTE) - less);
		return Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(cc.getTimeInMillis()));
	}

	/**
	 * 国际票时间处理 得到yyyymmdd格式
	 */
	public static String formatDateGJ(String date) {
		String date1 = date.substring(0, date.indexOf("T"));
		String[] splitDate = date1.split("-");
		String month = splitDate[1];
		if (month.length() == 1) {
			month = "0" + month;
		}
		String day = splitDate[2];
		if (day.length() == 1) {
			day = "0" + day;
		}
		return splitDate[0] + month + day;
	}

	/**
	 * 得到hhmm
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTimeGJ(String date) {
		int begin = date.indexOf("T");
		int over = date.indexOf(".");
		String date1 = date.substring(begin + 1, over);// 得到 hh:mm:ss
		StringBuffer str = new StringBuffer();
		str.append(date1.substring(0, 2));
		str.append(date1.substring(3, 5));
		return str.toString();
	}

	/**
	 * 得到形如： 2012-08-25T18：00形式的日期
	 * 
	 * @param date
	 *            传递 20120825
	 * @param time
	 *            传递1800
	 * @return
	 */
	public static String getDateTime(String date, String time) {
		StringBuilder str = new StringBuilder();
		str.append(date.substring(0, 4)).append("-").append(date.substring(4, 6)).append("-").append(date.substring(6, 8));
		str.append("T");
		str.append(time.substring(0, 2)).append(":").append(time.substring(2, 4));
		return str.toString();
	}

	// 针对微博日期格式 ： Thu Jul 05 04:40:43 +0800 2012 转换为对应的String
	public static String parseDate(String str) throws ParseException {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date date = sdf.parse(str);
		String res = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		return res;
	}

	public static String formatEnDate(String date) throws ParseException {
		Date d = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(date);
		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMMyy", Locale.ENGLISH);
		return formatDate.format(d).toUpperCase();
	}

	public static String formatEnTime(String time) throws ParseException {
		Date d = new SimpleDateFormat("HHmm", Locale.ENGLISH).parse(time);
		SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		return formatDate.format(d);
	}

	/**
	 * 指定时间格式转换
	 * 
	 * @param time
	 * @param format1
	 * @param format2
	 * @return
	 * @throws ParseException
	 */
	public static String timeFormat(String time, String format1, String format2) throws ParseException {
		Date d = new SimpleDateFormat(format1, Locale.ENGLISH).parse(time);
		SimpleDateFormat formatDate = new SimpleDateFormat(format2, Locale.ENGLISH);
		return formatDate.format(d);
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getFirstDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return Integer.parseInt(dateFormate(c.getTime(), "yyyyMMdd"));
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getLastDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		return Integer.parseInt(dateFormate(c.getTime(), "yyyyMMdd"));
	}
	
	/**
	 * 将XLM通用时间日期型转换为日期 2010-01-12T13:00:00+08:00 -> 20100112130000
	 * 
	 * @param dateTime
	 *            String
	 * @return String
	 */
	public static String dateTimeFormat(String dateTime) {
		// 2010-01-12T13:00:00+08:00
		return dateTime.substring(0, dateTime.indexOf("+")).replace("T", "").replace("-", "").replace(":", "");
	}
	
	/**
	 * 
	 * @param currentDate 20140514
	 * @param month 1,2,3.....
	 * @return 
	 *  从当前时间(例:20140514)开始获得n个月后的每一天的日期，返回一个20140514时间格式字符串的集合。
	 *  20140514 20140515 ......20140614 zhou_dong
	 */
	public static List<String> getAfterEveryDay(String currentDate,Integer month)
	{
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    try{
            Date dateOne = dateFormat.parse(currentDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOne);//当前日期
            Calendar calendarTwo = Calendar.getInstance();
            calendarTwo.setTime(dateOne);
            calendarTwo.add(Calendar.MONTH, month);//几个月后的日期
            while(calendar.getTime().before(calendarTwo.getTime())){                
                dateList.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dateList;
	}
	
}
