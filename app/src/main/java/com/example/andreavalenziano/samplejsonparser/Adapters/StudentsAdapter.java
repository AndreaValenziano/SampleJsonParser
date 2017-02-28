package com.example.andreavalenziano.samplejsonparser.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andreavalenziano.samplejsonparser.Model.Student;
import com.example.andreavalenziano.samplejsonparser.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by AndreaValenziano on 27/02/17.
 */
public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>  implements ItemTouchHelperAdapter{

    private ArrayList<Student> dataSet = new ArrayList<>();


    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        StudentViewHolder holder = new StudentViewHolder(v);


        return holder;
    }


    @Override
    public void onBindViewHolder(StudentsAdapter.StudentViewHolder holder, int position) {

        Student currentStudent = dataSet.get(position);

        holder.studentNameTv.setText(currentStudent.getName());
        holder.studentEmail.setText(currentStudent.getEmail());
       //new ImageDownloaderTask(holder.studentAvatar).execute(currentStudent.getAvatar());
        Glide
                .with(holder.itemView.getContext())
                .load(currentStudent.getAvatar())
                .placeholder(R.drawable.placeholder)
                .into(holder.studentAvatar);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setDataSet(ArrayList<Student> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView studentNameTv;
        public TextView studentEmail;
        public ImageView studentAvatar;
        public ImageButton studenGithub;


        public StudentViewHolder(final View v) {
            super(v);
            studentNameTv = (TextView) v.findViewById(R.id.student_name);
            studentEmail = (TextView) v.findViewById(R.id.student_email);
            studenGithub = (ImageButton) v.findViewById(R.id.student_github);
            studentAvatar= (ImageView) v.findViewById(R.id.student_image);


            studenGithub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(dataSet.get(getAdapterPosition()).getGithub()));
                    itemView.getContext().startActivity(i);


                }
            });
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(dataSet, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(dataSet, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);

    }
}

