package com.example.lessonsscheduler.view.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lessonsscheduler.R;
import com.example.lessonsscheduler.model.Student;

import java.util.List;

public class SelectStudentRecyclerViewAdapter extends RecyclerView.Adapter<SelectStudentRecyclerViewAdapter.ViewHolder> {

    private final List<Student> students;

    public SelectStudentRecyclerViewAdapter(List<Student> items) {
        students = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_select_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.student = students.get(position);
        holder.idView.setText(position);
        holder.contentView.setText(students.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView idView;
        public final TextView contentView;
        public Student student;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            idView = (TextView) view.findViewById(R.id.item_number);
            contentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}