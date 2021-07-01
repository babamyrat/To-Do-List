package com.example.to_do_list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable{

    private ArrayList<ExampleItem> mExampleList;
    private ArrayList<ExampleItem> mExampleListFull; //full code

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTimeDate;
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        public ImageView mDeleteImage;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTimeDate = itemView.findViewById(R.id.timeDate);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
            mDeleteImage = itemView.findViewById(R.id.image_delete);
             //ItemClick
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
          // delete task
            mDeleteImage.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(position);
                }
            });
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        this.mExampleList = exampleList;   //this add
        mExampleListFull = new ArrayList<>(mExampleList); //add full code
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
        holder.mTimeDate.setText(currentItem.getTimeDate());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    @Override
    public Filter getFilter() {
        return mExampleFilter;
    }

    private Filter mExampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ExampleItem> filteredArrayList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredArrayList.addAll(mExampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ExampleItem item : mExampleListFull){
                    if (item.getLine1().toLowerCase().contains(filterPattern)){
                        filteredArrayList.add(item);

                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredArrayList;

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          mExampleList.clear();
          mExampleList.addAll((ArrayList) results.values);
          notifyDataSetChanged();
        }
    };
}