package com.example.reliancehmo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.reliancehmo.models.Provider;

import java.util.List;
import java.util.Locale;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderHolder> {
    private List<Provider> providers;
    private Context context;

    public ProviderAdapter (Context context, List<Provider> providers) {
        this.context = context;
        this.providers = providers;
    }

    @Override
    public ProviderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_card, null);
        return new ProviderHolder(view, context);
    }

    @Override
    public void onBindViewHolder(final ProviderHolder holder, int position) {
        Provider provider = providers.get(position);

        holder.setProvider(provider);

        holder.nameTV.setText(provider.getName());
        holder.ratingTV.setText(String.format(Locale.getDefault(), "%.1f/5", provider.getRating()));

        holder.typeTV.setText(provider.getType());

        holder.cardView.setOnClickListener(holder);

        if (provider.hasLocation()) {
            holder.addressTV.setText(provider.getLocation().getAddress());
            holder.stateTV.setText(provider.getLocation().getState());
        }
        else {
            holder.addressTV.setText(provider.getAddress());
            holder.stateTV.setText(provider.getState());
        }
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }
}
