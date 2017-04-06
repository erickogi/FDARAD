package com.erickogi14gmail.fdarad.dishes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickogi14gmail.fdarad.R;
import com.erickogi14gmail.fdarad.mPicasso.*;

import java.util.ArrayList;

/**
 * Created by kimani kogi on 2/15/2017.
 */


    public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.MyViewHolder> {
        Context context;
        private ArrayList<Model> modelList;

    public ModelAdapter(Context context){
        this.context = context;
       // this.listData = listData;
    }
    public interface OnItemClickListener {

        void onItemClick(Model item);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView textName;
            private TextView textDescription;
            private TextView textPrice;
            private TextView textId;
            private ImageView loadedImage;

            public MyViewHolder(View view) {
                super(view);
                textName = (TextView) view.findViewById(R.id.dish_name);
                textDescription = (TextView) view.findViewById(R.id.dish_description);
                textPrice=(TextView)view.findViewById(R.id.dish_price) ;
                textId=(TextView)view.findViewById(R.id.dish_id) ;
                loadedImage= (ImageView) view.findViewById(R.id.dish_image);
               /* title = (TextView) view.findViewById(R.id.title);
                genre = (TextView) view.findViewById(R.id.genre);
                year = (TextView) view.findViewById(R.id.year);*/
            }
        }


        public ModelAdapter(ArrayList<Model> modelList,Context context) {
            this.modelList = modelList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_dish, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Model model = modelList.get(position);
            holder.textName.setText(model.getDish_name());
            holder.textDescription.setText(model.getDish_description());
            holder.textPrice.setText(String.valueOf(model.getDish_price()));
            holder.textId.setText(String.valueOf(model.getDish_id()));
            PicassoClient.LoadImage(context,model.getDish_image(),
                    holder.loadedImage);
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

    }



