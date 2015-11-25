package com.aleksandrp.schoolbooksleeveel1.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.aleksandrp.schoolbooksleeveel1.frament.BooksFragment;
import com.aleksandrp.schoolbooksleeveel1.frament.GDZFragment;


/**
 * Created by Aleksandr on 19.11.2015.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabAdapter(FragmentManager fm, int numberOfTabst) {
        super(fm);
        this.numberOfTabs = numberOfTabst;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BooksFragment.getInstance();
            case 1:
                return GDZFragment.getInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
