import java.util.ArrayList;

public class LCS {
	public static ArrayList startIndex = new ArrayList(); 
	public static ArrayList endIndex = new ArrayList(); 
	public static ArrayList trueIndex = new ArrayList(); 
	public static ArrayList convertArrList(String s,boolean containTrueIndex) {
		int n = 0;
		
		if(containTrueIndex == true) {
			startIndex.clear();
			endIndex.clear();
		}
		ArrayList data = new ArrayList();
		int start = 0;
		int i = 0;
		if(s.startsWith(" ")) {
			i = 1;
			start = 1;
		}
		while(i != -1) {
			i = s.indexOf(' ',i +1);
			System.out.println("i la " + i);
			boolean out = i == -1 ? (true) : (s.charAt(i-1) != ' ');
			if( out) {
				if(i != -1) {
					String temp = s.substring(start,i);
					temp.trim();
					data.add(temp);
					if(containTrueIndex) {
						startIndex.add(start);
						endIndex.add(i);
					}		
				}else {
					String temp = s.substring(start,s.length());
					temp.trim();
					data.add(temp);
					if(containTrueIndex) {
						startIndex.add(start);
						endIndex.add(s.length());
					}				
				}
			}
			start = i+1;
			trueIndex.add(false);
		}
		
		return data;
	}

	public static ArrayList LCS(ArrayList arr1,ArrayList arr2) {
		boolean lastTrue = false;
		int z = 0;
		ArrayList trueAns = new ArrayList();
		int long1 = arr1.size();
		int long2 = arr2.size();
		for(int i = 0;i < long1;i++) {
			String s1 = arr1.get(i).toString();
			int x = lastTrue? (z ):((i <= 3) ? 0: i - 3);
			System.out.println ( "x la " + x);
			int WordFromAns = i + 5;
			//xet 5 tu bat dau tu tu nhap
			WordFromAns = WordFromAns <= long2 ? WordFromAns:long2;
			for(int j = x ;j < WordFromAns;j++) {
				String s2 = arr2.get(j).toString();
				System.out.println("so sanh: " +j + "   "+  s1 + " voi " + s2);
				if(s1.equalsIgnoreCase(s2)) {
					trueIndex.set(i,true);
					lastTrue = true;
					System.out.println ( "set " + j + " true " + trueIndex.get(j));
					trueAns.add(s1);
					z = j + 1;
					j = long2 + 1;
					
				}else {
					lastTrue = false;
				}
				
			}
		}
		return trueAns;
	}
}
