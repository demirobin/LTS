package longestTS;

import java.util.Scanner;

public class Lts {
    public static int indexl(char[] s) {  
	  int n = s.length;
      int a,c,k,l,t,max,min=0;
      int[] A = new int[n];
      int[][] Dk= new int[n][n];
      int[][] Fk= new int[n][n];
      
       for(k=2;k<=n;k++) {
    	  for(a=k-1; a>=1; a--) {
    		  if(s[a-1]==s[k-1]) {
    			  t=A[a-1];
    			  c=a+1;
    			  while(t<k && c<k) {
                   max=Math.max(A[c-1],t );
                   min=Math.min(A[c-1],t );
                   A[c-1]=min;
                   t=max;
    			   c++;
    			  }
    			  A[a-1]=k;
    		  }
    	  }
      }
      c=0;
	  l=1;
	  for(k=1;k<n;k++) {
		  t=0;
		  //examine if Ak(i)>i
		  for(a=1;a<=k;a++) {
			  if(A[a-1]>k) t++;}
	      //examine whether the number of p updates 1, if so, index l
		  //will also be updated to the current index i
		  if(t>c) {
				  c=t;
				  l=k;
			  }
		  }
	  /*Ak(i,j)
      for(int q=0;q<n;q++) {
    	  System.out.print(q+1+"\t");
    	  System.out.println(A[q]);
      }
      */
      //Dk(i,j)
	  
     for(int i=0;i<n;i++) {
    	for(int j=i+1;j<A[i];j++) {
    		Dk[i][j]=1;
    	}
    	for(int m=0;m<i;m++) {
    		Dk[i][m]=0;
    	}
    	for(int w=A[i];w<n;w++) {
    		Dk[i][w]=0;
    	}
    	/*
    	System.out.print(i+1+"\t");
    	for(int u=0;u<n;u++) {
    	System.out.print(Dk[i][u]);
      }
    	System.out.println();
    	*/
      }
      //Fk(i,j)
     for(int y=0;y<n;y++) {
     Fk[0][y]=Dk[0][y];
     }
     for(int p=1;p<n;p++) {
    	  for(int h=p+1;h<n;h++) {
          Fk[p][h]=Dk[p][h]+Fk[p-1][h];
    	  }
    	  for(int r=0;r<p;r++) {
    		  Fk[p][r]=0;
    	  }
      }
     /*
      for(int u=0;u<n;u++) {
    	  System.out.print(u+1+"\t");
    	  for(int b=0;b<n;b++) {
    		  System.out.print(Fk[u][b]);
    	  }
    	  System.out.println();
      }
     */
      return l;
    }
    public static void printChar(char[] s) {
    	int length= s.length;
    	for(int i=0; i<length; i++) {
    		System.out.print(s[i]);
    	}
    	System.out.println();
    }
    
    public static char[] createSub(char[] s, int begin, int end) {
    	char[] sub= new char[end-begin+1];
    	int index=0;
    	for(int i=begin; i<=end; i++) {
    		sub[index]=s[i-1];
    		index++;
    	}
    	return sub;
    }
    
  /*  public static int[][] algAL(char[]a, char[]b) {
    	int m=a.length;
    	int n=b.length;
    	int i,j=0;
    	int[][] L=new int[m][n];
    	for(i=0;i<=m;i++) {
    		L[i][0]=0;
    	}
    	for(j=0;j<=n;j++) {
    		L[0][j]=0;
    	}
    	for(i=1;i<=m;i++) {
    		for(j=1;j<=n;j++) {
    			if(a[i]==b[j]) {
    				L[i][j]=L[i-1][j-1]+1;
    			}else {
    				L[i][j]=Math.max(L[i][j-1],L[i-1][j]);
    			}
    		}
    	}
    	return L;
    }*/
  
    public static int[] algBLL(char[]a, char[]b){
    	int m=a.length;
    	int n=b.length;
    	int i,j=0;
    	int[][] K=new int[2][n+1];
    	int[] LL=new int[n+1];
    	for(j=0;j<=n;j++) {
    	K[1][j]=0;
    	}
    	for(i=1;i<=m;i++) {
    		for(j=0;j<=n;j++) {
        		K[0][j]=K[1][j];    			
    		}
    		for(j=1;j<=n;j++) {
    			if(a[i-1]==b[j-1]) {
    				K[1][j]=K[0][j-1]+1;
    			}else {
    				K[1][j]=Math.max(K[1][j-1], K[0][j]);
    			}
    		}
    	}
    	for(j=0;j<=n;j++) {
    		LL[j]=K[1][j];
    	}
    	return LL;
    }
    
