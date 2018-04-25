package com.softnobug.guigedialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weixuemeng on 2018/4/24.
 */


public class goodsChooseDialog extends PopupWindow {

    private Context mcontext;
    private ArrayList<TextView> buttonArrayList;
    private LinearLayout ggLayout;
    private ArrayList<String> titleArr;


    public goodsChooseDialog(Context context) {

        mcontext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.goods_info_choose_layout, null);

        View backView = view.findViewById(R.id.backView);
        backView.setBackgroundColor(Color.BLACK);
        backView.setAlpha(.8f);
        view.setBackgroundColor(0x00000000);

        buttonArrayList = new ArrayList<TextView>();

        ggLayout = view.findViewById(R.id.guiGe);

        LinearLayout imgLayout = view.findViewById(R.id.shop_img_layout);
        ImageView img = view.findViewById(R.id.shop_img);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(dip2px(mcontext,5));
        drawable.setColor(Color.WHITE);
        drawable.setStroke(1,Color.parseColor("#e6e6e6"));
        imgLayout.setBackground(drawable);
        img.setBackground(drawable);

        view.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        this.setContentView(view);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);

        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(true);


        System.out.println("屏幕宽度:" + String.valueOf(getWindowWidth(context)));

        titleArr = new ArrayList<String>();

        titleArr.add("1零食");
        titleArr.add("2新鲜水果");
        titleArr.add("3生鲜");
        titleArr.add("4家电冰箱洗以及1");
        titleArr.add("5零食");
        titleArr.add("6家电冰箱洗以及2");
        titleArr.add("7零食");
        titleArr.add("8超长边框大个黑白电视");
        titleArr.add("9超长边框大个黑白电视");
        titleArr.add("10超长边框大个黑白电视");
        titleArr.add("11超长边框大个黑白电视");
        titleArr.add("12超长边框大个黑白电视");
        titleArr.add("13超长边框大个黑白电视");
        titleArr.add("14超长边框大个黑白电视");

        animotionAddBtn(titleArr);

        messureAndLoad(ggLayout);

        selectBtn(buttonArrayList.get(0));

    }


    public void messureAndLoad(LinearLayout layout) {

        int ScreanWidth = getWindowWidth(mcontext);
        int lineWidth = 20;

        LinearLayout layout1 = creatLinearLayout();

        for (int i = 0; i < buttonArrayList.size(); i++) {

            int buttonWidth = theBtnWidth(buttonArrayList.get(i));

            TextView button = buttonArrayList.get(i);

            System.out.println(i + "lineWidth:" + lineWidth);
            System.out.println(i + "buttonWidth:" + buttonWidth);

            if (lineWidth < ScreanWidth && lineWidth + buttonWidth + 60 < ScreanWidth) {

                layout1.addView(button);
                lineWidth = lineWidth + buttonWidth + 20;

            } else {

                ggLayout.addView(layout1);
                layout1 = creatLinearLayout();
                layout1.addView(button);
                lineWidth = buttonWidth;

            }

        }

    }

    //创建所有的Button
    public void animotionAddBtn(ArrayList mlist) {

        for (int i = 0; i < mlist.size(); i++) {

            TextView button = creatBtn(titleArr.get(i));
            buttonArrayList.add(button);
        }

    }


    public LinearLayout creatLinearLayout() {

        LinearLayout temp = new LinearLayout(mcontext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, 0);
        lp.setMargins(0, dip2px(mcontext, 5), 0, dip2px(mcontext, 10));
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        temp.setLayoutParams(lp);
        temp.setBackgroundColor(Color.WHITE);
        temp.setOrientation(LinearLayout.HORIZONTAL);
        return temp;
    }


    //创建button
    public TextView creatBtn(String title) {

        TextView button = new TextView(mcontext);
        button.setText(title);
        button.setTextSize(14);
        button.setTextColor(Color.BLACK);
        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button.setBackgroundResource(R.drawable.bg_item_order);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, dip2px(mcontext, 30));
        layoutParams.setMargins(dip2px(mcontext, 20), 0, 0, 0);
        button.setPadding(dip2px(mcontext, 10), 0, dip2px(mcontext, 10), 0);
        button.setGravity(Gravity.CENTER);
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBtn(v);
            }
        });

        return button;

    }


    public void selectBtn(View mtv) {

        for (TextView tv :
                buttonArrayList) {
            if (mtv.equals(tv)){
                tv.setTextColor(Color.parseColor("#f03749"));
            }else {
                tv.setTextColor(Color.parseColor("#000000"));
            }
        }

    }


    //获取button的宽度
    public int theBtnWidth(TextView button) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        button.measure(w, h);
        int width = button.getMeasuredWidth();
        return width;
    }

    //获取屏幕宽度
    public static int getWindowWidth(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point screenPoint = new Point();
        display.getSize(screenPoint);
        return screenPoint.x;
    }

    //获取屏幕宽度
    public static int getWindowHeight(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point screenPoint = new Point();
        display.getSize(screenPoint);
        return screenPoint.y;
    }

    //获取通知栏高度
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
