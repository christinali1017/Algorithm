package leetcode;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] strs1 = version1.split("\\.");
        String[] strs2 = version2.split("\\.");
        int len = Math.min(strs1.length, strs2.length);
        for(int i = 0; i < len; i++){
        	int val1 = Integer.valueOf(strs1[i]);
        	int val2 = Integer.valueOf(strs2[i]);
        	if(val1 > val2) return 1;
        	else if (val1 < val2) return -1;
        }
        /* note 1.0 and 1 are equal */
        if(strs1.length > strs2.length && Integer.valueOf(strs1[strs1.length-1]) != 0) return 1;
        else if(strs1.length < strs2.length && Integer.valueOf(strs2[strs2.length-1]) != 0) return -1;
        else return 0;
    }
    
    public static void main(String[] args) {
    	CompareVersionNumbers c = new CompareVersionNumbers();
    	System.out.println(c.compareVersion("1.000", "1"));
	}
}
