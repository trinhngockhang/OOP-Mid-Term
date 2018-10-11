import java.util.ArrayList;

public class LCS {
	public static ArrayList convertArrList(String s) {
		ArrayList data = new ArrayList();
		int start = 0;
		int i = 0;
		if(s.startsWith(" ")) {
			i = 1;
			start = 1;
		}
		while(i != -1) {
			i = s.indexOf(' ',i +1);
			if(i != -1) {
				String temp = s.substring(start,i);
				temp.trim();
				data.add(temp);
			}else {
				String temp = s.substring(start,s.length());
				temp.trim();
				data.add(temp);
			}
			start = i+1;
		}
		
		return data;
	}

	public static ArrayList LCS(ArrayList arr1,ArrayList arr2) {
		ArrayList trueAns = new ArrayList();
		int long1 = arr1.size();
		int long2 = arr2.size();
		for(int i = 0;i < long1;i++) {
			int x = i <= 5 ? 0: i -5;
			for(int j = x ;j < long2;j++) {
				
				String s1 = arr1.get(i).toString();
				String s2 = arr2.get(j).toString();
				System.out.println("so sanh: " + s1 + " voi " + s2);
				if(s1.equals(s2)) {
					trueAns.add(s1);
					j = long2 + 1;
				}
			}
		}
		return trueAns;
	}
}
