package fi.seco.openrdf;

import org.openrdf.model.URI;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IllegalURICorrectingValueFactory extends ValueFactoryImpl {

	private static final Logger log = LoggerFactory.getLogger(IllegalURICorrectingValueFactory.class);

	public static IllegalURICorrectingValueFactory instance = new IllegalURICorrectingValueFactory();

	@Override
	public URI createURI(String uriString) {
		if (uriString.indexOf(':') < 0) {
			log.warn(uriString + " is not a valid URI, correcting");
			uriString = "illegal:" + uriString;
		}
		return super.createURI(uriString);
	}

}
