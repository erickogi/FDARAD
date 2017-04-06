package com.erickogi14gmail.fdarad.hotels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickogi14gmail.fdarad.R;
//import com.erickogi14gmail.fdarad.dishes.Model;
import com.erickogi14gmail.fdarad.mPicasso.PicassoClient;

import java.util.ArrayList;

/**
 * Created by kimani kogi on 2/15/2017.
 */


    public class HotelModelAdapter extends RecyclerView.Adapter<HotelModelAdapter.MyViewHolder> {
        Context context;
        private ArrayList<HotelModel> modelList;

    public HotelModelAdapter(Context context){
        this.context = context;
       // this.listData = listData;
    }
    public interface OnItemClickListener {

        void onItemClick(HotelModel item);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView textName;
            private TextView textDescription;
            private TextView textHours;
            private TextView textId;
            private ImageView loadedImage;

            public MyViewHolder(View view) {
                super(view);
                textName = (TextView) view.findViewById(R.id.hotel_name);
                textDescription = (TextView) view.findViewById(R.id.hotel_description);
                textHours=(TextView)view.findViewById(R.id.hotel_hours) ;
                textId=(TextView)view.findViewById(R.id.hotel_id) ;
                loadedImage= (ImageView) view.findViewById(R.id.hotel_image);
               /* title = (TextView) view.findViewById(R.id.title);
                genre = (TextView) view.findViewById(R.id.genre);
                year = (TextView) view.findViewById(R.id.year);*/
            }
        }


        public HotelModelAdapter(ArrayList<HotelModel> modelList, Context context) {
            this.modelList = modelList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_hotel, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            HotelModel model = modelList.get(position);
            holder.textName.setText(model.getHotel_name());
            holder.textDescription.setText(model.getHotel_description());
            holder.textHours.setText(model.getHotel_hours());
            holder.textId.setText(String.valueOf(model.getHotel_id()));
            PicassoClient.LoadImage(context,model.getHotel_image(),
                    holder.loadedImage);
        }

        @Override
        public int getItemCount() {
            return modelList.size();
        }

    }



