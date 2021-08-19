package com.lrs.hsbte;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;



public class QueAdapter extends FirebaseRecyclerAdapter<models,QueAdapter.myviewholder>
{
String sem,sub;


    public QueAdapter(@NonNull FirebaseRecyclerOptions<models> options) {
        super(options);

    }
    public void getSubSem(String semm, String subb){
        sem=semm;
        sub=subb;

    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder,final int position, @NonNull models models)
    {
       /* String sem,sub;
        sub=holder.getsub.getText().toString();
        sem=holder.getsemsub.getText().toString();*/

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final DialogPlus dialogPlus=DialogPlus.newDialog(holder.edit.getContext())
                    .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                    .setExpanded(true,1200)
                    .create();

            View myview=dialogPlus.getHolderView();
            EditText que=myview.findViewById(R.id.ques);
            Button submit=myview.findViewById(R.id.usubmit);

            que.setText(models.getQue());

            dialogPlus.show();

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,Object> map=new HashMap<>();
                    map.put("que",que.getText().toString());

                    FirebaseDatabase.getInstance().getReference(sem).child(sub).child(getRef(position).getKey()).updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    dialogPlus.dismiss();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    dialogPlus.dismiss();
                                }
                            });
                }
            });
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.edit .getContext());
                builder.setTitle("Warning !");
                builder.setMessage("Do you want to Delete this Question ?");

                builder.setPositiveButton("Yes, Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference(sem).child(sub)
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });


        String newss=models.getQue().toString().replace("_n","\n");
        holder.quee.setText(newss);

        //holder.que.setText(model.getQue());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView quee,getsub,getsemsub;
        View mView;
        ImageView edit,delete;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            quee=(TextView)itemView.findViewById(R.id.queee);
            mView=itemView;
            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);

        }
    }

}
