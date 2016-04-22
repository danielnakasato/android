package com.supernova.android.renderersample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import com.supernova.android.renderersample.decorators.SpacesItemDecoration;
import com.supernova.android.renderersample.models.renderers.Banner;
import com.supernova.android.renderersample.models.renderers.Video;
import com.supernova.android.renderersample.R;
import com.supernova.android.renderersample.renderers.BannerRenderer;
import com.supernova.android.renderersample.renderers.VideoRenderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RVRendererAdapter<Video> mAdapter;
    ListAdapteeCollection mListAdapteeCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fab actions setup
        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video video = new Video();
                video.setTitle(String.valueOf(mListAdapteeCollection.size()));
                mAdapter.add(video);
                mAdapter.notifyItemInserted(mListAdapteeCollection.indexOf(video));
            }
        });
        findViewById(R.id.fab_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toBeRemovedIndex = getRandom(0, mListAdapteeCollection.size() - 1);
                mAdapter.notifyItemRemoved(toBeRemovedIndex);
                mAdapter.remove(mListAdapteeCollection.get(toBeRemovedIndex));
            }
        });

        // build mock data
        mListAdapteeCollection = new ListAdapteeCollection();
        for (int i = 0; i < 10; i++) {
            if (getRandom(0, 1) == 0) {
                Video video = new Video();
                video.setTitle(String.valueOf(i));
                mListAdapteeCollection.add(video);
            } else {
                Banner banner = new Banner();
                banner.setTitle("BANNER");
                mListAdapteeCollection.add(banner);
            }
        }

        // init renderer builder
        RendererBuilder rendererBuilder = new RendererBuilder()
                .bind(Video.class, new VideoRenderer())
                .bind(Banner.class, new BannerRenderer());

        // init recycler view
        SpacesItemDecoration decoration = new SpacesItemDecoration(10);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RVRendererAdapter(rendererBuilder,  mListAdapteeCollection);
        recyclerView.setAdapter(mAdapter);
    }

    private int getRandom(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }


}
