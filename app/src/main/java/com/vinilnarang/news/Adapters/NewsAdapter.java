package com.vinilnarang.news.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinilnarang.news.Models.Article;
import com.vinilnarang.news.R;


import java.util.List;

/**
 * Created by vinilnarang on 2/25/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<Article> articles;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView, infoImageView;
        public TextView sourceTextView, authorTextView, contentTextView, detailTextView;
        public View newsItemView;

        public ViewHolder(View itemView) {
            super(itemView);
            newsItemView = (View) itemView.findViewById(R.id.news_item_view);
            imageView = (ImageView) itemView.findViewById(R.id.news_item_icon);
            sourceTextView = (TextView) itemView.findViewById(R.id.news_item_source);
            authorTextView = (TextView) itemView.findViewById(R.id.news_item_author);
            contentTextView = (TextView) itemView.findViewById(R.id.news_item_content);
            infoImageView = (ImageView) itemView.findViewById(R.id.jump_to);
            detailTextView = (TextView) itemView.findViewById(R.id.news_item_detail);
        }
    }

    public NewsAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newsItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, parent,false);
        return new ViewHolder(newsItemView);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.ViewHolder holder, int position) {
        final Article article = articles.get(position);
        String source = article.getSourceName()==null?"N/A":article.getSourceName();
        String author = article.getAuthor()==null?"N/A":article.getAuthor();
        String title = article.getTitle()==null?"N/A":article.getTitle();
        String detail = article.getDescription()==null?"N/A":article.getDescription();
        holder.sourceTextView.setText("Source : " + source);
        holder.authorTextView.setText("Author : " + author);
        holder.contentTextView.setText(title);
        holder.detailTextView.setText(detail);
        Picasso.with(context)
                .load(article.getUrlToImage())
                .fit()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);

        holder.infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                context.startActivity(intent);
            }
        });

        holder.newsItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.detailTextView.getVisibility()==View.GONE){
                    holder.detailTextView.setVisibility(View.VISIBLE);
                }else{
                    holder.detailTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
