package org.fireking.universal_chart.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fireking.ui_component.ringview.RingView;
import org.fireking.universal_chart.R;

public class RingViewFragment extends Fragment {

    public RingViewFragment() {
    }

    public static RingViewFragment newInstance() {
        RingViewFragment fragment = new RingViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ring_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RingView ringView1 = (RingView) this.getView().findViewById(R.id.ringview1);
        final RingView ringView2 = (RingView) this.getView().findViewById(R.id.ringview2);
        final RingView ringView3 = (RingView) this.getView().findViewById(R.id.ringview3);
        ringView1.setText(35);
        ringView2.setText(89);
        ringView3.setText(69);

        final TextView reload = (TextView) getView().findViewById(R.id.reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringView1.setText(35);
                ringView2.setText(89);
                ringView3.setText(69);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
