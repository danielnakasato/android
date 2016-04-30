package com.supernova.android.renderersample.renderers;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;
import com.supernova.android.renderersample.R;
import com.supernova.android.renderersample.models.renderers.Banner;

/**
 * Created by Thiago on 4/21/2016.
 */
public class BannerRenderer extends Renderer<Banner> {

    TextView mTextView;

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.banner_renderer, parent, false);
        return inflatedView;
    }

    //If you don't use ButterKnife you have to implement these methods.

    /**
     * Maps all the view elements from the xml declaration to members of this renderer.
     */
    @Override protected void setUpView(View rootView) {
        mTextView = (TextView) rootView.findViewById(R.id.banner_renderer_text_view);
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
        Banner banner = getContent();
        renderTitle(banner);
    }

    private void renderTitle(Banner banner) {
        mTextView.setText(banner.getTitle());
    }
}
