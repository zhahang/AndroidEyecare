package com.wormhole.eyecare.view;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.Constant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class BlinkView extends View {

	private Paint paintCircle;
	private Paint paintOval;
	private RectF rectFOval;
	private RectF rectFCircle;
	
	private int centerX;
	private int centerY;
	private float height = 100f;
	

	public BlinkView(Context context) {
		super(context);
		init();
	}

	private void init() {
		centerX = Constant.SCREENWIDTH / 4;
		centerY = Constant.SCREENHEIGHT / 2;

		paintCircle = new Paint();
		paintCircle.setColor(getResources().getColor(R.color.black));
		paintCircle.setStyle(Paint.Style.FILL);
		paintCircle.setAntiAlias(true);
		paintCircle.setStrokeWidth(5);

		paintOval = new Paint();
		paintOval.setColor(getResources().getColor(R.color.black));
		paintOval.setStyle(Paint.Style.STROKE);
		paintOval.setAntiAlias(true);
		paintOval.setStrokeWidth(10);
		
		rectFOval = new RectF(centerX - 200, centerY - 100, centerX + 200, centerY + 100);
		rectFCircle = new RectF(centerX - 100, centerY - 100, centerX + 100, centerY + 100);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		rectFCircle.top = centerY - height;
		rectFCircle.bottom = centerY + height;
		
		rectFOval.top = centerY - height;
		rectFOval.bottom = centerY + height;
		
//		if(rectFCircle.top < rectFOval.top){
//			rectFCircle.top = rectFOval.top;
//			rectFCircle.bottom = rectFOval.bottom;
//		}
		canvas.drawOval(rectFOval, paintOval);
		canvas.drawOval(rectFCircle, paintCircle);
	}

	public void playEyeOpen() {
		height += 5; 
		postInvalidate();
	}

	public void playEyeClose(){
		height -= 5;
		postInvalidate();
	}
}
