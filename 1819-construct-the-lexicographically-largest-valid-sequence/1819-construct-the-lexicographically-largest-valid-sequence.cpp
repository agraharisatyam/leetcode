class Solution {
public:
    int n , az;
    unsigned viz = 0;
    vector<int>ans;
    bool dfs(int pos){
        if(pos == az)
           return popcount(viz) == n;
        if(ans[pos] != 0)
            return dfs(pos + 1);

        for(int j = n ; j >= 1 ; j--){
            if((viz >> j)&1)
              continue;
            int next_pos = (j >1 ) ? pos + j : pos;

            if(next_pos >= az || ans[next_pos] != 0)
              continue;

              ans[pos] = ans[next_pos] = j;
              viz |=(1<<j);

              if(dfs(pos + 1)) 
              return 1;

              ans[pos] = ans[next_pos] = 0;
              viz&=(~(1<<j)); 
        }       
        return 0;
    }
    vector<int> constructDistancedSequence(int n) {
        this->n = n;
        az = 2 * n - 1;
        ans.assign(az , 0);
        dfs(0);
        return ans;
    }
};