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
		String xml = "<mxGraphModel dx=\"946\" dy=\"512\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"827\" pageHeight=\"1169\" math=\"0\" shadow=\"0\">\n" +
				"  <root>\n" +
				"    <mxCell id=\"0\"/>\n" +
				"    <mxCell id=\"1\" parent=\"0\"/>\n" +
				"    <mxCell id=\"IYtZMzpI9PyeZRInsfsU-1\" value=\"\" style=\"shape=process;whiteSpace=wrap;html=1;backgroundOutline=1;\" vertex=\"1\" parent=\"1\">\n" +
				"      <mxGeometry x=\"30\" y=\"240\" width=\"120\" height=\"60\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"    <mxCell id=\"IYtZMzpI9PyeZRInsfsU-2\" value=\"\" style=\"shape=trapezoid;perimeter=trapezoidPerimeter;whiteSpace=wrap;html=1;\" vertex=\"1\" parent=\"1\">\n" +
				"      <mxGeometry x=\"100\" y=\"160\" width=\"120\" height=\"60\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"    <mxCell id=\"IYtZMzpI9PyeZRInsfsU-3\" value=\"\" style=\"rounded=1;whiteSpace=wrap;html=1;\" vertex=\"1\" parent=\"1\">\n" +
				"      <mxGeometry x=\"240\" y=\"240\" width=\"120\" height=\"60\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"    <mxCell id=\"IYtZMzpI9PyeZRInsfsU-4\" value=\"\" style=\"shape=step;perimeter=stepPerimeter;whiteSpace=wrap;html=1;fixedSize=1;\" vertex=\"1\" parent=\"1\">\n" +
				"      <mxGeometry x=\"160\" y=\"340\" width=\"120\" height=\"80\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"    <mxCell id=\"IYtZMzpI9PyeZRInsfsU-5\" value=\"\" style=\"shape=mxgraph.bpmn.ad_hoc;fillColor=#000000;html=1;outlineConnect=0;\" vertex=\"1\" parent=\"1\">\n" +
				"      <mxGeometry x=\"346\" y=\"200\" width=\"14\" height=\"14\" as=\"geometry\"/>\n" +
				"    </mxCell>\n" +
				"    <mxCell id=\"IYtZMzpI9PyeZRInsfsU-6\" value=\"\" style=\"aspect=fixed;perimeter=ellipsePerimeter;html=1;align=center;fontSize=12;verticalAlign=top;fontColor=#364149;shadow=0;dashed=0;image;image=img/lib/cumulus/rack_out_of_band.svg;\" vertex=\"1\" parent=\"1\">\n" +
				"      <mxGeometry x=\"80\" y=\"20\" width=\"96\" height=\"114\" as=\"geometry\"/>\n" +
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
