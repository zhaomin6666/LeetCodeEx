package LeetCode;

public class LEET005 {
	public String longestPalindrome(String s) {
		char[] chars = s.toCharArray();
		if (s.length() == 0) {
			return "";
		}
		int max = 1;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			if (i > 0) {
				int dev1 = 0;
				int len1 = 0;
				loop1 : while (i - dev1 > 0 && i + dev1 < chars.length) { // abba
					if (chars[i + dev1] == chars[i - dev1 - 1]) {
						len1 = len1 + 2;
						if (len1 > max) {
							max = len1;
							index = i + dev1;
						}
						dev1++;
					} else {
						break loop1;
					}
				}
				if (i > 1) {
					int dev2 = 0;
					int len2 = 1;
					loop2 : while (i - dev2 - 1 > 0
							&& i + dev2 < chars.length) { // abcba
						if (chars[i + dev2] == chars[i - dev2 - 2]) {
							len2 = len2 + 2;
							if (len2 > max) {
								max = len2;
								index = i + dev2;
							}
							dev2++;
						} else {
							break loop2;
						}
					}
				}
			}
		}
		return s.substring(index - max + 1, index + 1);
	}

	public static void main(String[] args) {
		System.out.println(new LEET005().longestPalindrome("abcbs"));
		System.out.println(new LEET005().longestPalindrome("abcbs"));
	}
	
	public String longestPalindrome2(String s) {
        int n = s.length();

        int [] range = new int[2];
        for(int i = 0;i<n;i++){
            i = helper(s, range, i);
        }

        return s.substring(range[0],range[1]);
    }

    public int helper(String s,int [] range, int i){
        int lo = i; int hi=i;
        while (hi<s.length()-1 && s.charAt(hi) == s.charAt(hi+1)){
            hi++;
        }

        int ret = hi;
        while (lo>0 && hi<s.length()-1 && s.charAt(lo-1)== s.charAt(hi+1)){
            lo--;
            hi++;
        }

        if(hi-lo +1 > range[1]-range[0]){
            range[0] = lo;
            range[1] = hi+1;
        }

        return ret;
    }
}
