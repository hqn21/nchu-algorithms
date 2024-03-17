#include<bits/stdc++.h>
 
#define iof ios::sync_with_stdio(0);cin.tie(0);cout.tie(0)
#define int long long
 
using namespace std;
 
struct p{
	int val,id1,id2;
};
 
signed main(){
	iof;
	int n,m;
	cin>>n>>m;
	
	int arr[n];
	for(int a=0;a<n;a++)cin>>arr[a];
	
	vector<p> v;
	for(int a=0;a<n;a++){
		for(int b=a+1;b<n;b++)v.push_back({arr[a]+arr[b],a+1,b+1});
	}
	sort(v.begin(),v.end(),[](p p1,p p2){return p1.val<p2.val;});
	
	int l=0,r=v.size()-1;
	while(l<r){
		if(v[l].val+v[r].val==m){
			if(
				v[l].id1!=v[r].id1
				&&v[l].id1!=v[r].id2
				&&v[l].id2!=v[r].id1
				&&v[l].id2!=v[r].id2
			)break;
			else if(v[l].val==v[l+1].val)l++;
			else r--;
		}	
		else if(v[l].val+v[r].val>m)r--;
		else l++;
	}
	
	if(l<r)cout<<v[l].id1<<" "<<v[l].id2<<" "<<v[r].id1<<" "<<v[r].id2<<endl;
    else cout<<"IMPOSSIBLE"<<endl;
}
