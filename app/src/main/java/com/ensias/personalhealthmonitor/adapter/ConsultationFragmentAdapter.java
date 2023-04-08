package com.ensias.personalhealthmonitor.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ensias.personalhealthmonitor.ConsultationFragmentPage;
import com.ensias.personalhealthmonitor.Hospitalisation;

public class ConsultationFragmentAdapter extends FragmentPagerAdapter {
    private int[] colors;

    public ConsultationFragmentAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(2);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ConsultationFragmentPage.newInstance();
            case 1:
                return Hospitalisation.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "Consultation";
            case 1: //Page number 2
                return "Hospitalisation";
            default:
                return null;
        }
    }
}
