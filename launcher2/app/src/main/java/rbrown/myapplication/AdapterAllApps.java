package rbrown.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.IntentCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.makeRestartActivityTask;
import static androidx.core.content.ContextCompat.startActivity;

public class AdapterAllApps extends RecyclerView.Adapter<AdapterAllApps.ViewHolderAllApp> {
    private List<String> listAppNames = new ArrayList<>();
    private List<String> packageNames = new ArrayList<>();
    private PackageManager packageManager;
    private Context context;
    final private int VIEW_HEADER = 1;
    final private int VIEW_TEXT = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolderAllApp extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        //data items for viewholder
        TextView textView;
        View holder;

        public ViewHolderAllApp(View itemView) {
            super(itemView);
            textView = (TextView)itemView;
            //textView = (TextView)itemView.findViewById(R.id.tvAllApps);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle(textView.getText());
            //todo improve the context menu
            //Why do only group ids work but not normal ids?
            MenuItem appInfo = contextMenu.add(0, itemView.getId(), 0, "App Info");//groupId, itemId, order, title
            MenuItem pinning = contextMenu.add(1, itemView.getId(), 1, "Pin / Unpin");

            appInfo.setOnMenuItemClickListener(this);
            pinning.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            int allAppsId = getLayoutPosition();

            Log.d("TAG","allAppsID " + allAppsId);
            Log.d("TAG","ContextMenuItemClicked " + menuItem.getGroupId());

            switch(menuItem.getGroupId()) {
                case 0: //appInfo
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    //appIndex = listAppNames.indexOf((String) menuItem.getTitle());

                    intent.setData(Uri.parse("package:"+ packageNames.get(allAppsId)));
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    break;
                case 1: //Add remove to favourites
                    Log.d("TAG", "LONG" + packageNames.get(allAppsId));

                    SharedPreferences sharedPreferences = context.getSharedPreferences("BrownLauncher", MODE_PRIVATE);

                    Set<String> retrievedFavNames = new HashSet<>(sharedPreferences.getStringSet("favNames", new HashSet<String>()));

                    if (!retrievedFavNames.remove(packageNames.get(allAppsId))) {
                        retrievedFavNames.add(packageNames.get(allAppsId));
                    }
                    Log.d("TAG", retrievedFavNames.toString());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putStringSet("favNames", retrievedFavNames);

                    editor.commit();
                    
                    break;
                    //todo
            }
            return true;
        }
    }

    public AdapterAllApps(Context context, PackageManager packageManager, List<String> listAppNames, List<String> packageNames ) {
        Log.d("TAG",listAppNames.toString());
        this.listAppNames = listAppNames;
        this.packageNames = packageNames;
        //get PackageManager
        this.context = context;
        this.packageManager = packageManager;
    }

    //Create new views invoked by layout manager
    @NonNull
    @Override
    public AdapterAllApps.ViewHolderAllApp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_all_apps, parent, false);
        return new ViewHolderAllApp(v);
    }

    //Replace contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(ViewHolderAllApp holder, int position) {
        holder.textView.setText(listAppNames.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount " + listAppNames.size());
        return listAppNames.size();
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0) {
            return 1;
        }
        return 0;
    }
}
