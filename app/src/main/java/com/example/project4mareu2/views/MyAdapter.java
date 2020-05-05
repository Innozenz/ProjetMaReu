package com.example.project4mareu2.views;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.project4mareu2.R;
import com.example.project4mareu2.di.DI;
import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;
import com.example.project4mareu2.services.MeetingApiService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private Meeting currentMeeting;
    private MeetingApiService mApiService = DI.getUserApiService();

    private List<Meeting> meetings;
    private List<Meeting> meetingsFiltered;

    public MyAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
        meetingsFiltered = new ArrayList<>(meetings);
    }


    @Override
    public int getItemCount() {
        return meetings.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        display(holder, meetings.get(position));
        deleteMeeting(holder, meetings.get(position), position);

        final StringBuffer sb = getStringBuffer();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle(currentMeeting.getMeetingSubject())
                        .setMessage(sb.toString() + "\n\n" + currentMeeting.getMeetingDate())
                        .show();
            }
        });
    }

    // Display the email adresses correctly

    private StringBuffer getStringBuffer() {
        final StringBuffer sb = new StringBuffer();
        for (Participants meetingParticipant : currentMeeting.getMeetingParticipants()) {
            sb.append(meetingParticipant.getParticipants());
            if (currentMeeting.getMeetingParticipants().size()-1 != currentMeeting.getMeetingParticipants().indexOf(meetingParticipant)) {
                sb.append(" - ");
            }
        }
        return sb;
    }

    // Display the meetings

    private void display(MyViewHolder holder, Meeting meeting) {
        currentMeeting = meeting;
        holder.name.setText(String.format("%s - %s - %s", meeting.getMeetingSubject(), meeting.getMeetingHour(), meeting.getMeetingLocation()));
        holder.description.setText(getStringBuffer());
    }

    // Delete the meetings

    private void deleteMeeting(final MyViewHolder holder, final Meeting meeting, final int position) {
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiService.deleteMeetings(meeting);
                meetings.clear();
                meetings.addAll(mApiService.getMeetings());
                notifyDataSetChanged();
            }
        });
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;
        private ImageButton deleteButton;

        MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.text_up));
            description = ((TextView) itemView.findViewById(R.id.text_down));
            deleteButton = (ImageButton) itemView.findViewById(R.id.item_list_delete_button);
        }


    }

    // Filter the meetings

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            meetingsFiltered = mApiService.getMeetings();
            List<Meeting> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(meetingsFiltered);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Meeting item : meetingsFiltered) {
                    if (item.getMeetingLocation().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    } if (item.getMeetingDate().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            meetings.clear();
            meetings.addAll((Collection<? extends Meeting>) results.values);
            notifyDataSetChanged();
        }
    };

//    public Filter getDateFilter() {
//        return dateFilter;
//    }
//
//    private Filter dateFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            meetingsFiltered = mApiService.getMeetings();
//            List<Meeting> filteredList = new ArrayList<>();
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(meetingsFiltered);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Meeting item2 : meetingsFiltered) {
//                    if (item2.getMeetingDate().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item2);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            meetings.clear();
//            meetings.addAll((Collection<? extends Meeting>) results.values);
//            notifyDataSetChanged();
//        }
//    };
}