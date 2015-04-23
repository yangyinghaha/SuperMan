package zixunFragment;

import data.Contents;

/**
 * Created by aaa on 15-4-21.
 */
public class ShouYe extends Fragment_ZiXun_base{

    public ShouYe(int fid, String title) {
        super(fid, title);
    }

    @Override
    public String setStr() {
        return Contents.shouye;
    }
}
