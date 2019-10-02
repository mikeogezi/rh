package com.example.reliancehmo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reliancehmo.models.Provider;

public class ProviderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context context;
    private Provider provider;

    public TextView nameTV;

    public TextView ratingTV;

    public TextView typeTV;

    public TextView addressTV;
    public TextView stateTV;

    public ImageView imageView;

    public CardView cardView;

//    public CardView cardView;
//    public TextView shortNameTV;
//    public CardView shortNameCV;

    public ProviderHolder (View view, Context context) {
        super(view);

        nameTV = view.findViewById(R.id.provider_name);
        ratingTV = view.findViewById(R.id.provider_rating);
        typeTV = view.findViewById(R.id.provider_type);
        addressTV = view.findViewById(R.id.provider_address);
        stateTV = view.findViewById(R.id.provider_state);

        imageView = view.findViewById(R.id.image_view);

        cardView = view.findViewById(R.id.card_view);

        this.context = context;
    }

    public void setProvider (Provider provider) {
        this.provider = provider;
    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(context, CoursesActivity.class);
//        intent.putExtra(Keys.DEPARTMENT_NAME_KEY, provider.getName());
//        context.startActivity(intent);
    }
}
