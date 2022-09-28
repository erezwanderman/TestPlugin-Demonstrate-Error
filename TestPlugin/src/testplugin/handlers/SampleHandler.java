package testplugin.handlers;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.eclipse.jface.dialogs.MessageDialog;

public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ExecutionException("Error", e);
		}
	    InputSource is = new InputSource(new StringReader("<a>READING XML</a>"));
	    Document parsed;
	    try {
			parsed = builder.parse(is);
		} catch (SAXException | IOException e) {
			throw new ExecutionException("Error", e);
		}
	    String text = parsed.getDocumentElement().getFirstChild().getTextContent();
		MessageDialog.openInformation(
				window.getShell(),
				"TestPlugin",
				text/*"Hello, Eclipse world"*/);
		return null;
	}
}
