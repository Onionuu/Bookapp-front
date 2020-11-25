package com.example.myapplication.ui.mine;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.myapplication.R;

/**
 * @ClassName: RoundImageView
 * @Description: 图像圆形化
 *  圆形ImageView，可设置最多两个宽度不同且颜色不同的圆形边框。
 *  设置颜色在xml布局文件中由自定义属性配置参数指定
 */
public class RoundImageView {
//    //边框的宽度
//    private int mBorderThickness = 0;
//    private Context mContext;
//    private int defaultColor = 0xFFFFFFFF;
//    // 如果只有其中一个有值，则只画一个圆形边框
//    private int mBorderOutsideColor = 0;
//    private int mBorderInsideColor = 0;
//    // 控件默认长、宽
//    private int defaultWidth = 0;
//    private int defaultHeight = 0;
//
//    public RoundImageView(Context context) {
//        super(context);
//        mContext = context;
//    }
//
//    public RoundImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        mContext = context;
//        setCustomAttributes(attrs);
//    }
//
//    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        mContext = context;
//        setCustomAttributes(attrs);
//    }
//
//    private void setCustomAttributes(AttributeSet attrs) {
//        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.roundedimageview);
//        mBorderThickness = a.getDimensionPixelSize(R.styleable.roundedimageview_border_thickness, 0);
//        mBorderOutsideColor = a.getColor(R.styleable.roundedimageview_border_outside_color,defaultColor);
//        mBorderInsideColor = a.getColor(R.styleable.roundedimageview_border_inside_color, defaultColor);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        Drawable drawable = getDrawable();
//        if (drawable == null) {
//            return;
//        }
//
//        if (getWidth() == 0 || getHeight() == 0) {
//            return;
//        }
//
//        this.measure(0, 0);
//
//        if (drawable.getClass() == NinePatchDrawable.class)
//            return;
//
//        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
//        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
//
//        if (defaultWidth == 0) {
//            defaultWidth = getWidth();
//        }
//
//        if (defaultHeight == 0) {
//            defaultHeight = getHeight();
//        }
//        int radius = 0;
//
//        if (mBorderInsideColor != defaultColor && mBorderOutsideColor != defaultColor) {// 定义画两个边框，分别为外圆边框和内圆边框
//            radius = (defaultWidth < defaultHeight ? defaultWidth: defaultHeight) / 2 - 2 * mBorderThickness;
//            // 画内圆
//            drawCircleBorder(canvas, radius + mBorderThickness / 2,mBorderInsideColor);
//            // 画外圆
//            drawCircleBorder(canvas, radius + mBorderThickness + mBorderThickness / 2, mBorderOutsideColor);
//        } else if (mBorderInsideColor != defaultColor && mBorderOutsideColor == defaultColor) {// 定义画一个边框
//            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2 - mBorderThickness;
//            drawCircleBorder(canvas, radius + mBorderThickness / 2, mBorderInsideColor);
//        } else if (mBorderInsideColor == defaultColor && mBorderOutsideColor != defaultColor) {// 定义画一个边框
//            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2 - mBorderThickness;
//            drawCircleBorder(canvas, radius + mBorderThickness / 2,mBorderOutsideColor);
//        } else {// 没有边框
//            radius = (defaultWidth < defaultHeight ? defaultWidth : defaultHeight) / 2;
//        }
//
//        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
//        canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius, defaultHeight / 2 - radius, null);
//    }
//
//    /**
//     *
//     * 获取裁剪后的圆形图片
//     * @param radius半径
//     */
//    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
//        Bitmap scaledSrcBmp;
//        int diameter = radius * 2;
//        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
//        int bmpWidth = bmp.getWidth();
//        int bmpHeight = bmp.getHeight();
//        int squareWidth = 0, squareHeight = 0;
//        int x = 0, y = 0;
//        Bitmap squareBitmap;
//        if (bmpHeight > bmpWidth) {// 高大于宽
//            squareWidth = squareHeight = bmpWidth;
//            x = 0;
//            y = (bmpHeight - bmpWidth) / 2;
//            // 截取正方形图片
//            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,squareHeight);
//        } else if (bmpHeight < bmpWidth) {// 宽大于高
//            squareWidth = squareHeight = bmpHeight;
//            x = (bmpWidth - bmpHeight) / 2;
//            y = 0;
//            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth, squareHeight);
//        } else {
//            squareBitmap = bmp;
//        }
//
//        if (squareBitmap.getWidth() != diameter || squareBitmap.getHeight() != diameter) {
//            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,diameter, true);
//        } else {
//            scaledSrcBmp = squareBitmap;
//        }
//
//        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),scaledSrcBmp.getHeight(),Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        Paint paint = new Paint();
//        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),scaledSrcBmp.getHeight());
//        paint.setAntiAlias(true);
//        paint.setFilterBitmap(true);
//        paint.setDither(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,scaledSrcBmp.getHeight() / 2,scaledSrcBmp.getWidth() / 2,paint);
//        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
//        bmp = null;
//        squareBitmap = null;
//        scaledSrcBmp = null;
//        return output;
//    }
//
//    /**
//     * 边缘画圆
//     */
//    private void drawCircleBorder(Canvas canvas, int radius, int color) {
//        Paint paint = new Paint();
//        /* 去锯齿 */
//        paint.setAntiAlias(true);
//        paint.setFilterBitmap(true);
//        paint.setDither(true);
//        paint.setColor(color);
//        /* 设置paint的　style　为STROKE：空心 */
//        paint.setStyle(Paint.Style.STROKE);
//        /* 设置paint的外框宽度 */
//        paint.setStrokeWidth(mBorderThickness);
//        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
//    }
}

