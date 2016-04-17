package com.supernova.android.floatlabeledittext;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Thiago on 17/04/2016.
 */
public class FloatLabelEditText extends TextInputLayout {

    TextInputEditText mEditText;

    public FloatLabelEditText(Context context) {
        super(context);
        init();
    }

    public FloatLabelEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatLabelEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // region init method

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.float_label_edit_text, this, true);

        mEditText = (TextInputEditText) view.findViewById(R.id.text_input_edit_text);
    }

    //endregion

    // region public methods

    public void showError(String errorText) {
        setErrorEnabled(true);
        setError(errorText);
    }

    public void hideError() {
        setErrorEnabled(false);
        setError(null);
    }

    //endregion
}
