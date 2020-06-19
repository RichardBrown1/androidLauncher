package rbrown.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**     * The number of pages (wizard steps).     */
    private static final int NUM_PAGES = 3;

    /**     * The pager widget, which handles animation and allows swiping horizontally to access previous     * and next wizard steps.     */
    private ViewPager2 viewPager;

    /**     * The pager adapter, which provides the pages to the view pager widget.     */
    private FragmentStateAdapter pagerAdapter;

    /** The fragment manager     */
    private FragmentManager fragmentManager;

    private View activityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        fragmentManager = getSupportFragmentManager();
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 1) {
            //super.onBackPressed();
            Log.d("TAG", "backstackCount="+fragmentManager.getBackStackEntryCount());
            Log.d("TAG", "All frags count " + fragmentManager.getFragments().size());
            List<Fragment> fragmentManagerList = fragmentManager.getFragments();

            for(int i = 0; i < fragmentManagerList.size(); i++) {
                Log.d("TAG", fragmentManagerList.get(i).toString());
            }
            //Log.d("TAG, fragments" + fragmentManager.get)
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK));

        } else {
            // Otherwise, move to middle (favs)
            viewPager.setCurrentItem(1);
        }
    }

    public void updateFavouritesFragment(Bundle savedInstanceState){
        //fragmentManager.getFragment(savedInstanceState, );
    }

    /**
     * A simple pager adapter that represents 3 ScreenSlidePageFragment objects, in
     * sequence.
     **/
    public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
         private int fragmentsCreated;
         public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
         }

         @Override
         public Fragment createFragment(int position) {
             fragmentsCreated += 1;
             Log.d("TAG",position + " Position : Fragments Created " + fragmentsCreated);

             switch(position) {
                 case (0): {
                     Log.d("TAG", "create Widgetfrag");
                     return new FragmentWidgetScreen();
                 }
                 case (1): {
                     Log.d("TAG", "create FavouriteScreen frag");
                     return new FragmentFavouriteScreen();
                 }
                 case (2): {
                     Log.d("TAG", "create AllAppsScreen frag");
                     //FragmentFavouriteScreen fragmentFavouriteScreen = new FragmentFavouriteScreen();
                     //fragmentFavouriteScreen.setNameAndPkgLists(favouriteAppNames, favouritePackageNames);
                     return new FragmentAllAppsScreen();
                 }
             }
             //shouldnt happen
             return null;
         }

         @Override
         public int getItemCount() {
             return NUM_PAGES;
         }

         public void recreateFragment() {
             this.createFragment(1);
         }
     }
}
