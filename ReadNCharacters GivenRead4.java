package leetcode;

public class ReadNCharactersGivenRead4 {
	/* read 4 characters each time, return the exact number of characters read. */
	int read(char[] buf){
		return 0;
	};
	public int read(char[] buf, int n){
		char[] buffer = new char[4];
		int count = 0;
		int current = 0;
		int needAdd = 0;
		while(true){
			current = read(buffer);
			needAdd = Math.min(n-count, current);
			for(int i = 0; i < needAdd; i++){
				buf[count++] =  buffer[i];
			}
			if(current < 4 || n == count) return count;
		}
	}

}
