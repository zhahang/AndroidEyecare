package com.wormhole.eyecare.view;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.Constant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.view.View;

public class WatchView extends View {

	private Paint paintCircle;
	private Paint paintLine;
	private Path path;
	
	private int centerX;
	private int centerY;
	private float cx;
	private float cy;
	private float radius = 20;
	private float angle;
	private float angleChange = 10;
	private boolean clear = false;
	

	public WatchView(Context context) {
		super(context);
		init();
	}

	private void init() {
		paintCircle = new Paint();
		paintCircle.setColor(getResources().getColor(R.color.green));
		paintCircle.setStyle(Paint.Style.FILL);
		paintCircle.setAntiAlias(true);

		paintLine = new Paint();
		paintLine.setColor(getResources().getColor(R.color.black));
		paintLine.setStyle(Paint.Style.STROKE);
		paintLine.setStrokeWidth(5);
		paintLine.setAntiAlias(true);
		
		path = new Path();
		
		centerX = Constant.SCREENWIDTH / 4;
		centerY = Constant.SCREENHEIGHT / 2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		canvas.drawCircle(cx, cy, 20f, paintCircle);
		
		if(!clear){
//			canvas.drawColor(Color.WHITE);
//			canvas.drawPath(path, paintLine);
		}
		super.onDraw(canvas);
	}

	public void playCount(int count) {
		if (count % 5 == 0) {
			cx = centerX;
			cy = centerY / 2;
		} else if (count % 5== 1) {
			cx = centerX;
			cy = centerY / 2 * 3;
		} else if (count % 5 == 2) {
			cx = centerX / 2;
			cy = centerY;
		} else if (count % 5 == 3) {
			cx = centerX / 2 * 3;
			cy = centerY;
		} else if (count == 14){
			cx = centerX;
			cy = centerY;
			path.moveTo(cx, cy);
		}
		postInvalidate();
	}

	public void playRotate(boolean isToBig) {
		if(isToBig){
			radius += 2;
			angle += angleChange;
			angleChange -= 0.025;
		}else{
			radius -= 2;
			angle -= angleChange;
			angleChange += 0.025;
		}
		cx = (float) (centerX - radius * Math.cos(angle * Math.PI / 180));
		cy = (float) (centerY - radius * Math.sin(angle * Math.PI / 180));
//		path.lineTo(cx, cy);
		postInvalidate();
	}
	
	public void setColor(int color){
		paintCircle.setColor(color);
	}
	
	public void clear(){
		clear = true;
		postInvalidate();
	}
}
