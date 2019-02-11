package pl.edu.agh.mwo.invoice.product;

import pl.gov.sc.Imported;

import java.math.BigDecimal;

public class PremiumOysters extends OtherProduct implements Imported {
	public PremiumOysters(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public Integer getImportId() {
		return null;
	}
}