    public static char[] reverse(char[] s) {
    	int n=s.length;
    	int j=n-1;
    	char[] re=new char[n];
    	for(int i=0;i<n;i++) {
    		re[j]=s[i];
    		j--;
    	}
    	return re;
    }
	public static String Alg3 (char[] subA, char[] subB) {
    	
    	int m=subA.length;
    	int n=subB.length;
    	int i,j,count=0;
    	int[] L1;
    	int[] L2;
    	String c="";
    	int k=0;
    	for(j=1;j<=n;j++) {
    		if(subA[0]==subB[j-1]) {
    			count++;
    		}
    		}
    	if(n==0) {
    		c="";
    	}else if(m==1) {
    		if(count>0) {
    			c=c+subA[0];
    		}else {
    			c="";
    		}
    	}else {
    		i=m/2;
        	char[] sub1=createSub(subA,1,i);
        	char[] sub2=createSub(subB,1,n);
        	char[] sub3=reverse(createSub(subA,i+1,m));
        	char[] sub4=reverse(createSub(subB,1,n));
    		L1=algBLL(sub1,sub2);
    		L2=algBLL(sub3,sub4);
    		int largest=L1[0]+L2[n];
    		int compare=0;
    		for(j=1;j<=n;j++) {
    			largest=Math.max(L1[j]+L2[n-j], largest);
    			}
    		for(j=0;j<=n;j++) {
    			compare=L1[j]+L2[n-j];
    			if(compare==largest) {
    				k=j;
    				break;
    			}
    		}
    	String C1=Alg3(createSub(subA,1,i),createSub(subB,1,k));
    	String C2=Alg3(createSub(subA,i+1,m),createSub(subB,k+1,n));
    	c= c+C1+C2;
    	}
    	return c;
	}

	public static char[] stochar(String s) {
		char arr[] = s.toCharArray();
		return arr;
	}
	
	//updated comparison 8.6
	public static String comparison(char[]a, char[] lcs) {
	int k=0;
	int d=0;
	int length=a.length;
	int count=lcs.length;
	char[] trans=new char[length];
	for(int m=0;m<length;m++) {
		trans[m]='0';
	}
	for(int j=k;j<count;j++) {
		for(int i=d;j<length;i++) {
			if(a[i]==lcs[j]) {
				trans[i]=lcs[j];
				k=j+1;
				d=i+1;
				break;
			}
		}
	}
	String match=new String(trans);
	String trim=match.replaceAll("0", " ");
	return trim;
	}
	
	public static String LTS(char[] input) {
		int l= indexl(input);
    	char[] subA=createSub(input,1,l);
    	char[] subB=createSub(input,l+1,input.length);
    	String LTS=Alg3(subA,subB);
    	LTS=LTS+LTS;
    	return LTS;
	}
	
    public static void main(String[] args) {
    	Scanner enter = new Scanner(System.in);
    	/*To input a character string:
    	System.out.println("Please enter a character sequence: ");
    	char[] input=enter.next().toCharArray();
    	*/
    	//To input the length of a random sequence:
    	System.out.println("Please enter the length of the character sequence: ");
    	int len=enter.nextInt();
    	MyStringRandomGen msr = new MyStringRandomGen();
    	char[] input=msr.generateRandomString(len).toCharArray();
    	//System.out.print("The index l for LTS split is: ");
    	int l= indexl(input);
    	System.out.println("The character sequence is: ");
    	System.out.println(input);
    	//System.out.println(l);
    	char[] subA=createSub(input,1,l);
    	char[] subB=createSub(input,l+1,input.length);
    	char[] LCS=stochar(Alg3(subA,subB));
    	System.out.print("Subsequence A="+"\t");
    	printChar(subA);
    	System.out.print("LCS of A&B="+"\t");
    	char[] fLCS=stochar(comparison(subA,LCS));
    	printChar(fLCS);
    	System.out.print("Subsequence B="+"\t");
    	printChar(subB);
    	System.out.print("LCS of A&B="+"\t");
    	char[] fLCS2=stochar(comparison(subB,LCS));
    	printChar(fLCS2);
    	System.out.println("LTS of the sequence entered="+"\t"+ LTS(input));
    	enter.close();
    }
}
