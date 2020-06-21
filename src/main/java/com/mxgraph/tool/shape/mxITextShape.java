/**
 * Copyright (c) 2010, Gaudenz Alder, David Benson
 */
package com.mxgraph.tool.shape;

import com.mxgraph.tool.canvas.mxGraphics2DCanvas;
import com.mxgraph.tool.view.mxCellState;

import java.util.Map;

public interface mxITextShape
{
	/**
	 * 
	 */
	void paintShape(mxGraphics2DCanvas canvas, String text, mxCellState state,
			Map<String, Object> style);

}
