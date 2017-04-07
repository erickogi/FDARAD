package com.erickogi14gmail.fdarad.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.erickogi14gmail.fdarad.R;
import com.erickogi14gmail.fdarad.dishes.Model;
import com.erickogi14gmail.fdarad.mPicasso.PicassoClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimani kogi on 2/15/2017.
 */


    public class ModelAdapterCart extends RecyclerView.Adapter<ModelAdapterCart.MyViewHolder> {
        Context context;
        private List<ModelCart> modelList;

    public ModelAdapterCart(Context context){
        this.context = context;
       // this.listData = listData;
    }
    public interface OnItemClickListener {

        void onItemClick(ModelCart item);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView textName;
            private TextView textDescription;
            private TextView textPrice;
            private TextView textId;
            private ImageView loadedImage;
            private TextView  quantity;

            public MyViewHolder(View view) {
                super(view);
                textName = (TextView) view.findViewById(R.id.dish_name);
                textDescription = (TextView) view.findViewById(R.id.dish_description);
                textPrice=(TextView)view.findViewById(R.id.dish_total_price) ;
             //   textId=(TextView)view.findViewById(R.id.dish_id) ;
               // loadedImage= (ImageView) view.findViewById(R.id.dish_image);
                quantity=(TextView) view.findViewById(R.id.dish_quantity) ;
                //ratingBar=(RatingBar) view.findViewById(R.id.dish_rating);
               /* title = (TextView) view.findViewById(R.id.title);
                genre = (TextView) view.findViewById(R.id.genre);
                year = (TextView) view.findViewById(R.id.year);*/
            }
        }


        public ModelAdapterCart(List<ModelCart> modelList, Context context) {
            this.modelList = modelList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cart_list_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ModelCart model = modelList.get(position);
            holder.textName.setText(model.getDish_name());
            holder.textDescription.setText(model.getDish_description());
            holder.textPrice.setText(String.valueOf(model.getDish_price())+": Ksh ");
          //  holder.textId.setText(String.valueOf(model.getDish_id()));
           // holder.ratingBar.setProgress(model.getDish_ratings());
            holder.quantity.setText(String.valueOf(model.getDish_quantity()));
           // PicassoClient.LoadImage(context,model.getDish_image(),
           //         holder.loadedImage);
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

    }



