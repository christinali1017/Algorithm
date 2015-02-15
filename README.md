
<link rel="stylesheet" href="http://yandex.st/highlightjs/6.2/styles/googlecode.min.css">
 
<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script src="http://yandex.st/highlightjs/6.2/highlight.min.js"></script>
 
<script>hljs.initHighlightingOnLoad();</script>
<script type="text/javascript">
 $(document).ready(function(){
      $("h2,h3,h4,h5,h6").each(function(i,item){
        var tag = $(item).get(0).localName;
        $(item).attr("id","wow"+i);
        $("#category").append('<a class="new'+tag+'" href="#wow'+i+'">'+$(this).text()+'</a></br>');
        $(".newh2").css("margin-left",0);
        $(".newh3").css("margin-left",20);
        $(".newh4").css("margin-left",40);
        $(".newh5").css("margin-left",60);
        $(".newh6").css("margin-left",80);
      });
 });
</script>
<div id="category"></div>



# leetcode
## Overview
* [157 Read N Characters Given Read4](#157-read-n-characters-given-read4)
* [158 Read N Characters Given Read4 II - Call multiple times](#158-read-n-characers-given-read4-ii-call-multiple-times) 
* [159 Longest String with At Most Two Distinct Characters](#159-longest-string-with-at-most-two-distinct-characters)

### 157 Read N Characters Given Read4 
### 158 Read N Characters Given Read4 II - Call multiple times. 
### 159 Longest String with At Most Two Distinct Characters
> Given a string, find longest substring T that contains at most 2 distinct characters.For example, Given s = “eceba”,T is "ece" which its length is 3.

**Idea**: Using a slide window keep track of the substring with at most 2 distinct characters. Each time we encounter the third distinct character, we first calculate the length of the substring, then we move the start position of our slide window to the position that contains only the third character, the first character or the third character, the second character. 

**Example:** Assume the string is accacccbac....., when we encounter "b" at index 7, we calculate the length of substring accaccc and do related operations, then we move the start position of slide window to index 4, then keep going... 

**Solution:**	Time complexity: O(n), map contains only two elements. Here, we can use two variables to replace the hashmap, if we extend the problem to k characters, then hashmap maybe more convenient 


	public String subString(String s){
		int start = 0; // start of longest substring
		int end = 0;   // end of longest substring
		int j = 0;     // start of slide window
		int max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			/*check for string contains <= 2 characters.
			  && check for longest substring ends at the end of string 
			*/
			if(i == s.length()-1 && map.size() <= 2 && map.containsKey(s.charAt(i))){
				if(end == 0) return s;
				else{
					if(max < s.length() - j){
						start = j;
						end = s.length()-1;	
					}
				}
			}
			if(map.size() == 2 && !map.containsKey(s.charAt(i))){
				if(max < i - j){
					max = i - j ;
					start = j;
					end = i-1;
				}
				j = s.length();
				char needToRemove = ' ';
				for(char c : map.keySet()){
					if(j > map.get(c)){
						j = map.get(c);
						needToRemove = c;
					}
				}
				j++;
				map.remove(needToRemove);
			}
				map.put(s.charAt(i), i);
		}
		return s.substring(start, end+1);
	}
	
	
**Extention:** Find the longest substring contains at most k unique elements. Idea is much similar with k = 2. When we encounter the k+1 character, we need to calculate the length of string do related operations and move slide window, let it contains only k-1 different characters, add the k+1 character, and keep going.  


	
	public String subString(String s, int k){
		int start = 0; // start of longest substring
		int end = 0;   // end of longest substring
		int j = 0;     // start of slide window
		int max = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			/*check for string contains <= 2 characters.
			  && check for longest substring ends at the end of string 
			*/
			if(i == s.length()-1 && map.size() <= k && map.containsKey(s.charAt(i))){
				if(end == 0) return s;
				else{
					if(max < s.length() - j){
						start = j;
						end = s.length()-1;	
					}
				}
			}
			if(map.size() == k && !map.containsKey(s.charAt(i))){
				if(max < i - j){
					max = i - j ;
					start = j;
					end = i-1;
				}
				j = s.length();
				char needToRemove = ' ';
				for(char c : map.keySet()){
					if(j > map.get(c)){
						j = map.get(c);
						needToRemove = c;
					}
				}
				j++;
				map.remove(needToRemove);
			}
				map.put(s.charAt(i), i);
		}
		return s.substring(start, end+1);
	}



