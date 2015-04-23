package CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.example.administrator.car.R;

public class TabManagerView extends RelativeLayout {

	private CheckBox cb0;
	private CheckBox cb1;
	private CheckBox cb2;
	private CheckBox cb3;
	private CheckBox cb4;
	private CheckBox cb5;
	private CheckBox cb6;
	private CheckBox cb7;
	private Button btn_no;
	private Button btn_yes;

	public TabManagerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.view_tabmanager, this,
				true);
		initview();
	}

	public void initview() {
		cb1 = (CheckBox) findViewById(R.id.cb1);
		cb2 = (CheckBox) findViewById(R.id.cb2);
		cb3 = (CheckBox) findViewById(R.id.cb3);
		cb4 = (CheckBox) findViewById(R.id.cb4);
		cb5 = (CheckBox) findViewById(R.id.cb5);
		cb6 = (CheckBox) findViewById(R.id.cb6);
		cb7 = (CheckBox) findViewById(R.id.cb7);
		cb0 = (CheckBox) findViewById(R.id.cb0);
		btn_no = (Button) findViewById(R.id.btn_no);
		btn_yes = (Button) findViewById(R.id.btn_yes);
	}

	public CheckBox getCb1() {
		return cb1;
	}

	public void setCb1(CheckBox cb1) {
		this.cb1 = cb1;
	}

	public CheckBox getCb2() {
		return cb2;
	}

	public void setCb2(CheckBox cb2) {
		this.cb2 = cb2;
	}

	public CheckBox getCb3() {
		return cb3;
	}

	public void setCb3(CheckBox cb3) {
		this.cb3 = cb3;
	}

	public CheckBox getCb4() {
		return cb4;
	}

	public void setCb4(CheckBox cb4) {
		this.cb4 = cb4;
	}

	public CheckBox getCb5() {
		return cb5;
	}

	public void setCb5(CheckBox cb5) {
		this.cb5 = cb5;
	}

	public CheckBox getCb6() {
		return cb6;
	}

	public void setCb6(CheckBox cb6) {
		this.cb6 = cb6;
	}

	public CheckBox getCb7() {
		return cb7;
	}

	public void setCb7(CheckBox cb7) {
		this.cb7 = cb7;
	}

	public CheckBox getCb0() {
		return cb0;
	}

	public void setCb0(CheckBox cb0) {
		this.cb0 = cb0;
	}

	public Button getBtn_no() {
		return btn_no;
	}

	public void setBtn_no(Button btn_no) {
		this.btn_no = btn_no;
	}

	public Button getBtn_yes() {
		return btn_yes;
	}

	public void setBtn_yes(Button btn_yes) {
		this.btn_yes = btn_yes;
	}

}
