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
		// ÿ����ֵΪһ��
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");
			// ��������ֵ
			if (arrSplitEqual.length > 1) {
				// ��ȷ����
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				if (arrSplitEqual[0] != "") {
					// ֻ�в���û��ֵ��������
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}
}
