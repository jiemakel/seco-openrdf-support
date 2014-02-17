/**
 * 
 */
package fi.seco.openrdf;

import java.nio.charset.Charset;

import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.RDFParserFactory;

import fi.seco.util.MediaTypeUtil;
import fi.seco.util.MediaTypeUtil.MediaType;

/**
 * @author jiemakel
 * 
 */
public class RDFFormats implements RDFParserFactory {
	public static final RDFFormat FREEBASE_QUADS = RDFFormat.register("Freebase-Quads", "text/fbquads", "fbq", Charset.forName("UTF-8"));
	public static final RDFFormat SINDICE_DE_TAR = RDFFormat.register("Sindice-DE-TAR", "application/sindice-de-tar", "sdetar", Charset.forName("UTF-8"));

	private static final RDFFormat DUMMY = RDFFormat.register("Dummy", "text/dummy", "dummy", Charset.forName("UTF-8"));

	@Override
	public RDFFormat getRDFFormat() {
		return DUMMY;
	}

	@Override
	public RDFParser getParser() {
		return null;
	}

	public static RDFFormat forParameters(String lang, String ctype, String acceptHeader, RDFFormat fallback) {
		RDFFormat type = null;
		if (lang != null) {
			type = RDFFormat.valueOf(lang);
			if (type == null) type = RDFFormat.forFileName(lang.contains(".") ? lang : '.' + lang);
		} else if (ctype != null)
			type = RDFFormat.forMIMEType(ctype);
		else if (acceptHeader != null) type = RDFFormats.forAcceptHeader(acceptHeader);
		if (type == null && fallback != null) type = fallback;
		return type;
	}

	/**
	 * Return a preferred RDF format for an accept or content-type header
	 * 
	 * @param acceptHeader
	 *            the accept or content-type header
	 * @return the preferred RDFFormat for the accept or content-type header
	 */
	public static RDFFormat forAcceptHeader(String acceptHeader) {
		MediaType[] mediaTypes = MediaTypeUtil.getMediaTypesForAcceptHeaderSortedByPreference(acceptHeader);
		for (MediaType t : mediaTypes) {
			RDFFormat type = RDFFormat.forMIMEType(t.getMimeType());
			if (type != null) return type;
		}
		return null;
	}

}
