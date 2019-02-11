package pl.edu.agh.mwo.invoice.product;

import pl.gov.sc.Imported;

import java.math.BigDecimal;

public class BottleOfWine extends OtherProduct implements HasExcise, Imported {
	public BottleOfWine(String name, BigDecimal price) {
		super(name, price);
	}

	@Override
	public BigDecimal getExcise() {
		return new BigDecimal("5.56");
	}

	@Override
	public Integer getImportId() {
		return null;
	}
}
