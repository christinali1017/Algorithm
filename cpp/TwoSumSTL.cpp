class Solution {
public:
    vector<int> twoSum(vector<int> &numbers, int target) {
        map<int, int> d;
        int i = 0;
        auto it = numbers.begin();
        
        goto first_iter;
        do {
            i ++;
            it ++;
            
            if (d.find(target - *it) != d.end()) {
                vector<int> b(2);
                b[0] = d.find(target - *it)->second + 1;
                b[1] = i + 1;
                return b;
            }
        first_iter:
            d[*it] = i;
        } while (true);
    }
};