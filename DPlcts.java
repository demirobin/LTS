package longestTS;

import java.util.Scanner;
import java.io.*;
import longestTS.MyStringRandomGen;

import longestTS.DPpoint2;

public class DPlcts {
	public static char[] comalphabet(String input1, String input2) {
		 String one = input1.replaceAll("(.)(?=.*?\\1)", "");
		 String two = input2.replaceAll("(.)(?=.*?\\1)", "");
		 String com = one+two;
		 String unique = com.replaceAll("(.)(?=.*?\\1)", "");
		 char[] ab = new char[unique.length()];
		 for(int i=0; i<unique.length();i++) {
			 ab[i]=unique.charAt(i);
		 }
		 return ab;
	}
	
	public static DPpoint2[][][][] DP4D(char[] input1, char[] input2){
		int length= input1.length;
		DPpoint2[][][][] DPtable=new DPpoint2[length][length][length][length];
		for(int i1=0;i1<length;i1++) {
			for(int j1=0;j1<=i1;j1++) {
				for(int i2=0;i2<length;i2++) {
					for(int j2=0;j2<=i2;j2++) {
				DPtable[i1][j1][i2][j2]=new DPpoint2(i1+1,j1+1,i2+1,j2+1);
				if(input2[j2]==input2[i2] && input2[j2]==input1[j1] && input2[j2]==input1[i1] && j2<i2 && j1<i1) {
					//updated initialize
					DPtable[i1][j1][i2][j2].initialize(j1+1,j2+1);
				}else {
					DPtable[i1][j1][i2][j2].setlpq(0,0,0);
				}
			}
				}
			}
		}
		return DPtable;
			}
	
	public static DPpoint2[][][][] DP2plus(DPpoint2[][][][] com, int[][] fi1, int[][] fi2){
		int ylength= com.length;
		int siglength= fi1.length;
		DPpoint2[][][][] plus=com;
		int l=1;
		int p=0;
		int q=0;
		int t=0;
		for(int i1=ylength-1; i1>=0; i1--) {
			for(int j1=i1; j1>=0; j1--) {
				for(int i2=ylength-1; i2>=0; i2--) {
					for(int j2=i2; j2>=0; j2--) {
				if(plus[i1][j1][i2][j2].getLength()>0) {
			    l=plus[i1][j1][i2][j2].getLength();
			    p=plus[i1][j1][i2][j2].getPx();
			    q=plus[i1][j1][i2][j2].getQx();
				for(int x=0; x<siglength; x++) {
					if(fi1[x][j1]>0 && fi1[x][i1]>0 && fi2[x][i2]>0 && fi2[x][j2]>0) {
						if(plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPx()<(i1+1) &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQx()<(i2+1) &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPx()!=0 &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQx()!=0) {
						t=plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getLength();
						if((t+1)>l) {
						l= t+1;
						p= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPx();
						q= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQx();
						}else if((t+1)==l) {
							p= Math.min(p, plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPx());
							q= Math.min(q, plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQx());
						}
						}
					}
				}
				plus[i1][j1][i2][j2].setlpq(l,p,q);
		       }
			}
		}
			}
		}
		return plus;
	}
	
	public static char[] proberMax(DPpoint2[][][][] table, char[] input) {
		int ilength= input.length;
		int count=0;
		int l=0;
		int p=0;
		int q=0;
		int add=1;
		int coi=0;
		for(int i1=0;i1<ilength;i1++) {
			for(int j1=0;j1<i1;j1++) {
				for(int i2=0;i2<ilength; i2++) {
					for(int j2=0; j2<i2; j2++) {
				count=table[i1][j1][i2][j2].getLength();
				l=Math.max(l, count);
					}
				}
			}
		}
		
		if(l==0)return null;
		
		char[] half=new char[l];
		for(int i1=0;i1<ilength;i1++) {
			for(int j1=0;j1<i1;j1++) {
				for(int i2=0;i2<ilength;i2++) {
					for(int j2=0; j2<i2;j2++) {
				if(table[i1][j1][i2][j2].getLength()==l) {
					p=table[i1][j1][i2][j2].getPx();
					q=table[i1][j1][i2][j2].getQx();
					coi=i1+1;               
					half[0]=input[i1];
					break;
				}
			}
					if(half[0]==input[i1]) break;
			}
				if(half[0]==input[i1]) break;
		}
			if(half[0]==input[i1]) break;
		}
		int subtract=l-1;
		int tap=0;
		
		for(int i1=coi;i1<ilength;i1++) {
			for(int j1=0;j1<i1;j1++) {
				tap=0;
				for(int i2=0;i2<ilength;i2++) {
					tap=0;
					for(int j2=0;j2<i2;j2++) {
				if(add<l && subtract>0 && table[i1][j1][i2][j2].getPx()==p && table[i1][j1][i2][j2].getQx()==q && table[i1][j1][i2][j2].getLength()==subtract) {
					half[add]=input[i1];
					tap=1;
					add++;
					subtract--;
					break;
				}
			}
					if(tap==1) break;
		}
				if(tap==1) break;
			}
		}
		return half;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException{
		/* To input a text file, use the following code:
		Scanner inFile = new Scanner(new FileReader("C:\\Users\\Robin Alice X\\Documents\\dna.txt"));
		String input1=inFile.nextLine().replaceAll(" ", "");
    	System.out.println("The first character sequence is \n"+input1);
		String input2=inFile.nextLine().replaceAll(" ", "");
    	System.out.println("The second character sequence is \n"+input2);
    	*/
		/* To input two strings as an user, use the following code:
		Scanner enter = new Scanner(System.in);
    	System.out.println("Please enter the first character sequence: ");
		String input1=enter.nextLine();
    	System.out.println("Please enter the second character sequence: ");
    	String input2=enter.nextLine();
    	*/
		//To generate two random strings, use the following code:
		MyStringRandomGen msr = new MyStringRandomGen();
		Scanner enter = new Scanner(System.in);
		System.out.println("Please enter the length of the two sequences you want to generate: ");
		int inputl=enter.nextInt();
		String input1=msr.generateRandomString(inputl);
		String input2=msr.generateRandomString(inputl);
		System.out.println("The first character sequence is \n"+input1);
		System.out.println("The second character sequence is \n"+input2);
		//
		char[] enter1=input1.toCharArray();
		char[] enter2=input2.toCharArray();
		char[] sigma=comalphabet(input1,input2);
		//System.out.println(sigma);
		int[][] fi1=DPlts.phi(sigma, input1.toCharArray());
		int[][] fi2=DPlts.phi(sigma,input2.toCharArray());
		//DPlts.print2D(fi1);
		//System.out.println();
		//DPlts.print2D(fi2);
		DPpoint2[][][][] table4D = DP4D(enter1,enter2);
		DPpoint2[][][][] tableUp = DP2plus(table4D, fi1, fi2);
		char[] max=proberMax(tableUp, enter1);
		System.out.println("Common LTS:");
		if(max!=null) {
		char[] lcts=DPlts.concatenate(max);
		System.out.println(Lts.comparison(enter1, enter2, lcts));
		}else {
			System.out.println("Non-existent");
		}
		enter.close();
	}
}
