package test.com.mxgraph;

import com.mxgraph.tool.io.mxCodec;
import com.mxgraph.tool.util.mxCellRenderer;
import com.mxgraph.tool.util.mxUtils;
import com.mxgraph.tool.util.mxXmlUtils;
import com.mxgraph.tool.view.mxGraph;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OldImageExportTest
{

	public static void main(String[] args) throws IOException
	{
		System.out.println("Parsing graphmodel.xml");
		Document doc = mxXmlUtils.parseXml(mxUtils.readFile(mxImageExportTest.class.getResource("/com/mxgraph/test/graphmodel.xml")
				.getPath()));

		mxGraph graph = new mxGraph();
		mxCodec codec = new mxCodec(doc);
		codec.decode(doc.getDocumentElement(), graph.getModel());

		ImageIO.write(mxCellRenderer.createBufferedImage(graph, null, 1, null, true, null), "png", new File("oldimageexport.png"));
		
		System.out.println("Writing oldimageexport.png");
	}

}
