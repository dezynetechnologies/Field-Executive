package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dezyne 2 on 9/10/2016.
 */
public class PendingActivity extends Activity {

    DatabaseHandler db;
    ArrayList<Fields> stringArrayList;
    int count;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_layout);

        //db = new DatabaseHandler(this);
        //db.getContact(1);




         recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        ContentAdapter adapter = new ContentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView image;
        public TextView name;
        public TextView sex;
        public TextView age;
        public TextView address;
        public TextView csalary;
        public TextView saving;
        public TextView status;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.pending_layout, parent, false));
            image =(TextView)itemView.findViewById(R.id.imageview);
            name =(TextView)itemView.findViewById(R.id.nameview);
            sex =(TextView)itemView.findViewById(R.id.sexview);
            age =(TextView)itemView.findViewById(R.id.ageview);
            address =(TextView)itemView.findViewById(R.id.addressview);
            csalary =(TextView)itemView.findViewById(R.id.csalaryview);
            saving =(TextView)itemView.findViewById(R.id.savingview);
            status =(TextView)itemView.findViewById(R.id.statusview);


        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 24;
        private final String[] imagepath;
        private final String[] name;
        private final String[] sex;
        private final String[] age;
        private final String[] address;
        private final String[] csalary;
        private final String[] saving;
        private final String[] status;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            imagepath = resources.getStringArray(R.array.imagepath);
            name = resources.getStringArray(R.array.name);
            sex = resources.getStringArray(R.array.sex);
            age = resources.getStringArray(R.array.age);
            address = resources.getStringArray(R.array.address);
            csalary = resources.getStringArray(R.array.current_salary);
            saving = resources.getStringArray(R.array.saving_target);
            status = resources.getStringArray(R.array.status);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setText(imagepath[position % imagepath.length]);
            holder.name.setText(name[position % name.length]);
            holder.sex.setText(sex[position % sex.length]);
            holder.age.setText(age[position % age.length]);
            holder.address.setText(address[position % address.length]);
            holder.csalary.setText(csalary[position % csalary.length]);
            holder.saving.setText(saving[position % saving.length]);
            holder.status.setText(status[position % status.length]);
        }
        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
