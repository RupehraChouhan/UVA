/* UVa problem: 11330
 *
 * Topic: Combinatorics
 *
 * Level: easy
 *
 * Brief problem description:
 *
 *    Given n pairs of shoes in n different colors, they are put together
 *    such that the left shoe is next to the right shoe but they are not
 *    necessarily of the same color. Our job to put the shoes in right
 *    order in minimum steps. 1 step = swapping two shoes.
 *
 * Solution Summary:
 *
 *    This solution uses flip sort to solve the problem. It compares two
 *    shoes next to each other and if they are not the same it finds the
 *    other shoe and swaps it with the non matching one.
 *
 * Used Resources:
 *
 *   ...
 *
 * I hereby certify that I have produced the following solution myself
 * using only the resources listed above in accordance with the CMPUT
 * 403 collaboration policy.
 *
 *
 * --------------------- Rupehra Chouhan
 */

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
