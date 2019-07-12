public class Lts {
    public static int indexl(char[] s) {  
	  int n = s.length;
      int a,c,k,l,t,max,min=0;
      int[] A = new int[n];
      int[][] Dk= new int[n][n];
      int[][] Fk= new int[n][n];
      //the original
       for(k=1;k<=n;k++) {
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
		  for(a=1;a<=k;a++) {
			  if(A[a-1]>k) t++;}
			  if(t>c) {
				  c=t;
				  l=k;
			  }
		  }
	  
      for(int q=0;q<n;q++) {
    	  System.out.println(A[q]);
      }
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
    	for(int u=0;u<n;u++) {
    	System.out.print(Dk[i][u]);
      }
    	System.out.println();
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
      for(int u=0;u<n;u++) {
    	  for(int b=0;b<n;b++) {
    		  System.out.print(Fk[u][b]);
    	  }
    	  System.out.println();
      }
      return l;
    }
    	 
    public static void main(String[] args) {
    	char[] s = {'A','B','C','B','B','C','A','B','A','B','A','C'};
    	indexl(s);
    	System.out.println(indexl(s));
    }
}
