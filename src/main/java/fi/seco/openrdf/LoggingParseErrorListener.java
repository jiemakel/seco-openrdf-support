package fi.seco.openrdf;

import org.openrdf.rio.ParseErrorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingParseErrorListener implements ParseErrorListener {

	private static final Logger log = LoggerFactory.getLogger(LoggingParseErrorListener.class);

	private final String file;

	public LoggingParseErrorListener(String file) {
		this.file = file;
	}

	@Override
	public void error(String arg0, int arg1, int arg2) {
		log.error(arg0 + " parsing " + file + " at line " + arg1 + ", column " + arg2);
	}

	@Override
	public void fatalError(String arg0, int arg1, int arg2) {
		log.error(arg0 + " parsing " + file + " at line " + arg1 + ", column " + arg2);
	}

	@Override
	public void warning(String arg0, int arg1, int arg2) {
		log.warn(arg0 + " parsing " + file + " at line " + arg1 + ", column " + arg2);
	}

}
