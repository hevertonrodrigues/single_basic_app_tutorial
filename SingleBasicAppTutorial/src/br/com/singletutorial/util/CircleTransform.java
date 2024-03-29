package br.com.singletutorial.util;

import com.squareup.picasso.Transformation;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

/***
 * This class create a circle image view using CircleTransform from Picasso library.
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 */

public class CircleTransform implements Transformation {
	@Override
	public Bitmap transform(Bitmap source) {
		int size = Math.min(source.getWidth(), source.getHeight());
		float radius = size / 2f;

		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP,
				Shader.TileMode.CLAMP));

		Bitmap output = Bitmap.createBitmap(source.getWidth(),
				source.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		canvas.drawCircle(radius, radius, radius, paint);

		if (source != output) {
			source.recycle();
		}

		return output;
	}

	@Override
	public String key() {
		return "circle";
	}
}