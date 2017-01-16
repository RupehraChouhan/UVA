#include <iostream>
#include <vector>
using namespace std;

int countSwaps(int shoes[], int size) {
  int count=0;
  int index=0;
  for(int i=0; i<size-1; i++) {
    if(shoes[i]!=shoes[i+1]) {
      for(int j=i+2; j<size; j++) {
        if(shoes[j] == shoes[i]){
          index=j;
          break;
        }
      }
      int temp = shoes[i+1];
      shoes[i+1] = shoes[index];
      shoes[index] = temp;
      count++;
      i++;
    } else
      i++;
  }
  return count;
}

int main(){
	int t, size;
  cin >> t;
  vector<int> ans;
  ans.reserve(t);
  for(int j=0; j<t; j++) {
    cin >> size;
    int shoes[size*2];
    for(int i=0;i<size*2; i++) {
      cin >> shoes[i];
    }
    ans.push_back(countSwaps(shoes, size*2));
  }
  for(int i=0; i<ans.size(); i++) {
    cout << ans[i] << endl;
  }
  return 0;
}
