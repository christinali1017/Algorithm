int titleToNumber(char *s) {
    int i = 0, val = 0, x = 1;
    
    if (s[0] == 0) { return 0; }
    for (; s[i+1] != 0; i ++);
    
    for (; i >= 0; i --) {
        val += x * (s[i] - 'A' + 1);
        x *= 26;
    }
    
    return val;
}