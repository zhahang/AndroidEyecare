package com.wormhole.eyecare.view;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.Constant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;

public class RelaxView extends View {

	private Paint paint;
	private Path path;
	private int centerX;
	private int centerY;
	private int side = 10;

	public RelaxView(Context context) {
		super(context);
		init();
	}

	private void init() {
		centerX = Constant.SCREENWIDTH / 4;
		centerY = Constant.SCREENHEIGHT / 2;

		paint = new Paint();
		paint.setColor(getResources().getColor(R.color.green));
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		// canvas.scale(1f + scale, 1f + scale, centerX, centerY);
	
		path = new Path();
		int h = (int) Math.sqrt(Math.pow(side, 2) - Math.pow(side / 2, 2));
//		Log.w("ischanged", "h = " + h);
		path.moveTo(centerX - side / 2, centerY + h);
		path.lineTo(centerX + side / 2, centerY + h);
		path.lineTo(centerX + side, centerY);
		path.lineTo(centerX + side / 2, centerY - h);
		path.lineTo(centerX - side / 2, centerY - h);
		path.lineTo(centerX - side, centerY);
		path.close();
		
		canvas.drawPath(path, paint);
		canvas.restore();
	}

	public void toBig() {
		this.side += 5;
		postInvalidate();
	}
	
	public void toSmall(){
		this.side -= 5;
		postInvalidate();
	}

	public void setColor(int color){
		paint.setColor(color);
		postInvalidate();
	}
}
