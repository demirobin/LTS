package longestTS;

public class DPpoint2 {
	
		private int i1;
		private int j1;
		private int i2;
		private int j2;
		private int l;
		private int p;
		private int q;
		public DPpoint2(int i1, int j1, int i2, int j2) {
			if(j1>i1 || j2>i2) {
				throw new IllegalArgumentException("invalid point");
			}
			this.i1=i1;
			this.j1=j1;
			this.i2=i2;
			this.j2=j2;
			this.l=0;
			this.p=0;
			this.q=0;
		}
		
		public void setlpq(int l, int p, int q) {
			this.l=l;
			this.p=p;
			this.q=q;
		}
		
		public int getLength() {
			return this.l;
		}
		
		public int getPx() {
			return this.p;
		}
		
		public int getQx() {
			return this.q;
		}
			
		public void initialize(int j1, int j2) {
			this.l=1;
			this.p=j1;
			this.q=j2;
		}
		
		public void printdpp() {
			System.out.print("("+this.l+","+this.p+","+this.q+")"+"\t");
		}
	}
