package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ApprovedActivity extends AppCompatActivity {

    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approve_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view3);
        ContentAdapter adapter = new ContentAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);



    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView submitdate;


        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {


            super(inflater.inflate(R.layout.testlayout1, parent, false));
            image =(ImageView)itemView.findViewById(R.id.imageview);
            name =(TextView)itemView.findViewById(R.id.nameview);
            submitdate =(TextView)itemView.findViewById(R.id.submitdate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailApprovedActivity.class);
                    intent.putExtra(DetailPendingActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });

        }
    }


    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 10;
        private final Drawable[] imagepath;
        private final String[] name;
        private final String[] submitdate;


        public ContentAdapter(Context context)
        {





            Resources resources = context.getResources();
            TypedArray a = resources.obtainTypedArray(R.array.imagepath);
            imagepath = new Drawable[a.length()];
            for (int i = 0; i < imagepath.length; i++) {
                imagepath[i] = a.getDrawable(i);
            }
            a.recycle();
            name = resources.getStringArray(R.array.name);
            submitdate = resources.getStringArray(R.array.submitdate);


        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setImageDrawable(imagepath[position % imagepath.length]);
            holder.name.setText(name[position % name.length]);
            holder.submitdate.setText(submitdate[position % submitdate.length]);
        }
        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}
