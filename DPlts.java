package longestTS;

import longestTS.DPpoint;

public class DPlts {

	public static char[] alphabet(String input) {
		    String unique = input.replaceAll("(.)(?=.*?\\1)", "");
		    int length= unique.length();
		    char[] ab=new char[length];
		    for(int i=0;i<length;i++) {
		    	ab[i]=unique.charAt(i);
		    }
		    return ab;
	}
	
	public static int[][] phi(char[] alphabet, char[] input) {
		int sigl=alphabet.length;
		int il=input.length;
		int app=0;
		int[][] fi=new int[sigl][il];
		for(int sig=0;sig<sigl;sig++) {
			app=0;
			for(int i=il-1;i>=0;i--) {
				fi[sig][i]=app;
				if(input[i]==alphabet[sig]) {
					app=i+1;
				}
			}
		}
		return fi;
	}
	
	//print a 2D array in a reverse way e.g.
	//array[0]=123
	//array[1]=456
	//print:
	//1 4
	//2 5
	//3 6
	public static void print2D(int[][] array) {
		int col=array.length;
		int row=array[0].length;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				System.out.print(array[j][i]+"\t");
			}
			System.out.println();
		}
	}
	
	public static DPpoint[][] DPtable(char[] input){
		int length= input.length;
		DPpoint[][] DPtable=new DPpoint[length][length];
		for(int i=0;i<length;i++) {
			for(int j=0;j<=i;j++) {
				DPtable[i][j]=new DPpoint(i+1,j+1);
				if(input[j]==input[i] && j<i) {
					DPtable[i][j].initialize(i+1,j+1,j+1);
					//DPtable[i][j].printdpp();
				}else {
					DPtable[i][j].setlp(0, 0);
					//System.out.print("-"+"\t");
				}
			}
			//System.out.println();
		}
		return DPtable;
	}
	
	public static void printDPtable(DPpoint[][] input) {
		int ylength= input.length;
		for(int i=0;i<ylength;i++) {
			for(int j=0;j<=i;j++) {
				if(input[i][j].getLength()>=1 && j<i) {
					input[i][j].printdpp();
				}else {
					System.out.print("-"+"\t");
				}
			}
			System.out.println();
		}
	}
	
	public static DPpoint[][] DPplus(DPpoint[][] com, int[][] fi){
		int ylength= com.length;
		int siglength= fi.length;
		DPpoint[][] plus=com;
		int l=1;
		int p=0;
		int t=0;
		for(int i=ylength-1; i>=0; i--) {
			for(int j=i; j>=0; j--) {
				if(plus[i][j].getLength()!=0) {
			    l=plus[i][j].getLength();
			    p=plus[i][j].getPx();
				for(int x=0; x<siglength; x++) {
					if(fi[x][j]>0 && fi[x][i]>0) {
						if(plus[fi[x][i]-1][fi[x][j]-1].getPx()<plus[i][j].geti() && plus[fi[x][i]-1][fi[x][j]-1].getPx()!=0) {
						t=plus[fi[x][i]-1][fi[x][j]-1].getLength();
						if((t+1)>l) {
						l= t+1;
						p= plus[fi[x][i]-1][fi[x][j]-1].getPx();
						}else if((t+1)==l) {
							p= Math.min(p, plus[fi[x][i]-1][fi[x][j]-1].getPx());
						}
						}
					}
				}
				plus[i][j].setlp(l, p);
		       }
			}
		}
		return plus;
	}
	
	public static char[] proberMax(DPpoint[][] table, char[] input) {
		int ilength= table.length;
		int count=0;
		int l=0;
		int p=0;
		int add=1;
		int coi=0;
		for(int i=0;i<ilength;i++) {
			for(int j=0;j<i;j++) {
				count=table[i][j].getLength();
				l=Math.max(l, count);
			}
		}
		char[] half=new char[l];
		for(int i=0;i<ilength;i++) {
			int j=0;
				if(table[i][j].getLength()==l) {
					p=table[i][j].getPx();
					coi=table[i][j].geti();
					half[0]=input[i];
					break;
				}else {
					j++;
				}
			}
		int subtract=l-1;
		
		for(int i=coi;i<ilength;i++) {
			for(int j=0;j<i;j++) {
				if(add<l && subtract>0 && table[i][j].getPx()==p && table[i][j].getLength()==subtract) {
					half[add]=input[i];
					add++;
					subtract--;
					break;
				}
			}
		}
		return half;
	}
	
	public static void main(String[] args) {
		String input="ABCBBCABABAC";
		char[] enter= input.toCharArray();
		System.out.println(alphabet(input));
		int[][] fi=phi(alphabet(input),enter);
		DPpoint[][] DP= DPtable(enter);
		printDPtable(DP);
		DPpoint[][] fDP= DPplus(DP,fi);
		printDPtable(fDP);
		System.out.println(proberMax(fDP,enter));
	}
}
