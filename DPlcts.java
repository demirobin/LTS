package longestTS;

import java.util.*;
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
			//
			for(int j1=0;j1<=i1;j1++) {
				for(int i2=0;i2<length;i2++) {
					//
					for(int j2=0;j2<=i2;j2++) {
				DPtable[i1][j1][i2][j2]=new DPpoint2(i1+1,j1+1,i2+1,j2+1);
				if(input2[j2]==input2[i2] && input2[j2]==input1[j1] && input2[j2]==input1[i1] && j2<i2 && j1<i1) {
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
	
	/*public static DPpoint2[][][][] DP2plus(DPpoint2[][][][] com, int[][] fi1, int[][] fi2){
		int ylength= com.length;
		int siglength= fi1.length;
		DPpoint2[][][][] plus=com;
		int l=1;
		int p=0;
		int q=0;
		int t=0;
		int character=0;
		int indexq;
		for(int i1=ylength-1; i1>=0; i1--) {
			//
			for(int j1=i1-1; j1>=0; j1--) {
				for(int i2=ylength-1; i2>=0; i2--) {
					//
					for(int j2=i2-1; j2>=0; j2--) {
				if(plus[i1][j1][i2][j2].getLength()!=0) {
			    l=plus[i1][j1][i2][j2].getLength();
			    p=plus[i1][j1][i2][j2].getPz(0);
			    q=plus[i1][j1][i2][j2].getQz(0);
				for(int x=0; x<siglength; x++) {
					if(fi1[x][j1]>0 && fi1[x][i1]>0 && fi2[x][i2]>0 && fi2[x][j2]>0) {
						for(int c=0; c<plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].pzsize(); c++) {
						if(plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c)<(i1+1) &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c)<(i2+1) &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c)!=0 &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c)!=0) {
						t=plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getLength();
						if((t+1)>l) {
						l= t+1;
						p= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c);
						q= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c);
						plus[i1][j1][i2][j2].generator(l,p,q);
						character=x;
						}
						//problem: p and q must change simultaneously
						if((t+1)==l) {
						p= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c);
						q= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c);
						plus[i1][j1][i2][j2].setlpq(l,p,q);
					}
						
				}
				}
				}
				}
		       }
			}
		}
			}
		}
		return plus;
	}*/
	
	public static DPpoint2[][][][] DP2plus(DPpoint2[][][][] com, int[][] fi1, int[][] fi2){
		int ylength= com.length;
		int siglength= fi1.length;
		DPpoint2[][][][] plus=com;
		int l=1;
		int p=0;
		int q=0;
		int t=0;
		int pr=0;
		int qr=0;

		for(int i1=ylength-1; i1>=0; i1--) {
			//
			for(int j1=i1-1; j1>=0; j1--) {
				for(int i2=ylength-1; i2>=0; i2--) {
					//
					for(int j2=i2-1; j2>=0; j2--) {
				if(plus[i1][j1][i2][j2].getLength()!=0) {
			    l=plus[i1][j1][i2][j2].getLength();
				for(int x=0; x<siglength; x++) {
					if(fi1[x][j1]>0 && fi1[x][i1]>0 && fi2[x][i2]>0 && fi2[x][j2]>0) {
						for(int c=0; c<plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].pzsize(); c++) {
						if(plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c)<(i1+1) &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c)<(i2+1) &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c)!=0 &&
								plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c)!=0) {
						t=plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getLength();
						if((t+1)>l) {
						l= t+1;
						p= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c);
						q= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c);
						plus[i1][j1][i2][j2].generator(l,p,q);
						}
						if((t+1)==l) {
								pr= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getPz(c);
								qr= plus[fi1[x][i1]-1][fi1[x][j1]-1][fi2[x][i2]-1][fi2[x][j2]-1].getQz(c);
								plus[i1][j1][i2][j2].setlpq(l,pr,qr);
							}
						}
						}
				}	
				}
				int stop=0;
				char[] alp=plus[i1][j1][i2][j2].trimpz();
				for(int r=0;r<alp.length;r++) {
					p=alp[r];
					for(int e=0;e<plus[i1][j1][i2][j2].pzsize();e++) {
						if(plus[i1][j1][i2][j2].getPz(e)==p) {
							q=plus[i1][j1][i2][j2].getQz(e);
							stop=e;
							break;
						}
					}
					for(int e=stop;e<plus[i1][j1][i2][j2].pzsize();e++) {
						if(plus[i1][j1][i2][j2].getPz(e)==p) {
							q=Math.min(q,plus[i1][j1][i2][j2].getQz(e));
						}
					}
					for(int e=0;e<plus[i1][j1][i2][j2].pzsize();e++) {
						if(plus[i1][j1][i2][j2].getPz(e)==p && plus[i1][j1][i2][j2].getQz(e)>q) {
							plus[i1][j1][i2][j2].removepair(e, e);
						}
					}
				}
				}
		       }
			}
		}
			}
		
		return plus;
	}
	
	public static char[] proberMax(DPpoint2[][][][] table, char[] input1, char[] input2) {
		int ilength= input1.length;
		int count=0;
		int l=0;
		int p=0;
		int q=0;
		int add=1;
		int coi1=0;
		int coi2=0;
		int coj1=0;
		int coj2=0;
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
		System.out.println("length is "+l);
		
		
		char[] half=new char[l];
		for(int i1=0;i1<ilength;i1++) {
			//
			for(int j1=0;j1<i1;j1++) {
				for(int i2=0;i2<ilength;i2++) {
					//
					for(int j2=0; j2<i2;j2++) {
						//problem here: check if every element in half is identical in input1 and input2
						//solution: needs to update searcher coordinates
				if(table[i1][j1][i2][j2].getLength()==l) {
					p=table[i1][j1][i2][j2].getPz(table[i1][j1][i2][j2].pzsize()-1);
					q=table[i1][j1][i2][j2].getQz(table[i1][j1][i2][j2].qzsize()-1);
					coi1=i1+1;
					coi2=i2+1;
					coj1=j1+1;
					coj2=j2+1;
					half[0]=input1[i1];
				}
			}
			}
		}
		}
		
		int subtract=l-1;
		int tap=0;
		//
		for(int i1=coi1;i1<ilength;i1++) {
			for(int j1=coj1;j1<i1;j1++) {
				tap=0;
				for(int i2=coi2;i2<ilength;i2++) {
					tap=0;
					for(int j2=coj2;j2<i2;j2++) {
						tap=0;
						for(int c=0; c<table[i1][j1][i2][j2].pzsize();c++) {
						if(add<l && subtract>0 && table[i1][j1][i2][j2].getPz(c)==p && table[i1][j1][i2][j2].getQz(c)==q && table[i1][j1][i2][j2].getLength()==subtract) {
					half[add]=input1[i1];
					//System.out.println(half[add]+" "+ i1+" "+j1+" "+i2+" "+j2+" "+p+" "+q);
					tap=1;
					coj2=j2+1;
					coj1=j1+1;
					coi2=i2+1;
					add++;
					subtract--;
					break;
				}
			}
					if(tap==1) break;
		}
				if(tap==1) break;
			}
				if(tap==1) break;
		}
		}
		return half;
	}
	
	public static String comparing(char[]a, char[] lcs){
	int k=0;
	int d=0;
	int length=a.length;
	int count=lcs.length;
	char[] trans=new char[length];
	for(int m=0;m<length;m++) {
		trans[m]='0';
	}
	for(int j=k;j<count;j++) {
		for(int i=d;i<length;i++) {
			if(a[i]==lcs[j]) {
				trans[i]=lcs[j];
				k=j+1;
				d=i+1;
				break;
			}
		}
	}
	String match=new String(trans);
	String trim=match.replaceAll("0", "-");
	return trim;
	}	
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner hello = new Scanner(System.in);
		System.out.println("Options:\na. to input a text file with two sequences;\n"+
	"b. to generate two random strings and find their lcts;\n"+"c. to input two character sequences as an user;\n"+
				"d. to only generate a random sequence of any length; \n"+"e. other options in other java files;\n"+
	"f. to end the program;");
		int end=0;
		while(end==0) {
		System.out.println("\nPlease enter \"a\", \"b\" , \"c\", \"d\" or \"e\" or \"f\"(always enter \"f\" to end the program):");
		String hi=hello.nextLine();
		if(hi.equalsIgnoreCase("a")) {
		// To input a text file, use the following code:
	    Scanner path = new Scanner(System.in);
		System.out.println("Please enter the text file path:(if you want to go back to choices, enter\"back\")");
		String s=path.nextLine();
		if(s.equalsIgnoreCase("back")) continue;
		Scanner inFile = new Scanner(new FileReader(s));
		String input1=inFile.nextLine().replaceAll(" ", "");
    	System.out.println("The first character sequence is \n"+input1);
		String input2=inFile.nextLine().replaceAll(" ", "");
    	System.out.println("The second character sequence is \n"+input2);
    	char[] enter1=input1.toCharArray();
		char[] enter2=input2.toCharArray();
		char[] sigma=comalphabet(input1,input2);
		//System.out.println(sigma);
		int[][] fi1=DPlts.phi(sigma, enter1);
		int[][] fi2=DPlts.phi(sigma,enter2);
		//DPlts.print2D(fi1);
		//System.out.println();
		//DPlts.print2D(fi2);
		DPpoint2[][][][] table4D = DP4D(enter1,enter2);
		DPpoint2[][][][] tableUp = DP2plus(table4D, fi1, fi2);
		char[] max=proberMax(tableUp, enter1,enter2);
		if(max!=null) {
		char[] lcts=DPlts.concatenate(max);
		System.out.println("In the first sequence:");
		System.out.println(comparing(enter1,lcts));
		System.out.println("In the second sequence:");
		System.out.println(comparing(enter2,lcts));
		System.out.print("Common LTS: ");
		System.out.println(lcts);
		}else {
			System.out.print("Common LTS: ");
			System.out.println("Non-existent");
		}
		continue;
		}else if(hi.equalsIgnoreCase("c")) {
		//To input two strings as an user, use the following code:
		Scanner enter = new Scanner(System.in);
    	System.out.println("Please enter the first character sequence: ");
		String input1=enter.nextLine();
    	System.out.println("Please enter the second character sequence: ");
    	String input2=enter.nextLine();
    	char[] enter1=input1.toCharArray();
		char[] enter2=input2.toCharArray();
		char[] sigma=comalphabet(input1,input2);
		//System.out.println(sigma);
		int[][] fi1=DPlts.phi(sigma, enter1);
		int[][] fi2=DPlts.phi(sigma,enter2);
		//DPlts.print2D(fi1);
		//System.out.println();
		//DPlts.print2D(fi2);
		DPpoint2[][][][] table4D = DP4D(enter1,enter2);
		DPpoint2[][][][] tableUp = DP2plus(table4D, fi1, fi2);
		char[] max=proberMax(tableUp, enter1,enter2);
		if(max!=null) {
		char[] lcts=DPlts.concatenate(max);
		System.out.println("The first character sequence is \n"+input1);
		System.out.println("In the first sequence:");
		System.out.println(comparing(enter1,lcts));
		System.out.println("The second character sequence is \n"+input2);
		System.out.println("In the second sequence:");
		System.out.println(comparing(enter2,lcts));
		System.out.print("Common LTS: ");
		System.out.println(lcts);
		}else {
			System.out.println("The first character sequence is \n"+input1);
			System.out.println("The second character sequence is \n"+input2);
			System.out.print("Common LTS: ");
			System.out.println("Non-existent");
		}
		continue;
		}else if(hi.equalsIgnoreCase("b")) {
		//To generate two random strings, use the following code:
		MyStringRandomGen msr = new MyStringRandomGen();
		Scanner enter = new Scanner(System.in);
		System.out.println("Please enter the length of the two sequences you want to genrate(<74): ");
		int inputl=enter.nextInt();
		String input1=msr.generateRandomString(inputl);
		String input2=msr.generateRandomString(inputl);
		char[] enter1=input1.toCharArray();
		char[] enter2=input2.toCharArray();
		char[] sigma=comalphabet(input1,input2);
		//System.out.println(sigma);
		int[][] fi1=DPlts.phi(sigma, enter1);
		int[][] fi2=DPlts.phi(sigma,enter2);
		//DPlts.print2D(fi1);
		//System.out.println();
		//DPlts.print2D(fi2);
		DPpoint2[][][][] table4D = DP4D(enter1,enter2);
		DPpoint2[][][][] tableUp = DP2plus(table4D, fi1, fi2);
		char[] max=proberMax(tableUp, enter1,enter2);
		if(max!=null) {
		char[] lcts=DPlts.concatenate(max);
		System.out.println("The first character sequence is \n"+input1);
		System.out.println("In the first sequence:");
		System.out.println(comparing(enter1,lcts));
		System.out.println("The second character sequence is \n"+input2);
		System.out.println("In the second sequence:");
		System.out.println(comparing(enter2,lcts));
		System.out.print("Common LTS: ");
		System.out.println(lcts);
		}else {
			System.out.println("The first character sequence is \n"+input1);
			System.out.println("The second character sequence is \n"+input2);
			System.out.print("Common LTS: ");
			System.out.println("Non-existent");
		}
		continue;
		}else if(hi.equalsIgnoreCase("d")) {
			MyStringRandomGen msr = new MyStringRandomGen();
			Scanner enter = new Scanner(System.in);
			System.out.println("Please enter the length of the sequence you want to generate: ");
			int inputl=enter.nextInt();
			String usrinput=msr.generateRandomString(inputl);
			System.out.println("The sequence is:\n"+usrinput);
		continue;
		}else if(hi.equalsIgnoreCase("e")) {
			System.out.println("To find the longest tandem subsequence of a single sequence, please go to DPlts.java");
			end++;
		}else if(hi.equalsIgnoreCase("f")){
			end++;
		}else {
			System.out.println("Please don't enter anything beyond the five alphabet letters.");
			continue;
		}
		hello.close();
	}
	}
}
