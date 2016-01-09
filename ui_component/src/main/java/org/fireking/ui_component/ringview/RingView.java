package org.fireking.ui_component.ringview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import org.fireking.ui_component.R;

/**
 * Created by wangg on 16/1/9.
 */
public class RingView extends View {

    /**
     * 边框宽度
     */
    private int mRectWidth;
    private static int DEFAULT_RECT_WIDTH;
    /**
     * 边框颜色
     */
    private int mRectColor;
    private static int DEFAULT_RECT_COLOR;

    /**
     * 内部文字颜色
     */
    private int mTextColor;
    private static int DEFAULT_TEXT_COLOR;
    private String mText;

    /**
     * 圆环背景填充
     */
    private int mRingBgColor;
    private static int DEFAULT_RINGBG_COLOR;

    private int mInnerColor;
    private static int DEFAILT_INNER_COLOR;

    private Paint mRectPaint = null;
    private Paint mTextPaint = null;
    private Paint mInnerCirclePaint = null;
    private Paint mOutterCirclePaint = null;

    private RectF mRectRectF = null;

    private int mCenterWidth = 0;
    private int mCenterHeight = 0;

    private int duration = 2000;

    /**
     * 字体绘制开始点
     */
    private int mTextX = 0;
    private int mTextY = 0;

    private int offset = 0;
    private int progress = 0;

    private int startDegress = 0;
    private int endDegress = 360;

    private ValueAnimator animator;

    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 默认值初始化
         */
        DEFAULT_RECT_WIDTH = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics());
        DEFAULT_RECT_COLOR = Color.parseColor("#7ecbff");
        offset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics());
        DEFAULT_TEXT_COLOR = Color.WHITE;
        DEFAILT_INNER_COLOR = Color.parseColor("#2d384c");
        DEFAULT_RINGBG_COLOR = Color.parseColor("#364667");

        /**
         * 自定义属性
         */
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RingView);
            mRectWidth = (int) a.getDimension(R.styleable.RingView_r_rect_width, DEFAULT_RECT_WIDTH);
            mRectColor = a.getColor(R.styleable.RingView_r_rect_color, DEFAULT_RECT_COLOR);
            mTextColor = a.getColor(R.styleable.RingView_r_text_color, DEFAULT_TEXT_COLOR);
            mInnerColor = a.getColor(R.styleable.RingView_r_bg_color, DEFAILT_INNER_COLOR);
            mRingBgColor = a.getColor(R.styleable.RingView_r_ring_bg_color, DEFAULT_RINGBG_COLOR);
            duration = a.getInt(R.styleable.RingView_r_ring_duration, duration);
            a.recycle();
        }

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStrokeWidth(mRectWidth);
        mRectPaint.setColor(mRectColor);
        mRectPaint.setStrokeCap(Paint.Cap.ROUND);
        mRectPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setStyle(Paint.Style.FILL);
        mInnerCirclePaint.setColor(mInnerColor);

        mOutterCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutterCirclePaint.setStyle(Paint.Style.FILL);
        mOutterCirclePaint.setColor(mRingBgColor);
        mOutterCirclePaint.setStrokeWidth(mRectWidth);
        mOutterCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mOutterCirclePaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 设置显示内容
     *
     * @param num 比例
     */
    public void setText(final int num) {
        if (num > 100 || num < 0) {
            throw new IllegalArgumentException("num 值必须在0-100之间");
        }
        progress = num;

        animator = ValueAnimator.ofInt(0, progress);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mText = (int) animation.getAnimatedValue() + "%";
                endDegress = (int) Math.floor((int) animation.getAnimatedValue() * 3.6);
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterWidth = w / 2;
        mCenterHeight = h / 2;
        // 这里设置字体大小为组件大小的4分之一大小
        mTextPaint.setTextSize(mCenterWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆环背景
        RectF mRectF = new RectF(offset, offset, mCenterWidth * 2 - offset, mCenterHeight * 2 - offset);
        canvas.drawArc(mRectF, 0, 360, false, mOutterCirclePaint);

        //绘制内部圆
        canvas.drawCircle(mCenterWidth, mCenterHeight, mCenterWidth - offset * 2, mInnerCirclePaint);
        Rect rect = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), rect);
        mTextX = rect.width();
        mTextY = rect.height();

        //绘制文字
        if (!TextUtils.isEmpty(mText)) {
            canvas.drawText(mText, mCenterWidth - mTextX / 2, mCenterHeight + mTextY / 2, mTextPaint);
        }
        mRectRectF = new RectF(offset, offset, mCenterWidth * 2 - offset, mCenterHeight * 2 - offset);
        //绘制圆环
        canvas.drawArc(mRectRectF, startDegress, endDegress, false, mRectPaint);
    }
}
