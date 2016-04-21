package com.supernova.android.renderersample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import com.supernova.android.renderersample.Model.Video;
import com.supernova.android.renderersample.R;
import com.supernova.android.renderersample.Renderers.SampleRenderer;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    RVRendererAdapter<Video> mAdapter;
    ArrayList<Video> mSourceData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fab actions setup
        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video video = new Video();
                video.setTitle(String.valueOf(mSourceData.size()));
                mAdapter.add(video);
            }
        });
        findViewById(R.id.fab_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.remove(mSourceData.get(0));
            }
        });

        // build mock data
        mSourceData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Video video = new Video();
            video.setTitle(String.valueOf(i));
            mSourceData.add(video);
        }
        AdapteeCollection<Video> adapteeCollection = new AdapteeCollection<Video>() {
            @Override
            public int size() {
                return mSourceData.size();
            }

            @Override
            public Video get(int index) {
                return mSourceData.get(index);
            }

            @Override
            public boolean add(Video element) {
                mSourceData.add(1, element);
                mAdapter.notifyItemInserted(1);
                return true;
            }

            @Override
            public boolean remove(Object element) {
                mSourceData.remove(element);
                mAdapter.notifyItemRemoved(mSourceData.indexOf(element));
                return true;
            }

            @Override
            public boolean addAll(Collection<? extends Video> elements) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> elements) {
                return false;
            }

            @Override
            public void clear() {

            }
        };

        // init renderer builder
        Renderer<Video> renderer = new SampleRenderer();
        RendererBuilder<Video> rendererBuilder = new RendererBuilder<>(renderer);

        // init recycler view
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RVRendererAdapter<>(rendererBuilder,  adapteeCollection);
        recyclerView.setAdapter(mAdapter);
    }


}
