package rbrown.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class FragmentAllAppsScreen extends Fragment {
    //ScreenSlidePagerActivity.OnHeadlineSelectedListener callback;
    private Context context;
    private Activity activity;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<String> packageNames = new ArrayList<>();
    private List<String> appNameList = new ArrayList<>();
    private PackageManager packageManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_screen_slide_page_all_apps, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        activity = getActivity();
        context = activity.getApplicationContext();

        recyclerView = (RecyclerView) getView().findViewById(R.id.rViewAllApps);

        //improve performance if layout size of recyclerview doesnt change
        recyclerView.setHasFixedSize(true);

        //Use linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        fetchAppList();
        recyclerView.setAdapter(adapter);

        //get PackageManager
        packageManager = context.getPackageManager();
        //Launch App
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("TAG","packageNames " + packageNames.get(position));
                startActivity(packageManager.getLaunchIntentForPackage(packageNames.get(position)));
            }
        });
    }

    private void fetchAppList() {
        packageNames.clear();
        appNameList.clear();

        //Add in blanks
        for(int i = 0; i < 4; i++) {
            appNameList.add("");
            packageNames.add("");
        }

        for(ResolveInfo resolver : getActivities(context.getPackageManager())) {
            String appName = (String) resolver.loadLabel(context.getPackageManager());
            appNameList.add(appName);
            packageNames.add(resolver.activityInfo.packageName);
        }

        //Add in blanks
        for(int i = 0; i < 7; i++) {
            appNameList.add("");
            packageNames.add("");
        }

        adapter = new AdapterAllApps(context, packageManager, appNameList, packageNames);
        Log.d("TAG","App List Fetched");
    }

    private List<ResolveInfo> getActivities(PackageManager packageManager) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        Collections.sort(activities, new ResolveInfo.DisplayNameComparator(packageManager));
        return activities;
    }
}
