package com.mxgraph.tool.shape;

import com.mxgraph.tool.canvas.mxGraphics2DCanvas;
import com.mxgraph.tool.util.mxPoint;
import com.mxgraph.tool.view.mxCellState;

public interface mxIMarker
{
	/**
	 * 
	 */
	mxPoint paintMarker(mxGraphics2DCanvas canvas, mxCellState state, String type,
			mxPoint pe, double nx, double ny, double size, boolean source);

}
