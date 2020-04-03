package rbrown.myapplication;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListenerAllApps extends RecyclerView.SimpleOnItemTouchListener {
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return super.onInterceptTouchEvent(rv, e);
    }


}