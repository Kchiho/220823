package member;

public class MemberVO {		
	private int mpk;
	private String mid;
	private String mpw;
	private String mname;
	public int getMpk() {
		return mpk;
	}
	public void setMpk(int mpk) {
		this.mpk = mpk;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	@Override
	public String toString() {
		return "MemberVO [mpk=" + mpk + ", mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + "]";
	}

}
