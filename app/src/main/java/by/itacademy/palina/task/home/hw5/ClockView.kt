package by.itacademy.palina.task.home.hw5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import java.util.*


class ClockView : View {
    private var autoUpdate: Boolean = false
    private var radius: Float = 0f
    private var cX: Float = 0f
    private var cY: Float = 0f
    private var calendar: Calendar = Calendar.getInstance()
    private var circlePaint: Paint = Paint()
    private var linePaint: Paint = Paint()
    private var lineHandPaint: Paint = Paint()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {
        circlePaint.color = Color.DKGRAY
        circlePaint.isAntiAlias = true
        linePaint.color = Color.argb(255, 245, 0, 5)
        linePaint.strokeWidth = 5f
        linePaint.isAntiAlias = true
        lineHandPaint.color = Color.RED
        lineHandPaint.isAntiAlias = true
    }

    private fun countNums() {
        radius = Math.min(width / 2f, height / 2f) / 1.2f
        cX = width / 2f
        cY = height / 2f
        linePaint.textSize = Math.min(width / 2f, height / 2f) / 6f
    }

    private fun drawDashes(canvas: Canvas) {
        canvas.save()
        val step = 360 / (12 * 5f)
        val yStartLine = cY + radius
        val yEndLine = cY + radius / 1.15f
        val yEndLineLittle = cY + radius / 1.05f
        for (i in 0..12) {
            for (j in 0..3) {
                canvas.rotate(step, cX, cY)
                canvas.drawLine(cX, yStartLine, cX, yEndLineLittle, linePaint)
            }
            canvas.rotate(step, cX, cY)
            if (i != 2 && i != 5 && i != 8 && i != 11)
                canvas.drawLine(cX, yStartLine, cX, yEndLine, linePaint)
        }
        canvas.restore()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        countNums()
        canvas.drawCircle(cX, cY, radius, circlePaint)
        drawDashes(canvas)

        canvas.drawText("12", cX - linePaint.textSize / 1.7f, cY - radius * 0.8f, linePaint)
        canvas.drawText("6", cX - linePaint.textSize / 3f, cY + radius * 0.95f, linePaint)
        canvas.drawText("9", cX - radius * 0.95f, cY * 1.05f, linePaint)
        canvas.drawText("3", cX + radius * 0.8f, cY * 1.05f, linePaint)

        val minute = calendar.get(Calendar.MINUTE)
        val hourDegree = 360f / 12 * calendar.get(Calendar.HOUR) + 360f / 12 / 60 * minute
        val minuteDegree = 360f / 60 * minute
        val secondDegree = 360f / 60 * calendar.get(Calendar.SECOND)
        drawHand(canvas, hourDegree, 2f, 15f)
        drawHand(canvas, minuteDegree, 1.3f, 10f)
        drawHand(canvas, secondDegree, 1.15f, 3f)
    }

    private fun drawHand(canvas: Canvas, degree: Float, coefficient: Float, strokeWidth: Float) {
        val yEndLine = cY + radius / coefficient
        lineHandPaint.strokeWidth = strokeWidth
        canvas.save()
        canvas.rotate(180f + degree, cX, cY)
        canvas.drawLine(cX, cY, cX, yEndLine, lineHandPaint)
        canvas.restore()
    }

    fun setTime(calendar: Calendar) {
        this.calendar = calendar
        invalidate()
        if (autoUpdate) {
            Handler().postDelayed({ setTime(Calendar.getInstance()) }, 1000)
        }
    }

    fun setAutoUpdate(autoUpdate: Boolean) {
        this.autoUpdate = autoUpdate;
        setTime(Calendar.getInstance());
    }
}





