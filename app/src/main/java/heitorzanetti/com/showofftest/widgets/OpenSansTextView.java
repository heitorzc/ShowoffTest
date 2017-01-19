package heitorzanetti.com.showofftest.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;




/**
 * Created by heitorzc on 18/01/17.
 */
public class OpenSansTextView extends TextView {


    public OpenSansTextView(Context context) {
        super(context);
        setTypeFace(context);
    }

    public OpenSansTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeFace(context);
    }

    public OpenSansTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeFace(context);
    }


    private void setTypeFace(Context context){
        Typeface regular  = Typeface.createFromAsset(context.getAssets(), "fonts/opensans.ttf");
        this.setTypeface(regular);
    }


    public void setTextAnimated(String text){
        this.setText(text);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);

        this.setAnimation(fadeIn);
    }

}