//public class RoundImageView extends android.support.v7.widget.AppCompatImageView {
//    /**
//     * 圆形模式
//     */
//    private static final int MODE_CIRCLE = 1;
//    /**
//     * 普通模式
//     */
//    private static final int MODE_NONE = 0;
//    /**
//     * 圆角模式
//     */
//    private static final int MODE_ROUND = 2;
//    private Paint mPaint;
//    private int currMode = 0;
//    /**
//     * 圆角半径
//     */
//    private int currRound = dp2px(10);
//
//    public RoundImageView(Context context) {
//        super(context);
//        initViews();
//    }
//
//    public RoundImageView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        obtainStyledAttrs(context, attrs, defStyleAttr);
//        initViews();
//    }
//
//    private void obtainStyledAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
//        currMode = a.hasValue(R.styleable.RoundImageView_type) ? a.getInt(R.styleable.RoundImageView_type, MODE_NONE) : MODE_NONE;
//        currRound = a.hasValue(R.styleable.RoundImageView_radius) ? a.getDimensionPixelSize(R.styleable.RoundImageView_radius, currRound) : currRound;
//        a.recycle();
//    }
//
//    private void initViews() {
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        /**
//         * 当模式为圆形模式的时候，我们强制让宽高一致
//         */
//        if (currMode == MODE_CIRCLE) {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//            int result = Math.min(getMeasuredHeight(), getMeasuredWidth());
//            setMeasuredDimension(result, result);
//        } else {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        Drawable mDrawable = getDrawable();
//        Matrix mDrawMatrix = getImageMatrix();
//        if (mDrawable == null) {
//            return; // couldn't resolve the URI
//        }
//
//        if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
//            return;     // nothing to draw (empty bounds)
//        }
//
//        if (mDrawMatrix == null && getPaddingTop() == 0 && getPaddingLeft() == 0) {
//            mDrawable.draw(canvas);
//        } else {
//            final int saveCount = canvas.getSaveCount();
//            canvas.save();
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                if (getCropToPadding()) {
//                    final int scrollX = getScrollX();
//                    final int scrollY = getScrollY();
//                    canvas.clipRect(scrollX + getPaddingLeft(), scrollY + getPaddingTop(),
//                            scrollX + getRight() - getLeft() - getPaddingRight(),
//                            scrollY + getBottom() - getTop() - getPaddingBottom());
//                }
//            }
//            canvas.translate(getPaddingLeft(), getPaddingTop());
//            if (currMode == MODE_CIRCLE) {//当为圆形模式的时候
//                Bitmap bitmap = drawable2Bitmap(mDrawable);
//                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//                canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, mPaint);
//            } else if (currMode == MODE_ROUND) {//当为圆角模式的时候
//                Bitmap bitmap = drawable2Bitmap(mDrawable);
//                mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//                canvas.drawRoundRect(new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom()),
//                        currRound, currRound, mPaint);
//            } else {
//                if (mDrawMatrix != null) {
//                    canvas.concat(mDrawMatrix);
//                }
//                mDrawable.draw(canvas);
//            }
//            canvas.restoreToCount(saveCount);
//        }
//    }
//
//    /**
//     * drawable转换成bitmap
//     */
//    private Bitmap drawable2Bitmap(Drawable drawable) {
//        if (drawable == null) {
//            return null;
//        }
//        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        //根据传递的scaletype获取matrix对象，设置给bitmap
//        Matrix matrix = getImageMatrix();
//        if (matrix != null) {
//            canvas.concat(matrix);
//        }
//        drawable.draw(canvas);
//        return bitmap;
//    }
//
//    private int dp2px(float value) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
//    }
//}