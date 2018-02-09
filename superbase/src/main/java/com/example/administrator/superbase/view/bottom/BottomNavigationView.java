package com.example.administrator.superbase.view.bottom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.superbase.R;

import java.util.List;

/**
 * Created by v_chicunxiang on 2018/2/7.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/2/7 17:58
 */

public class BottomNavigationView extends LinearLayout {

    private int mTab_xmlRes;
    private View backgroundOverlay;
    private ViewGroup outerContainer;
    private ViewGroup tabContainer;
    private View shadowView;

    public BottomNavigationView(@NonNull Context context) {
        this(context,null);
    }

    public BottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);

    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        // 获取自定义参数
        populateAttributes(context, attrs, defStyleAttr);
        // 初始化布局界面
        initializeViews();
        setBackgroundColor(Color.TRANSPARENT);

        if (mTab_xmlRes != 0) {
            setItems(mTab_xmlRes);
        }

    }

    private void setItems(int tab_xmlRes) {
        if (tab_xmlRes == 0) {
            throw new RuntimeException("No items specified for the BottomBar!");
        }

        TabParser parser = new TabParser(getContext(), tab_xmlRes);
        updateItems(parser.parseTabs());

    }

    private void updateItems(List<BottomTab> tabs) {


    }

    private void initializeViews() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setOrientation(VERTICAL);

        View rootView = inflate(getContext(),
                R.layout.view_bottom_item_container, this);
        rootView.setLayoutParams(params);

        backgroundOverlay = rootView.findViewById(R.id.bottom_bar_background_overlay);
        outerContainer = (ViewGroup) rootView.findViewById(R.id.bottom_bar_outer_container);
        tabContainer = (ViewGroup) rootView.findViewById(R.id.bottom_bar_item_container);
        shadowView = findViewById(R.id.bottom_bar_shadow);

    }

    private void populateAttributes(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationBar, defStyleAttr, 0);
        mTab_xmlRes = typedArray.getResourceId(R.styleable.BottomNavigationBar_tab_xmlRes, 0);

        typedArray.recycle();
    }
}
