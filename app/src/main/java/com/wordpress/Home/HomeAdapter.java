package com.wordpress.Home;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wordpress.Article;
import com.wordpress.R;

import java.util.ArrayList;

/**
 * Created by wail babou on 2016-12-24.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Article> datasource = new ArrayList<>();
    Context context;
    ViewHolder viewHolder;
    private String[] bgColors;
    int pos;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public HomeAdapter(Context context, ArrayList<Article> datasource, int position) {
        this.context = context;
        this.datasource = datasource;
        pos = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.custom, parent, false);
            return new ViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = inflater.inflate(R.layout.loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (datasource.get(position) == null) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Typeface caviar = Typeface.createFromAsset(context.getAssets(),
                "CaviarDreams.ttf");
        Typeface stc = Typeface.createFromAsset(context.getAssets(),
                "stc.otf");

        if (holder instanceof ViewHolder) {
            ViewHolder mholder = (ViewHolder) holder;
            mholder.title.setText(datasource.get(position).getTitle());
            mholder.title.setTypeface(stc);
            mholder.date.setText(datasource.get(position).getDate().substring(0, 10));
            mholder.date.setTypeface(caviar);
            mholder.auther.setText(datasource.get(position).getAuther());
            mholder.auther.setTypeface(stc);
            Log.e("fab", pos + "");
            if (pos == 0)
                mholder.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.classColor1)));
            else if (pos == 1)
                mholder.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.classColor2)));
            else if (pos == 2)
                mholder.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.classColor3)));
            else if (pos == 3)
                mholder.fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.classColor4)));
            Glide.with(context)
                    .load(datasource.get(position).getImg_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .fitCenter()
                    .skipMemoryCache(true)
                    .into(mholder.logo);
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }


    @Override
    public int getItemCount() {
        if (datasource == null)
            return 0;
        else
            return datasource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView title, auther, date;
        FloatingActionButton fab;

        public ViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            auther = (TextView) itemView.findViewById(R.id.auther);
            date = (TextView) itemView.findViewById(R.id.date);
            fab = (FloatingActionButton) itemView.findViewById(R.id.fab);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    Intent ii = new Intent(context, OpenArticle.class);
                    ii.putExtra("id", datasource.get(position).getId());
                    ii.putExtra("content", datasource.get(position).getContent());
                    ii.putExtra("title", datasource.get(position).getTitle());
                    ii.putExtra("title", datasource.get(position).getTitle());
                    ii.putExtra("logo", datasource.get(position).getImg_url());
                    ii.putExtra("ArticleUrl", datasource.get(position).getArticle_url());
                    context.startActivity(ii);
                }
            });
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    shareTextUrl(position);
                }
            });
        }
        private void shareTextUrl(int position) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    context.getResources().getString(R.string.app_name)+":"+datasource.get(position).getArticle_url());
            context.startActivity(Intent.createChooser(shareIntent, "Share link using"));
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            progressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_ATOP);
        }
    }
}
