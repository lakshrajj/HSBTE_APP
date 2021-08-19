package com.lrs.hsbte;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class MyAdapter extends FirebaseRecyclerAdapter<model,MyAdapter.myviewholder>
{
    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model)
    {
        String newss=model.getQue().toString().replace("_n","\n");
        holder.que.setText(newss);


        //holder.que.setText(model.getQue());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView que;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            que=(TextView)itemView.findViewById(R.id.tvfirstName);
        }
    }
}
