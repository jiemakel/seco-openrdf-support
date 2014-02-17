/**
 * 
 */
package fi.seco.openrdf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openrdf.rio.RDFFormat;

/**
 * @author jiemakel
 * 
 */
public class TestRDFFormats {

	@Test
	public void testForAcceptHeader() {
		assertThat(RDFFormats.forAcceptHeader("text/html,application/xml;q=0.5,*/*;q=0.8,application/sindice-de-tar;q=0.7,text/rdf+n3;q=0.75"), is(RDFFormat.N3));
		assertThat(RDFFormats.forAcceptHeader("text/html,application/xml;q=0.5,*/*;q=0.8,application/sindice-de-tar;q=0.7,text/n3;q=0.65"), is(RDFFormats.SINDICE_DE_TAR));
	}
}
