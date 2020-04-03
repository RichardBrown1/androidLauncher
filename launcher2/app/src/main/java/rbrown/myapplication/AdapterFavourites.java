package rbrown.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AdapterFavourites extends RecyclerView.Adapter<AdapterFavourites.ViewHolderAllApp> {
    private ArrayList<FavItem> favItems = new ArrayList<>();
    private Context context;
    final private int VIEW_HEADER = 1;
    final private int VIEW_TEXT = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolderAllApp extends RecyclerView.ViewHolder {
        //data items for viewholder
        public TextView appName;
        public int textColor;


        public ViewHolderAllApp(View view) {
            super(view);
            appName = (TextView) view.findViewById(R.id.view_FavListItem_AppName);
            //textColor.setTextColor(textColor);
            //appName.setTextColor(-1);
        }
    }

    public AdapterFavourites(ArrayList<FavItem> favItems, Context context) {
        //List<Object> listAppNames = Arrays.asList(setOfAppNames.toArray());
        this.favItems = favItems;
        this.context = context;
    }

    //Create new views invoked by layout manager
    @NonNull
    @Override
    public AdapterFavourites.ViewHolderAllApp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_favlistitem, parent, false);

        return new ViewHolderAllApp(v);
    }

    //Replace contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(ViewHolderAllApp holder, int position) {
        FavItem favItem = favItems.get(position);
        holder.appName.setText(favItem.getAppName());
        //holder.appName.setTextColor(favItem.getColor());
        Log.d("TAG", favItem.getAppName() + "color = " + favItem.getColor());
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount " + favItems.size());
        return favItems.size();
    }

}
