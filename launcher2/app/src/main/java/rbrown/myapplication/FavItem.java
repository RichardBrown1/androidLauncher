package rbrown.myapplication;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.palette.graphics.Palette;

public class FavItem {


    private String appName, pkgName;
    private ApplicationInfo appInfo;
    private PackageManager pkgManager;
    private int color;
    private Drawable appIcon;
    private Bitmap appIconBitmap;
    private Palette palette;


    public FavItem(String pkgName, PackageManager pkgManager) {
        this.pkgName = pkgName;
        this.pkgManager = pkgManager;

        Log.d("TAG", "FavItem Obj = " + pkgName);

        //appName
        try {
            appInfo =  pkgManager.getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
            appName = (String) pkgManager.getApplicationLabel(appInfo);
        } catch (final PackageManager.NameNotFoundException e) {
            appName = "Error";
        }

        //appColor
        if (appInfo != null) {
            appIcon = pkgManager.getApplicationIcon(appInfo);
            appIconBitmap = drawableToBitmap(appIcon);
            palette = Palette.from(appIconBitmap).generate();
            getPrimaryColorFromPalette(palette);
            /*new Palette.Builder(appIconBitmap).generate(new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette p) {
                    //use generated instance
                    Log.d("TAG","Palette Generated");
                    palette = p;
                    getPrimaryColorFromPalette(palette);
                }
            });*/

            Log.d("TAG", "Color generated " + color);
        } else {
            Log.d("TAG","App Info = null");
        }

    }

    public void getPrimaryColorFromPalette(Palette p) {
        if(p == null) {
            Log.d("TAG", "Palette is null");
            color = Color.WHITE;
            return;
        }

        color = p.getLightVibrantColor(Color.WHITE);
        if(color == Color.WHITE) color = p.getVibrantColor(Color.WHITE); else return;
        if(color == Color.WHITE) color = p.getDarkVibrantColor(Color.WHITE); else return;
        if(color == Color.WHITE) color = p.getLightMutedColor(Color.WHITE); else  return;
        if(color == Color.WHITE) color = p.getMutedColor(Color.WHITE); else return;
        color = Color.WHITE;
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public ApplicationInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(ApplicationInfo appInfo) {
        this.appInfo = appInfo;
    }

    public PackageManager getPkgManager() {
        return pkgManager;
    }

    public void setPkgManager(PackageManager pkgManager) {
        this.pkgManager = pkgManager;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public Palette getPalette() {
        return palette;
    }
}
