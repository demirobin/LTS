package longestTS;

public class DPpoint {

	private int i;
	private int j;
	private int l;
	private int p;
	public DPpoint(int i, int j) {
		if(j>i) {
			throw new IllegalArgumentException("invalid point");
		}
		this.i=i;
		this.j=j;
		this.l=0;
		this.p=0;
	}
	
	public void setlp(int l, int p) {
		this.l=l;
		this.p=p;
	}
	
	public int getLength() {
		return this.l;
	}
	
	public int getPx() {
		return this.p;
	}
	
	public int geti() {
		return this.i;
	}
	
	public int getj() {
		return this.j;
	}
	
	public void initialize(int i, int j, int p) {
		this.l=1;
		this.p=p;
		this.i=i;
		this.j=j;
	}
	
	public void printdpp() {
		System.out.print("("+this.l+","+this.p+")"+"\t");
	}
}
