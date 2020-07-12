package com.example.terremotos;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class TerremotoLoader extends AsyncTaskLoader {

    private String mUrl;
    public TerremotoLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Object loadInBackground() {

        if (mUrl == null){
            return null;
        }

        List<Terremoto> terremotos = QueryUtils.traerDataDeTerremotos(mUrl);
        return terremotos;

    }
}
