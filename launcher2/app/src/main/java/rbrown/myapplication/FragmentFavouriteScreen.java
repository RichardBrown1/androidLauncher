package rbrown.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class FragmentFavouriteScreen extends Fragment {
    private Context context;
    private Activity activity;

    private ArrayList<FavItem> arrFavItems = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private PackageManager packageManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_screen_slide_page_favourites, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        activity = getActivity();
        context = activity.getApplicationContext();

        //get PackageManager
        packageManager = context.getPackageManager();

        generateArrFavItems();
        setUpRecyclerView();

    }

    public void generateArrFavItems() {
        //setup read mode
        SharedPreferences sharedPreferences = context.getSharedPreferences("BrownLauncher", MODE_PRIVATE);
        Set<String> retrieved = new HashSet<>(sharedPreferences.getStringSet("favNames", new HashSet<String>()));

        //Add items to list for adapter
        arrFavItems.clear();
        for (String pkgName : retrieved) {
            arrFavItems.add(new FavItem(pkgName, packageManager));
        }
        Collections.sort(arrFavItems, sortByName);
    }

    public void setUpRecyclerView() {
        recyclerView = (RecyclerView) Objects.requireNonNull(getView()).findViewById(R.id.rViewFavs);

        //improve performance if layout size of recyclerview doesnt change
        recyclerView.setHasFixedSize(true);

        //Use linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //Adapter
        adapter = new AdapterFavourites(arrFavItems, context);
        recyclerView.setAdapter(adapter);

        //Launch App
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("TAG", arrFavItems.get(position).getAppName());
                startActivity(packageManager.getLaunchIntentForPackage(arrFavItems.get(position).getPkgName()));
            }
        });
    }

    public static Comparator<FavItem> sortByName = new Comparator<FavItem>() {
        @Override
        public int compare(FavItem item1, FavItem item2) {
            if(item1.getAppName().equalsIgnoreCase("Settings")) {
                return 1;
            } else if(item2.getAppName().equalsIgnoreCase("Settings")) {
                return -1;
            }

            return item1.getAppName().compareToIgnoreCase(item2.getAppName());
        }
    };
}
