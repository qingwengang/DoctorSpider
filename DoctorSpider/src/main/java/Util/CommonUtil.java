package Util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CommonUtil {
	public static void main(String[] args) {
		System.out
				.println(URLRequest("http://feedback.ebay.com/ws/eBayISAPI.dll?ViewFeedback2&ftab=FeedbackAsSeller&userid=power-stores09&iid=201069957553&de=off&interval=0&items=200"));
	}

	public static Queue<Character> ConvertString(String a) {
		Queue<Character> qc = new LinkedList<Character>();
		if (a == null)
			return qc;
		char[] aa = a.toCharArray();
		for (char k : aa) {
			qc.add(k);
		}
		return qc;
	}

	public static Map<String, String> URLRequest(String URL) {
		URL = URL.toLowerCase();
		Map<String, String> mapRequest = new HashMap<String, String>();
		String[] arrSplit = null;
		String strUrlParam = URL.substring(URL.indexOf('?'));
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");
			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}
}
