package com.example.agrofarmers.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agrofarmers.R;

import java.util.ArrayList;

import com.example.agrofarmers.model.Request;

/**
 * Created by Larry on 6/16/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Request> requests = new ArrayList();
    private Context context;
    private ClickListener clickListener;

    public RecyclerAdapter(Context context, ArrayList<Request> requests)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.requests = requests;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.content_request, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Request request = requests.get(position);
        holder.user_id.setText(String.valueOf(request.getRequest_id()));
        holder.product_weight.setText(String.valueOf(request.getWeight() + "Kg of " + request.getProduct()));
        holder.transport_type.setText(String.valueOf(request.getTransport_type()));
        holder.status.setText(String.valueOf((request.getStatus())));

    }

    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount()
    {
        return requests.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView user_id;
        TextView product_weight;
        TextView transport_type;
        TextView status;

        public MyViewHolder(View itemView) {
            super(itemView);
            user_id = (TextView)itemView.findViewById(R.id.user_id);
            product_weight = (TextView)itemView.findViewById(R.id.product_weight);
            transport_type = (TextView)itemView.findViewById(R.id.trans_type);
            status = (TextView)itemView.findViewById(R.id.status);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null)
            {
                clickListener.itemClicked(view, this.getLayoutPosition());

            }
        }
    }

    public interface ClickListener
    {
        void itemClicked(View view, int position);
    }
}
