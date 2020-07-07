//package rbrown.myapplication;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.ContextMenu;
//import android.view.MenuItem;
//import android.view.View;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public abstract class RecycleAdapter extends RecyclerView.Adapter<AdapterAllApps.ViewHolderAllApp> {
//    private ArrayList<FavItem> listAppNames = new ArrayList<>();
//    private ArrayList<MenuItem> ctxMenuOptions = new ArrayList<>();
//
//    public AdapterAllApps.ViewHolderAllApp
//
//    //Replace contents of a view (invoked by layout manager)
//    @Override
//    public void onBindViewHolder(AdapterAllApps.ViewHolderAllApp holder, int position) {
//        holder.textView.setText(listAppNames.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        Log.d("TAG", "getItemCount " + listAppNames.size());
//        return listAppNames.size();
//    }
//
//    @Override
//    public int getItemViewType(int position){
//        if(position == 0) {
//            return 1;
//        }
//        return 0;
//    }
//
//}
