package pl.edu.agh.mwo.invoice;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.PremiumOysters;
import pl.gov.sc.CustomsServiceVerificator;

import java.math.BigDecimal;

public class InvoiceAndScTest {
	private Invoice invoice;

	private CustomsServiceVerificator customsVerificator;

	private PremiumOysters importedProduct = new PremiumOysters("Ostrygi", new BigDecimal("100"));
	private DairyProduct notImportedProduct = new DairyProduct("Zsiadle mleko", new BigDecimal("100"));

	@Before
	public void createEmptyInvoiceForTheTest() {
		customsVerificator = EasyMock.createMock(CustomsServiceVerificator.class);
		invoice = new Invoice(this.customsVerificator);
	}

	@Test
	public void testCanAddImportedProductWhenScVerifies() {
		EasyMock.expect(customsVerificator.verify(importedProduct))
				.andReturn(true);
		EasyMock.replay(customsVerificator);
		invoice.addProduct(importedProduct);
		EasyMock.verify(customsVerificator);
		;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCanNotAddImportedProductWhenScDoesNotVerify() {
		EasyMock.expect(customsVerificator.verify(importedProduct))
				.andReturn(false);
		EasyMock.replay(customsVerificator);
		invoice.addProduct(importedProduct);
		EasyMock.verify(customsVerificator);
		;
	}

	@Test
	public void testDoesNotAsksScIfProductIsNotImported() {
		EasyMock.replay(customsVerificator);
		invoice.addProduct(notImportedProduct);
		EasyMock.verify(customsVerificator);
		;
	}
}
