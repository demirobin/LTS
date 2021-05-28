package longestTS;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DPpoint2 {
	
		private int i1;
		private int j1;
		private int i2;
		private int j2;
		private int l;
		private int p;
		private int q;
		private ArrayList<Integer> pz= new ArrayList<Integer>();
		private ArrayList<Integer> qz= new ArrayList<Integer>();
		//private int[] p;
		//private int[][] pairs;
		
		public DPpoint2(int i1, int j1, int i2, int j2) {
			if(j1>i1 || j2>i2) {
				throw new IllegalArgumentException("invalid point");
			}
			this.i1=i1;
			this.j1=j1;
			this.i2=i2;
			this.j2=j2;
			this.l=0;
		}
		
		public void setlpq(int l,int p, int q) {
			this.l=l;
			pz.add(p);
			qz.add(q);
		}
		
		public int getLength() {
			return this.l;
		}
		
		public int getPz(int i) {
			return this.pz.get(i);
		}
		
		public int getQz(int i) {
			return this.qz.get(i);
		}
			
		public void initialize(int j1, int j2) {
			this.l=1;
			pz.add(j1);
			qz.add(j2);
		}
		
		public void generator(int l, int j1, int j2) {
			this.l=l;
			ArrayList<Integer> ap= new ArrayList<Integer>();
			ap.add(j1);
			this.pz=ap;
			ArrayList<Integer> aq= new ArrayList<Integer>();
			aq.add(j2);
			this.qz=aq;
		}
		
		public int qzsize() {
			return this.qz.size();
		}
		
		public int pzsize() {
			return this.pz.size();
		}
		
		public void printdpp() {
			System.out.print("("+this.l+","+this.p+","+this.q+")"+"\t");
		}

		public char[] trimpz() {
			List<Integer> listUnique = pz.stream().distinct().collect(Collectors.toList());
	    	ArrayList<Integer> ab=new ArrayList<Integer>();
	    	for(Integer strNumber:listUnique) {
	    	    ab.add(strNumber);
	    }
	    	String unique=ab.toString();
	    	char[] aa=unique.toCharArray();
	    	return aa;
	}
		public void removepair(int p, int q) {
			pz.remove(p);
			qz.remove(q);
		}
		
	}
