package rbrown.myapplication;

import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentWidgetScreen extends Fragment {
    AppWidgetManager appWidgetManager;
    AppWidgetHost appWidgetHost;
    Context context;
    Activity activity;
    View rootView;

    //Arbitrary garbage
    private static final int APPWIDGET_HOST_ID = 0x1337;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_screen_slide_page_widgets, container, false);

        Log.d("", rootView.hasOnClickListeners() + "" );

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        context = getContext();
        activity = getActivity();


        appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetHost = new AppWidgetHost(context, APPWIDGET_HOST_ID );

        //this.onCreateContextMenu();
    }

}
