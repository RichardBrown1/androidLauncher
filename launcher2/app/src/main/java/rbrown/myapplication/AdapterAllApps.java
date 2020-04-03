package rbrown.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterAllApps extends RecyclerView.Adapter<AdapterAllApps.ViewHolderAllApp> {
    private List<String> listAppNames = new ArrayList<>();
    final private int VIEW_HEADER = 1;
    final private int VIEW_TEXT = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolderAllApp extends RecyclerView.ViewHolder {
        //data items for viewholder
        public TextView textView;

        public ViewHolderAllApp(TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }

    public AdapterAllApps(List<String> listAppNames) {
        this.listAppNames = listAppNames;
    }

    //Create new views invoked by layout manager
    @NonNull
    @Override
    public AdapterAllApps.ViewHolderAllApp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*if(viewType == VIEW_HEADER) {
            TextView vh = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_header, parent, false);
            return new ViewHolderAllApp(vh);
        } else {*/
            //Create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_all_apps, parent, false);
            return new ViewHolderAllApp(v);
        //}
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
