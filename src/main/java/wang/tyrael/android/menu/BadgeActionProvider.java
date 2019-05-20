package wang.tyrael.android.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.view.ActionProvider;

import wang.tyrael.library.R;

/**
 * Created by 王超 on 2017/10/18.
 */

public class BadgeActionProvider extends ActionProvider {
    private Context context;


    private ImageView mIvIcon;
    private TextView mTvBadge;

    // 用来记录是哪个View的点击，这样外部可以用一个Listener接受多个menu的点击。
    private int clickWhat;
    private OnClickListener onClickListener;

    public BadgeActionProvider(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateActionView() {
        int size = 96;
//        int size = context.getResources().getDimensionPixelSize(
//                androidx.R.dimen.abc_action_bar_default_height_material);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(size, size);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.menu_badge_provider, null, false);

        view.setLayoutParams(layoutParams);
        mIvIcon = view.findViewById(R.id.iv_icon);
        mTvBadge = view.findViewById(R.id.tv_badge);
        view.setOnClickListener(onViewClickListener);
        return view;
    }

    // 点击处理。
    private View.OnClickListener onViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onClickListener != null)
                onClickListener.onClick(clickWhat);
        }
    };

    // 外部设置监听。
    public void setOnClickListener(int what, OnClickListener onClickListener) {
        this.clickWhat = what;
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int what);
    }

    // 设置图标。
    public void setIcon(@DrawableRes int icon) {
        mIvIcon.setImageResource(icon);
    }

    // 设置显示的数字。
    public void setBadge(int i) {
        mTvBadge.setText(Integer.toString(i));
    }

    // 设置文字。
    public void setTextInt(@StringRes int i) {
        mTvBadge.setText(i);
    }

    // 设置显示的文字。
    public void setText(CharSequence i) {
        mTvBadge.setText(i);
    }

}
