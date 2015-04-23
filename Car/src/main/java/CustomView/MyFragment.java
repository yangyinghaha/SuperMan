package CustomView;

import android.support.v4.app.Fragment;

public class MyFragment extends Fragment{
	private int fid;
	private String title;

    public MyFragment() {
    }

    public MyFragment(int fid, String title) {
		super();
		this.fid = fid;
		this.title = title;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
