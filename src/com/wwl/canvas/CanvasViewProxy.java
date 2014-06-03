/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.wwl.canvas;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.view.TiDrawableReference;

import com.wwl.canvas.CanvasView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;

// This proxy can be created by calling Canvas.createCanvasView({ })
@Kroll.proxy(creatableInModule = CanvasModule.class)
public class CanvasViewProxy extends TiViewProxy {

	private CanvasView view;

	// Constructor
	public CanvasViewProxy() {
		super();
	}

	public CanvasViewProxy(TiContext tiContext) {
		this();
	}

	@Override
	public CanvasView createView(Activity activity) {
		view = new CanvasView(this);
		return view;
	}


	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options) {
		super.handleCreationDict(options);
	}

	
	// METHODS
	@Kroll.method
	public void save() {
		view.cView.currentCanvas.save(Canvas.ALL_SAVE_FLAG);
	}
	
	@Kroll.method
	public void restore() {
		view.cView.currentCanvas.restore();
	}

	// PATH METHODS
	@Kroll.method
	public void addRect(float x, float y, float width, float height, @Kroll.argument(optional=true) int direction) {
		Path.Direction dir = Path.Direction.CW;
		if (direction == 0) {
			dir = Path.Direction.CCW;
		}

		view.cView.path.addRect(x, y, x + width, x + height, dir);
	}

	@Kroll.method
	public void arc(float x1, float y1, float x2, float y2, float sAngle, float eAngle) {
		RectF oval = new RectF(x1, y1, x2, y2);
		sAngle = sAngle - 90;
		if (sAngle < 0) {
			sAngle = sAngle + 360;
		}
		view.cView.path.addArc(oval, sAngle, eAngle);
	}

	@Kroll.method
	public void arcTo(float x1, float y1, float x2, float y2, float sAngle, float eAngle) {
		RectF rect = new RectF(x1, y1, x2, y2);
		sAngle = sAngle - 90;
		if (sAngle < 0) {
			sAngle = sAngle + 360;
		}
		view.cView.path.arcTo(rect, sAngle, eAngle);
	}

	@Kroll.method
	public void begin() {
		view.cView.resetPaths();
	}

	@Kroll.method
	public void beginPath() {
		view.cView.path = new Path();
	}

	@Kroll.method
	public void bezierCurveTo(float cp1x, float cp1y, float cp2x, float cp2y, float x, float y) {
		view.cView.path.cubicTo(cp1x, cp1y, cp2x, cp2y, x, y);
	}

	@Kroll.method
	public void clip() {
		view.cView.currentCanvas.clipPath(view.cView.path);
	}

	@Kroll.method
	public void closePath() {
		view.cView.path.close();
	}

	@Kroll.method
	public void fill() {
		view.cView.paint.setStyle(Paint.Style.FILL);
		view.drawPath();
	}

	@Kroll.method
	public void lineTo(float x, float y) {
		view.cView.path.lineTo(x, y);
	}

	@Kroll.method
	public void moveTo(float x, float y) {
		view.cView.path.moveTo(x, y);
	}

	@Kroll.method
	public void quadraticCurveTo(float cpx, float cpy, float x, float y) {
		view.cView.path.quadTo(cpx, cpy, x, y);
	}

	@Kroll.method
	public void reset() {
		view.cView.path.reset();
	}

	@Kroll.method
	public void stroke() {
		view.cView.paint.setStyle(Paint.Style.STROKE);
		view.drawPath();
	}

	@Kroll.method
	public void toggleInverseFillType() {
		view.cView.path.toggleInverseFillType();
	}


	// CANVAS METHODS
	@Kroll.method
	void clear() {
		Paint tmpPaint = new Paint();
		tmpPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		tmpPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		view.cView.currentCanvas.drawPaint(tmpPaint);
		view.drawShape();
	}
	
	@Kroll.method
	void clearRect(int x, int y, int width, int height) {

		Paint tmpPaint = new Paint();
		tmpPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		tmpPaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		tmpPaint.setStyle(Paint.Style.FILL);
		view.cView.currentCanvas.drawRect(x, y, x + width, y + height, tmpPaint);
		view.drawShape();
	}

	@Kroll.method
	void clearShadow() {
		view.cView.shadowRadius = 0;
		view.cView.shadowOffsetX = 0;
		view.cView.shadowOffsetY = 0;
		view.cView.shadowColor = 0;
		view.cView.paint.clearShadowLayer();
	}

	@Kroll.method
	public void drawArc(float x1, float y1, float x2, float y2, float sAngle, float eAngle) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		RectF oval = new RectF(x1, y1, x2, y2);
		sAngle = sAngle - 90;
		if (sAngle < 0) {
			sAngle = sAngle + 360;
		}
		view.cView.currentCanvas.drawArc(oval, sAngle, eAngle, false, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method 
	public void drawCircle(float cx, float cy, float radius) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		view.cView.currentCanvas.drawCircle(cx, cy, radius, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void drawImage(Object image, float x, float y, float width, float height) {
		
		TiDrawableReference ref = TiDrawableReference.fromObject(view.cView.proxy.getActivity(), image);
		Bitmap bitmap = ref.getBitmap();
		Paint paint = new Paint();

		Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		RectF dst = new RectF(x, y, x + width, y + height);

		view.cView.currentCanvas.drawBitmap(bitmap, src, dst, paint);
		view.drawShape();
	}

	@Kroll.method
	public void drawLine(float startX, float startY, float stopX, float stopY) {
		view.cView.currentCanvas.drawLine(startX, startY, stopX, stopY, view.cView.paint);
		view.drawShape();
	}
	
	@Kroll.method
	public void drawPoint(float x, float y) {
		view.cView.currentCanvas.drawPoint(x, y, view.cView.paint);
		view.drawShape();
	}

	@Kroll.method
	public void drawRect(float x, float y, float width, float height) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		view.cView.currentCanvas.drawRect(x, y, x + width, y + height, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void drawRoundRect(float x, float y, float width, float height, float rx, float ry) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		RectF rect = new RectF(x, y, x + width, y + height);
		view.cView.currentCanvas.drawRoundRect(rect, rx, ry, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void drawText(String text, float x, float y) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		view.cView.currentCanvas.drawText(text, x, y, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void strokeText(String text, float x, float y /*[, int maxWidth]*/) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		view.cView.currentCanvas.drawText(text, x, y, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void drawTextOnPath(String text, float hOffset, float vOffset) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		Path tmpPath = new Path(view.cView.path);
		view.cView.removeCurrentPath();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		view.cView.currentCanvas.drawTextOnPath(text, tmpPath, hOffset, vOffset, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void fillTextOnPath(String text, float hOffset, float vOffset) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		Path tmpPath = new Path(view.cView.path);
		view.cView.removeCurrentPath();
		view.cView.paint.setStyle(Paint.Style.FILL);
		view.cView.currentCanvas.drawTextOnPath(text, tmpPath, hOffset, vOffset, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	void fillRect(int x, int y, int width, int height) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.FILL);
		view.cView.currentCanvas.drawRect(x, y, x + width, y + height, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void fillText(String text, float x, float y) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.FILL);
		view.cView.currentCanvas.drawText(text, x, y, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	@Kroll.method
	public void strokeRect(int x, int y, int width, int height) {
		Paint.Style currentStyle = view.cView.paint.getStyle();
		view.cView.paint.setStyle(Paint.Style.STROKE);
		view.cView.currentCanvas.drawRect(x, y, x + width, y + height, view.cView.paint);
		view.drawShape();
		view.cView.paint.setStyle(currentStyle);
	}

	// Matrix operations
	@Kroll.method
	public void rotate(float angle) {
		view.cView.currentCanvas.rotate(angle);
	}

	@Kroll.method
	public void scale(float x, float y) {
		view.cView.currentCanvas.scale(x, y);
	}

	@Kroll.method
	public void translate(float x, float y) {
		view.cView.currentCanvas.translate(x, y);
	}


	// Getters and setters
	@Kroll.setProperty @Kroll.method
	public void setAntiAlias(boolean antiAlias) {
		view.cView.paint.setAntiAlias(antiAlias);
	}

	@Kroll.setProperty @Kroll.method
	public void setDither(boolean dither) {
		view.cView.paint.setDither(dither);
	}

	@Kroll.setProperty @Kroll.method
	public void setFillStyle(final String color) {
		view.cView.paint.setColor(TiConvert.toColor(color));
	}

	@Kroll.setProperty @Kroll.method
	public void setFillType(final String filltype) {

		if (filltype.equals("evenodd")) {
			view.cView.path.setFillType(Path.FillType.EVEN_ODD);
		}
		if (filltype.equals("inverseevenodd")) {
			view.cView.path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
		}
		if (filltype.equals("inversewinding")) {
			view.cView.path.setFillType(Path.FillType.INVERSE_WINDING);
		}
		if (filltype.equals("winding")) {
			view.cView.path.setFillType(Path.FillType.WINDING);
		}
	}

	@Kroll.setProperty @Kroll.method
	public void setAlpha(float alpha) {
		view.cView.paint.setAlpha(Math.round(alpha * 255));
	}

	@Kroll.setProperty @Kroll.method
	public void setGlobalAlpha(float alpha) {
		view.cView.paint.setAlpha(Math.round(alpha * 255));
	}

	@Kroll.setProperty @Kroll.method
	public void setLineCap(final String cap) {

		if (cap.equals("butt"))  {
			view.cView.paint.setStrokeCap(Paint.Cap.BUTT);
		}
		if (cap.equals("round")) {
			view.cView.paint.setStrokeCap(Paint.Cap.ROUND);
		}
		if (cap.equals("square")) {
			view.cView.paint.setStrokeCap(Paint.Cap.SQUARE);
		}
	}
	
	@Kroll.setProperty @Kroll.method
	public void setLineJoin(final String join) {

		if (join.equals("miter")) {
			view.cView.paint.setStrokeJoin(Paint.Join.MITER);
		}
		if (join.equals("bevel")) {
			view.cView.paint.setStrokeJoin(Paint.Join.BEVEL);
		}
		if (join.equals("round")) {
			view.cView.paint.setStrokeJoin(Paint.Join.ROUND);
		}
	}

	@Kroll.setProperty @Kroll.method
	public void setLineWidth(float width) {
		view.cView.paint.setStrokeWidth(width);
	}

	@Kroll.setProperty @Kroll.method
	public void setMiterLimit(float miter) {
		view.cView.paint.setStrokeMiter(miter);
	}

	@Kroll.setProperty @Kroll.method
	public void setStrikeThruText(boolean strike) {
		view.cView.paint.setStrikeThruText(strike);
	}

	@Kroll.setProperty @Kroll.method
	public void setTextAlign(final String align) {
		
		Paint.Align alignType = Paint.Align.LEFT;

		if (align.equals("start") || align.equals("left")) {
			alignType = Paint.Align.LEFT;
		}
		if (align.equals("end") || align.equals("right")) {
			alignType = Paint.Align.RIGHT;
		}
		if (align.equals("center")) {
			alignType = Paint.Align.CENTER;
		}
		
		view.cView.paint.setTextAlign(alignType);
	}

	@Kroll.setProperty @Kroll.method
	public void setTextScaleX(float scaleX) {
		view.cView.paint.setTextScaleX(scaleX);
	}

	@Kroll.setProperty @Kroll.method
	public void setTextSize(float size) {
		view.cView.paint.setTextSize(size);
	}
	
	@Kroll.setProperty @Kroll.method
	public void setTextSkewX(float skewX) {
		view.cView.paint.setTextSkewX(skewX);
	}
	
	@Kroll.setProperty @Kroll.method
	public void setTextStyle(final String style) {

		int viewStyle = Typeface.NORMAL;

		if (style.equals("normal")) {
			viewStyle = Typeface.NORMAL;
		}
		if (style.equals("bold")) {
			viewStyle = Typeface.BOLD;
		}
		if (style.equals("italic")) {
			viewStyle = Typeface.ITALIC;
		}
		if (style.equals("bolditalic" )) {
			viewStyle = Typeface.BOLD_ITALIC;
		}

		view.cView.textStyle = viewStyle;
	}

	@Kroll.setProperty @Kroll.method
	public void setTypeface(String typeface) {
		view.cView.paint.setTypeface(Typeface.create(typeface, view.cView.textStyle));
	}

	@Kroll.setProperty @Kroll.method
	public void setUnderlineText(boolean underline) {
		view.cView.paint.setUnderlineText(underline);
	}

	@Kroll.setProperty @Kroll.method
	public void setShadowBlur(float blur) {
		view.cView.shadowRadius = blur;
		view.cView.paint.setShadowLayer(view.cView.shadowRadius, view.cView.shadowOffsetX, view.cView.shadowOffsetY, view.cView.shadowColor);
	}

	@Kroll.setProperty @Kroll.method
	public void setShadowColor(final String color) {
		view.cView.shadowColor = TiConvert.toColor(color);
		view.cView.paint.setShadowLayer(view.cView.shadowRadius, view.cView.shadowOffsetX, view.cView.shadowOffsetY, view.cView.shadowColor);
	}

	@Kroll.setProperty @Kroll.method
	public void setShadowOffsetX(float offset) {
		view.cView.shadowOffsetX = offset;
		view.cView.paint.setShadowLayer(view.cView.shadowRadius, view.cView.shadowOffsetX, view.cView.shadowOffsetY, view.cView.shadowColor);
	}

	@Kroll.setProperty @Kroll.method
	public void setShadowOffsetY(float offset) {
		view.cView.shadowOffsetY = offset;
		view.cView.paint.setShadowLayer(view.cView.shadowRadius, view.cView.shadowOffsetX, view.cView.shadowOffsetY, view.cView.shadowColor);
	}

	@Kroll.setProperty @Kroll.method
	public void setStrokeStyle(final String color) {
		view.cView.paint.setColor(TiConvert.toColor(color));
	}

	@Kroll.setProperty @Kroll.method
	public void setStrokeWidth(float width) {
		view.cView.paint.setStrokeWidth(width);
	}

	@Kroll.getProperty @Kroll.method
	public CanvasViewProxy getContext() {
		return this;
	}
}
