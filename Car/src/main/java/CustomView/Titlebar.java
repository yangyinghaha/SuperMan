package CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.car.R;

public class Titlebar extends RelativeLayout {

    ImageButton fl_title_search;
    TextView tv_title;

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this, true);
        fl_title_search = (ImageButton) findViewById(R.id.fl_title_search);
        tv_title = (TextView) findViewById(R.id.tv_title);

    }
    public void setMode(String str) {
        tv_title.setText(str);
    }

    public ImageButton getFl_title_search() {
        return fl_title_search;
    }
}
