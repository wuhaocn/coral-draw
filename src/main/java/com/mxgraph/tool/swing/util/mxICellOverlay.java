package com.mxgraph.tool.swing.util;

import com.mxgraph.tool.util.mxRectangle;
import com.mxgraph.tool.view.mxCellState;

public interface mxICellOverlay
{

	/**
	 * 
	 */
	mxRectangle getBounds(mxCellState state);

}
