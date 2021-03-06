package com.lauzy.freedom.librarys.view.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lauzy.freedom.librarys.common.ScreenUtils;

/**
 * Desc : GridSpacingItemDecoration With Toolbar Height
 * Author : Lauzy
 * Date : 2017/8/7
 * Blog : http://www.jianshu.com/u/e76853f863a9
 * Email : freedompaladin@gmail.com
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private Builder mBuilder;

    public GridSpacingItemDecoration(Builder builder) {
        mBuilder = builder;
    }

    public static class Builder {
        private Context mContext;
        private int mSpanCount = 2;
        private int mSpace;
        private boolean includeEdge = true;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setSpanCount(int count) {
            mSpanCount = count;
            return this;
        }

        public Builder setSpace(int space) {
            mSpace = space;
            return this;
        }

        public Builder setIncludeEdge(boolean includeEdge) {
            this.includeEdge = includeEdge;
            return this;
        }

        public GridSpacingItemDecoration build(){
            return new GridSpacingItemDecoration(this);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = mBuilder.mSpanCount;
        int spacing = mBuilder.mSpace;
        boolean includeEdge = mBuilder.includeEdge;
        Context context = mBuilder.mContext;
        int column = position % spanCount;
        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount;
            outRect.right = (column + 1) * spacing / spanCount;
            if (position < spanCount) {
//                outRect.top = spacing;
                outRect.top = spacing + ScreenUtils.getStatusHeight(context)
                        + ScreenUtils.getActionBarHeight(context);
            }
            outRect.bottom = spacing;
        } else {
            outRect.left = column * spacing / spanCount;
            outRect.right = spacing - (column + 1) * spacing / spanCount;
            if (position >= spanCount) {
//                outRect.top = spacing;
                outRect.top = spacing + ScreenUtils.getStatusHeight(context)
                        + ScreenUtils.getActionBarHeight(context);
            }
        }
    }
}
