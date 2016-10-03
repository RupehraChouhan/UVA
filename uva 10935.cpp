#include <iostream>
using namespace std;
#include <queue>

int main(){
	int num;
	int size;
	cin >> num;
	queue <int> input;
	
	input.push(num);
	
	while (num!=0)
	{
		cin >> num;
		input.push(num);
	}
	
	while (input.size() >1)
	{
		int x = 1;
		int total_cards = input.front();
		input.pop();
		queue<int> q;

		for (int i= 1;i<total_cards+1;i++)
		{
			q.push(i);
		}
		cout << "Discarded cards: ";
		while (q.size()>1)
		{
			if(x%2 ==1)
			{
				cout << q.front();
				if (q.size()> 2){
					cout << ", ";
				}
				q.pop();
			}
			
			else{
				q.push(q.front());
				q.pop();
			}
			x++;
		}
		cout << "\nRemaining card: " << q.front() << "\n";
	
		
	}
	return 0;
}

