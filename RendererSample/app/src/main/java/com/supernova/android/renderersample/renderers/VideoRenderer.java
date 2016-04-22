package com.supernova.android.renderersample.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;
import com.supernova.android.renderersample.models.renderers.Video;
import com.supernova.android.renderersample.R;

/**
 * Created by Thiago on 4/21/2016.
 */
public class VideoRenderer extends Renderer<Video> {

    TextView mTextView;

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.video_renderer, parent, false);
        return inflatedView;
    }

    //If you don't use ButterKnife you have to implement these methods.

    /**
     * Maps all the view elements from the xml declaration to members of this renderer.
     */
    @Override protected void setUpView(View rootView) {
        mTextView = (TextView) rootView.findViewById(R.id.renderer_text_view);
    }

    /**
     * Insert external listeners in some widgets.
     */
    @Override protected void hookListeners(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
    }

    @Override
    public void render() {
        Video video = getContent();
        renderTitle(video);
    }

    private void renderTitle(Video video) {
        mTextView.setText(video.getTitle());
    }
}
