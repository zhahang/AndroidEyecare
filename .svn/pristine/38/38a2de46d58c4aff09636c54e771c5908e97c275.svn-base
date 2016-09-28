package com.wormhole.eyecare.view;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.Constant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class TrainView extends View {

	private Paint paintLeft;
	private Paint paintRight;

	private float cxLeft;
	private float cyLeft;
	private float cxRight;
	private float cyRight;
	private int screenWidth;
	private int screenHeight;

	public TrainView(Context context) {
		super(context);
		init();
	}

	private void init() {
		paintLeft = new Paint();
		paintLeft.setColor(getResources().getColor(R.color.green));
		paintLeft.setAntiAlias(true);
		paintLeft.setStyle(Paint.Style.FILL);

		paintRight = new Paint();
		paintRight.setColor(getResources().getColor(R.color.green));
		paintRight.setAntiAlias(true);
		paintRight.setStyle(Paint.Style.FILL);

		screenWidth = Constant.SCREENWIDTH /2;
		screenHeight = Constant.SCREENHEIGHT;

		cxLeft = (screenWidth / 10) * 4;
		cyLeft = screenHeight / 2;
		cxRight = (screenWidth / 10) * 6;
		cyRight = screenHeight / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawCircle(cxLeft, cyLeft, 20, paintLeft);
		canvas.drawCircle(cxRight, cyRight, 20, paintRight);
	}

	public void playDisperse(){
		cxLeft -= 3;
		cxRight += 3;
		postInvalidate();
	}

	public void playMerge() {
		cxLeft += 3;
		cxRight -= 3;
		postInvalidate();
	}
	
	public void setColor(int color){
		paintLeft.setColor(color);
		paintRight.setColor(color);
	}
}
