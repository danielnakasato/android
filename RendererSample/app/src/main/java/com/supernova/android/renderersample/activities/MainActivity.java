package com.supernova.android.renderersample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import java.util.Objects;
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
                int toBeInsertedIndex = getRandom(0, mListAdapteeCollection.size() - 1);
                Object objectToBeInserted;

                if (getRandom(0, 1) == 0) {
                    Video video = new Video();
                    video.setTitle(String.valueOf(mListAdapteeCollection.size()));
                    objectToBeInserted = video;
                } else {
                    Banner banner = new Banner();
                    banner.setTitle("BANNER");
                    objectToBeInserted = banner;
                }

                mListAdapteeCollection.add(toBeInsertedIndex, objectToBeInserted);
                mAdapter.notifyItemInserted(toBeInsertedIndex);
            }
        });
        findViewById(R.id.fab_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toBeRemovedIndex = getRandom(0, mListAdapteeCollection.size() - 1);

                mListAdapteeCollection.remove(toBeRemovedIndex);
                mAdapter.notifyItemRemoved(toBeRemovedIndex);
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
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int spanSize;

                if (mListAdapteeCollection.get(position).getClass() == Banner.class) {
                    spanSize = 2;
                } else {
                    spanSize = 1;
                }

                return spanSize;
            }
        });
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
