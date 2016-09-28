package com.wormhole.eyecare.view;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.Constant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class AdjustView extends View {

	private Paint paint;
	private Path path;
	private int screenWidth;
	private int screenHeight;
	private int deltaY;

	public AdjustView(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setColor(getResources().getColor(R.color.darkgreen));
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);

		screenWidth = Constant.SCREENWIDTH / 2;
		screenHeight = Constant.SCREENHEIGHT;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();

		path = new Path();
		path.moveTo(10, deltaY);
		path.lineTo(screenWidth - 10 , deltaY);
		path.lineTo(screenWidth - 10, screenHeight/10 + deltaY);
		path.lineTo(10, screenHeight/10 + deltaY);
		path.close();
		canvas.drawPath(path, paint);

		canvas.restore();
	}
	
	public void playAnim(int deltaY){
		this.deltaY = deltaY;
		postInvalidate();
	}
	
	public void setColor(int color){
		paint.setColor(color);
	}
	
}
