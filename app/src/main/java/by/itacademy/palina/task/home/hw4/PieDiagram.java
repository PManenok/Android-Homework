package by.itacademy.palina.task.home.hw4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PieDiagram extends View {
    private Paint arcPaint;
    private Integer[] array;
    private ArrayList<Integer> colors = new ArrayList<>();
    private final RectF oval = new RectF();

    public PieDiagram(Context context) {
        super(context);
        init();
    }

    public PieDiagram(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PieDiagram(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        colors.add(Color.YELLOW);
        colors.add(0xFFFF8C6F);
        colors.add(0xFFFFEF3F);
        colors.add(0xFFFF893B);
        colors.add(0xFFFFC800);
        colors.add(0xFFFFAE2D);
        colors.add(0xFFFF9500);
        colors.add(0xFFFFD467);
        colors.add(0xFFFF6F00);
        colors.add(0xFFFFEC82);
        colors.add(0xFFFFCDC0);
        colors.add(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = (int) (Math.min(getWidth() / 2, getHeight() / 2) / 1.4f);
        int cX = getWidth() / 2;
        int cY = getHeight() / 2;
        oval.set(cX - radius, cY - radius, cX + radius, cY + radius);
        if (array != null) {
            Integer sum = 0;
            for (Integer num : array) {
                sum += num;
            }
            float startAngle = 0.f;
            float sweepAngle;
            int colorInd = 0;
            for (Integer num : array) {
                arcPaint.setColor(colors.get(colorInd++));
                sweepAngle = 360.f * num / sum;
                canvas.drawArc(oval, startAngle, sweepAngle, true, arcPaint);
                startAngle += sweepAngle;
                if (colorInd >= colors.size()) colorInd = 1;
            }
        }
    }

    public void setIntArray(Integer[] array) {
        this.array = array;
        invalidate();
    }
}
