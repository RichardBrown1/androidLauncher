package rbrown.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ScreenSlidePageFragment extends Fragment {

    public View viewGroup;

    TextView tvTitle;
    private int position= -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int fragXml = R.layout.fragment_screen_slide_page;
        Log.d("TAG","CreatingView");
        /*
        switch(position) {
            case 0: {
                Log.d("TAG", "onCreateViewWidget?Page");
                break;
            }
            case 1: {
                Log.d("TAG", "onCreateViewHomePage");
                fragXml = R.layout.fragment_screen_slide_page_favourites;
                break;
            }
            case 2: {
                Log.d("TAG", "onCreateViewAllAppsPage");
                break;
            }
            default: {
                Log.d("TAG", "default Switch being hit WTF?");
                //fragXml = R.layout.fragment_screen_slide_page_favourites;
            }
        }
        */
        viewGroup = (ViewGroup) inflater.inflate(
                fragXml, container, false
        );

        tvTitle = (TextView) viewGroup.findViewById(R.id.initText);
        return  viewGroup;
    }

    public void setTvTitle(String value) {
        tvTitle.setText("Favourite");
    }
}
