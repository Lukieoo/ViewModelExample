package com.anioncode.viewmodelexample;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.HeroViewHolder> {

    Context mCtx;
    List<ModelNews> heroList;

    public NewsAdapter(Context mCtx, List<ModelNews> heroList) {
        this.mCtx = mCtx;
        this.heroList = heroList;

    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_news, parent, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        ModelNews hero = heroList.get(position);

        holder.title.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        holder.title.setSelected(true);
        holder.title.setSingleLine(true);



        if (hero.getThumbnail() != null) {
            Picasso.get().load(hero.getThumbnail()).into(holder.imageView);
        } else {
            holder.imageView.setVisibility(View.GONE);
            holder.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        holder.title.setText(hero.getTitle());


        Date data =  fromISO8601UTC(hero.getDate());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm");

        if (data!=null){
            String strDate = dateFormat.format(data);
            holder.date.setText(strDate);

        }else {
            System.out.println(hero.getDate());
        }




    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
//        TextView description;
        TextView date;

        public HeroViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
//            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);


        }
    }
    public Date fromISO8601UTC(String dateStr) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        df.setTimeZone(tz);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}