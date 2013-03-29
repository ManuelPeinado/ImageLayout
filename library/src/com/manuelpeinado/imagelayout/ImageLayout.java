package com.manuelpeinado.imagelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

/**
 * A layout that arranges its children in relation to a background image. The layout of each 
 * child is specified in image coordinates (pixels), and the conversion to screen coordinates 
 * is performed automatically. 
 * <p>The background image is adjusted so that it fills the available space.
 * <p>For some applications this might be a useful replacement for the now deprecated AbsoluteLayout.
 */
public class ImageLayout extends ViewGroup {
    private Bitmap bitmap;
    private Rect bitmapDestRect;
    private int imageWidth;
    private int imageHeight;
    private Rect bitmapSrcRect;
    private BitmapFitter fitter;


    public ImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        parseAttributes(attrs);
    }

    private void parseAttributes(AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ImageLayout);
        Drawable drawable = a.getDrawable(R.styleable.ImageLayout_image);
        if (drawable == null) {
            throw new RuntimeException("Invalid drawable resource in layout description file");
        }
        if (!(drawable instanceof BitmapDrawable)) {
            throw new RuntimeException("Drawable resource in layout description file must be of type \"BitmapDrawable\"");
        }
       
        bitmap = ((BitmapDrawable) drawable).getBitmap();
        bitmapSrcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        imageWidth = a.getInteger(R.styleable.ImageLayout_imageWidth, -1);
        imageHeight = a.getInteger(R.styleable.ImageLayout_imageHeight, -1);
        
        int fitMode = a.getInt(R.styleable.ImageLayout_fit, BitmapFitter.MODE_FIT_AUTO);
        fitter = new BitmapFitter(fitMode);
        
        int gravity = a.getInt(R.styleable.ImageLayout_android_gravity, -1);
        setGravity(gravity);
        a.recycle();
    }
    
    private void setGravity(int newValue) {
        if ((newValue & Gravity.HORIZONTAL_GRAVITY_MASK) == 0) {
            newValue |= Gravity.CENTER_HORIZONTAL;
        }
        if ((newValue & Gravity.VERTICAL_GRAVITY_MASK) == 0) {
            newValue |= Gravity.CENTER_VERTICAL;
        }
        fitter.setGravity(newValue);
    }

    private int transformWidthFromBitmapToView(int w) {
        float widthRatio = bitmapDestRect.width() / (float) imageWidth;
        return (int) (w * widthRatio);
    }

    private int transformHeightFromBitmapToView(int h) {
        float heightRatio = bitmapDestRect.height() / (float) imageHeight;
        return (int) (h * heightRatio);
    }

    private int transformXFromBitmapToView(int x) {
        float widthRatio = bitmapDestRect.width() / (float) imageWidth;
        return bitmapDestRect.left + (int) (x * widthRatio);
    }

    private int transformYFromBitmapToView(int y) {
        float heightRatio = bitmapDestRect.height() / (float) imageHeight;
        return bitmapDestRect.top + (int) (y * heightRatio);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, bitmapSrcRect, bitmapDestRect, null);
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int N = getChildCount();
        for (int i = 0; i < N; ++i) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            child.layout(layoutParams.transformedRect.left, layoutParams.transformedRect.top, 
                         layoutParams.transformedRect.right, layoutParams.transformedRect.bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentWidth, parentHeight);
        int effectiveWidth = parentWidth - getPaddingLeft() - getPaddingRight();
        int effectiveHeight = parentHeight - getPaddingTop() - getPaddingBottom();
        bitmapDestRect = fitter.fit(bitmap, effectiveWidth, effectiveHeight);
        adjustBitmapRectForPadding();

        int N = getChildCount();
        for (int i = 0; i < N; ++i) {
            View child = getChildAt(i);
            measureChild(child);
        }
    }

    private void measureChild(View child) {
        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        checkChildLayoutParams(layoutParams);
        int wspec = makeWidthSpec(layoutParams);
        int hspec = makeHeightSpec(layoutParams);
        child.measure(wspec, hspec);
        int left = 0;
        if (layoutParams.left != -1) {
            left = transformXFromBitmapToView(layoutParams.left);
        } 
        else if (layoutParams.centerX != -1){
            int cx = transformXFromBitmapToView(layoutParams.centerX);
            left = cx - child.getMeasuredWidth() / 2;
        }
        int top = 0;
        if (layoutParams.top != -1) {
            top = transformYFromBitmapToView(layoutParams.top);
        } 
        else if (layoutParams.centerY != -1){
            int cy = transformYFromBitmapToView(layoutParams.centerY);
            top = cy - child.getMeasuredHeight() / 2;
        }
        layoutParams.transformedRect.set(left, top, left + child.getMeasuredWidth(), 
                                         top + child.getMeasuredHeight());
    }

    private void adjustBitmapRectForPadding() {
        bitmapDestRect.left += getPaddingLeft();
        bitmapDestRect.right += getPaddingLeft();
        bitmapDestRect.top += getPaddingTop();
        bitmapDestRect.bottom += getPaddingTop();
    }

    private int makeHeightSpec(LayoutParams layoutParams) {
        int hspec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        if (layoutParams.maxHeight != -1) {
            int height = transformHeightFromBitmapToView(layoutParams.maxHeight);
            hspec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
        } else if (layoutParams.height != -1) {
            int height = transformHeightFromBitmapToView(layoutParams.height);
            hspec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        return hspec;
    }

    private int makeWidthSpec(LayoutParams layoutParams) {
        int wspec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        if (layoutParams.maxWidth != -1) {
            int maxWidth = transformWidthFromBitmapToView(layoutParams.maxWidth);
            wspec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST);
        } else if (layoutParams.width != -1) {
            int width = transformWidthFromBitmapToView(layoutParams.width);
            wspec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }
        return wspec;
    }

    private void checkChildLayoutParams(LayoutParams layoutParams) {
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        // In image coords
        int maxWidth = -1, maxHeight = -1;
        int width = -1, height = -1;
        int left = -1, top = -1, right = -1, bottom = -1;
        int centerX = -1, centerY = -1;
        // In view coords
        Rect transformedRect = new Rect();

        public LayoutParams(Context c, AttributeSet attrs) {
            // We don't call super(c, attrs) to prevent the layout_width and layout_height
            // attributes from being mandatory
            super(WRAP_CONTENT, WRAP_CONTENT);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.ImageLayout_Layout);

            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                case R.styleable.ImageLayout_Layout_layout_left:
                    left = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_top:
                    top = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_right:
                    right = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_bottom:
                    bottom = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_centerX:
                    centerX = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_centerY:
                    centerY = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_width:
                    width = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_maxWidth:
                    maxWidth = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_height:
                    height = a.getInt(attr, -1);
                    break;
                case R.styleable.ImageLayout_Layout_layout_maxHeight:
                    maxHeight = a.getInt(attr, -1);
                    break;
                }
            }
            a.recycle();
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        /**
         * {@inheritDoc}
         */
        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

}