package com.ibm.ect;

import java.util.ArrayList;

public class SMSTemplateUtil {

	public static void main(String[] args) {
		String template = "很抱歉，<%您的改$param1$期失败了！%><%由于一些$param2$系统原因，%>原客票需要您致电$param3$重新确定行程，给您带来的麻烦我们深表歉意！ ";
		String value = "param1=11&param2=22&param3=33";
		
		ArrayList<String> alList = getString(template, null);
		// HashMap<String, String> hmHashMap =new HashMap<String, String>();

		String[] v = value.split("&");
		for (int i = 0; i < v.length; i++) {
			String[] vlist = v[i].split("=");
			for (int j = 0; j < alList.size(); j++) {
				if (alList.get(j).contains(vlist[0])) {
					if (vlist.length >1 ) {
						if (vlist[1] != null && !"".equals(vlist[1])) {
							alList.set(j, alList.get(j).replace("$" + vlist[0] + "$", vlist[1]));
							break;
						} else {
							alList.remove(j);
						}
					}else {
						alList.remove(j);
					}
				}
			}
		}

		for (int i = 0; i < alList.size(); i++) {
			System.out.print(alList.get(i));
		}

	}

	private static ArrayList<String> getString(String template,
			ArrayList<String> all) {
		ArrayList<String> al = new ArrayList<String>();
		if (all != null && all.size() > 0) {
			al = all;
		}
		int k = template.indexOf("<%");
//		System.out.println(template);

		switch (k) {
		case -1:
			al.add(template);
			break;
		case 0:
			int j = template.indexOf("%>");
			al.add(template.substring(2, j));
			template = template.substring(j + 2);
			getString(template, al);
			break;
		default:
			al.add(template.substring(0, k));
			template = template.substring(k);
			getString(template, al);
			break;
		}
		return al;
	}

	// if(k == -1){
	// al.add(template);
	// return al;
	// }
	//
	//
	// if(k > 0){
	// al.add(template.substring(0, k));
	// template = template.substring(k);
	// return getString(template, al);
	// }
	// if(k == 0){
	// int j = template.indexOf("%>");
	// al.add(template.substring(2, j));
	// template= template.substring(j+2);
	// return getString(template, al);
	// }

	// return al;

}
