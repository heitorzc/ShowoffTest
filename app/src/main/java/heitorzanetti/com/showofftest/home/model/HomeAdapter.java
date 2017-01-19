package heitorzanetti.com.showofftest.home.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import heitorzanetti.com.showofftest.R;
import heitorzanetti.com.showofftest.home.utils.ApiUserMediaResponse.Data;
import heitorzanetti.com.showofftest.widgets.OpenSansTextView;

/**
 * Created by heitorzc on 18/01/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Data> items;


    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivPhoto)      ImageView ivPhoto;
        @Bind(R.id.tvLikes)      OpenSansTextView tvLikes;
        @Bind(R.id.tvCaption)    OpenSansTextView tvCapition;


        private ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }


    public HomeAdapter(Context context, ArrayList<Data> items) {
        this.context = context;
        this.items = items;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_media_item, parent, false);
        return new ViewHolder(v);

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder vHolder = (ViewHolder) holder;

        Data d = items.get(position);

        String likes     = String.valueOf(d.getLikes().getCount());
        String photo_url = d.getImages().getStandard_resolution().getUrl();
        String caption   = d.getCaption().getText();


        Picasso.with(context).load(photo_url).placeholder(R.drawable.photo_placeholder).into(vHolder.ivPhoto);

        vHolder.tvLikes.setText(likes);
        vHolder.tvCapition.setText(caption);

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
