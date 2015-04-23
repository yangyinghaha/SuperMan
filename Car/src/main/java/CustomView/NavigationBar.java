package CustomView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

/**
 * Created by aaa on 15-4-21.
 */
public class NavigationBar extends HorizontalScrollView {

    private View ll_content;
    private View ll_more;
    private View rl_column;
    private ImageView leftImage;
    private ImageView rightImage;
    private int mScreenWitdh = 0;
    private Activity activity;

    public NavigationBar(Context context) {
        super(context);

    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setParam(Activity activity, int mScreenWitdh,View paramView1,ImageView paramView2, ImageView paramView3 ,View paramView4,View paramView5){
        this.activity = activity;
        this.mScreenWitdh = mScreenWitdh;
        ll_content = paramView1;
        leftImage = paramView2;
        rightImage = paramView3;
        ll_more = paramView4;
        rl_column = paramView5;
    }

    protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // TODO Auto-generated method stub
        super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        shade_ShowOrHide();
        if(!activity.isFinishing() && ll_content !=null && leftImage!=null && rightImage!=null && ll_more!=null && rl_column !=null){
            if(ll_content.getWidth() <= mScreenWitdh){
                leftImage.setVisibility(View.GONE);
                rightImage.setVisibility(View.GONE);
            }
        }else{
            return;
        }
        if(paramInt1 ==0){
            leftImage.setVisibility(View.GONE);
            rightImage.setVisibility(View.VISIBLE);
            return;
        }
        if(ll_content.getWidth() - paramInt1 + ll_more.getWidth() + rl_column.getLeft() == mScreenWitdh){
            leftImage.setVisibility(View.VISIBLE);
            rightImage.setVisibility(View.GONE);
            return;
        }
        leftImage.setVisibility(View.VISIBLE);
        rightImage.setVisibility(View.VISIBLE);
    }
    public void shade_ShowOrHide() {
        if (!activity.isFinishing() && ll_content != null) {
            measure(0, 0);
            //如果整体宽度小于屏幕宽度的话，那左右阴影都隐藏
            if (mScreenWitdh >= getMeasuredWidth()) {
                leftImage.setVisibility(View.GONE);
                rightImage.setVisibility(View.GONE);
            }
        } else {
            return;
        }
        //如果滑动在最左边时候，左边阴影隐藏，右边显示
        if (getLeft() == 0) {
            leftImage.setVisibility(View.GONE);
            rightImage.setVisibility(View.VISIBLE);
            return;
        }
        //如果滑动在最右边时候，左边阴影显示，右边隐藏
        if (getRight() == getMeasuredWidth() - mScreenWitdh) {
            leftImage.setVisibility(View.VISIBLE);
            rightImage.setVisibility(View.GONE);
            return;
        }
        //否则，说明在中间位置，左、右阴影都显示
        leftImage.setVisibility(View.VISIBLE);
        rightImage.setVisibility(View.VISIBLE);
    }
}
