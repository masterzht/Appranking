package masterzht.top.appranking.ui.fragment.fourth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import masterzht.top.appranking.R;
import masterzht.top.appranking.base.fragmentation.BaseMainFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourthTabFragment extends SupportFragment {


    public static FourthTabFragment newInstance(){
        Bundle args = new Bundle();

        FourthTabFragment fragment = new FourthTabFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth_tab, container, false);
    }

}
