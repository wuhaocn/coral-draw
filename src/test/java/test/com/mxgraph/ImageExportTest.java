package test.com.mxgraph;

import com.mxgraph.tool.io.mxCodec;
import com.mxgraph.tool.util.mxCellRenderer;
import com.mxgraph.tool.util.mxUtils;
import com.mxgraph.tool.util.mxXmlUtils;
import com.mxgraph.tool.view.mxGraph;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageExportTest
{

	public static void main(String[] args) throws IOException
	{
		String xml = "<mxGraphModel dx=\"946\" dy=\"561\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" math=\"0\" shadow=\"0\">\n" +
				"  <root>\n" +
				"    <mxCell id=\"0\"/>\n" +
				"    <mxCell id=\"1\" parent=\"0\"/>\n" +
				"    <mxCell id=\"9GduNLHQVJNXabTG7y4u-1\" value=\"\" style=\"rounded=0;whiteSpace=wrap;html=1;\" parent=\"1\" vertex=\"1\">\n" +
				"      <mxGeometry x=\"460\" y=\"220\" width=\"120\" height=\"60\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"    <mxCell id=\"9GduNLHQVJNXabTG7y4u-2\" value=\"\" style=\"rounded=0;whiteSpace=wrap;html=1;\" parent=\"1\" vertex=\"1\">\n" +
				"      <mxGeometry x=\"150\" y=\"190\" width=\"120\" height=\"60\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"    <mxCell id=\"9GduNLHQVJNXabTG7y4u-3\" value=\"\" style=\"rounded=0;whiteSpace=wrap;html=1;\" parent=\"1\" vertex=\"1\">\n" +
				"      <mxGeometry x=\"180\" y=\"360\" width=\"120\" height=\"60\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"  </root>\n" +
				"</mxGraphModel>\n";
		Document doc = mxXmlUtils.parseXml(xml);

		mxGraph graph = new mxGraph();
		mxCodec codec = new mxCodec(doc);
		codec.decode(doc.getDocumentElement(), graph.getModel());

		ImageIO.write(mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null), "png", new File("file.png"));
		
		System.out.println("Writing oldimageexport.png");
	}

}
