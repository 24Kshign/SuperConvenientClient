package com.jack.mc.cyg.superconvenientclient.cyg.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 *
 */
public final class CygViewUtil {

    private CygViewUtil() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static ViewStub findViewStub(View view, int id) { return (ViewStub) view.findViewById(id); }
    public static TextView findTextView(View view, int id) { return (TextView) view.findViewById(id); }
    public static Button findButton(View view, int id) { return (Button) view.findViewById(id); }
    public static ImageView findImageView(View view, int id) { return (ImageView) view.findViewById(id); }
    public static EditText findEditText(View view, int id) { return (EditText) view.findViewById(id); }
    public static ViewGroup findViewGroup(View view, int id) { return (ViewGroup) view.findViewById(id); }
    public static LinearLayout findLinearLayout(View view, int id) { return (LinearLayout) view.findViewById(id); }
    public static RelativeLayout findRelativeLayout(View view, int id) { return (RelativeLayout) view.findViewById(id); }
    public static FrameLayout findFrameLayout(View view, int id) { return (FrameLayout) view.findViewById(id); }
    public static ScrollView findScrollView(View view, int id) { return (ScrollView) view.findViewById(id); }
    public static ListView findListView(View view, int id) { return (ListView) view.findViewById(id); }
}
