#include <iostream>
using namespace std;

int main() {
  int n;
  //freopen("output.txt","w",stdout);

  while(cin >> n && n ) {
    int arr[n];
    long long sum = 0;
    long long maxSum = 0;
    for(int i = 0; i < n; i++) {
      cin >> arr[i];
      sum += arr[i];
      maxSum = max(maxSum, sum);
      if(sum < 0)
        sum = 0;
    }

    if (maxSum > 0)
      cout << "The maximum winning streak is " << maxSum << "." << endl;
    else
      cout << "Losing streak." << endl;
  }
  return 0;
}
