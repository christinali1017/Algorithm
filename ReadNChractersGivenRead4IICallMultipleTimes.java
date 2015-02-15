package leetcode;

public class ReadNChractersGivenRead4IICallMultipleTimes {

	/* read 4 characters each time, return the exact number of characters read. */
	int read4Ptr = 0;
	int read4Count = 0;
	char[] buffer = new char[4];
	int read(char[] buf){
		return 0;
	}
	public int read(char[] buf, int n){	
		int count = 0;
		while(true){
			if(read4Ptr == 0){
				read4Count = read(buffer);
			}
			if(read4Count == 0 || n == count) return count;
			while(count < n && read4Ptr < read4Count){
				buf[count++] =  buffer[read4Ptr++];
			}
			read4Ptr = read4Ptr%read4Count;
			
		}
	}
}
