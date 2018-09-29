package by.itacademy.palina.task.classwork.cw1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private Paint circlePaint;
    private Paint rectanglePaint;
    private RectF rectF;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        circlePaint = new Paint();
        circlePaint.setColor(Color.GREEN);
        circlePaint.setAntiAlias(true);

        rectanglePaint = new Paint();
        rectanglePaint.setColor(Color.YELLOW);
        rectanglePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF = new RectF();
        rectF.left = getWidth() * 0.6f;//перевод dp в пиксели
        rectF.top = getHeight() * 0.8f;
        rectF.right = getWidth() - rectF.left;
        rectF.bottom = getHeight() - rectF.top;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth() / 2, getHeight() / 2) / 2, circlePaint);

        canvas.drawRect(rectF, rectanglePaint);
    }

    public void setColor(){
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
