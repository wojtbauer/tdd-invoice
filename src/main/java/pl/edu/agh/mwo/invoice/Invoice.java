package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products = new HashMap<>();
	private static int nextNumber = 0;
	private final int number = ++nextNumber;

	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}
		if (products.containsKey(product)) {
			products.put(product, products.get(product) + quantity);
		} else {
			products.put(product, quantity);
		}
	}

	public BigDecimal getNetTotal() {
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;
	}

	public int getNumber() {
		return number;
	}

	public String preparePrint() {
//        String printed = String.valueOf(number);

		// solution 1 - with foreach loop
//		for (Product product: products.keySet()) {
//			printed += "\n";
//			printed += product.getName();
//			printed += " " + products.get(product);
//			printed += " " + product.getPrice();
//		}

		// solution 2 - with stream and concatenation
//        printed += products.keySet().stream()
//                .map(product -> "\n" + product.getName() + " " + products.get(product) + " " + product.getPrice())
//                .collect(Collectors.toList());

		// solution 3 - with stream and String.format
//        printed += products.keySet().stream()
//                .map(product -> String.format("\n%s %d %s", product.getName(), products.get(product), product.getPrice()))
//                .collect(Collectors.toList());

//        printed += "\nLiczba pozycji: " + products.size();


		// and always remember - it's better to use StringBuilder!
		// so here you go, solution 4 with streams, string format and StringBuilder (PRO)
		StringBuilder sb = new StringBuilder(String.valueOf(this.number));
		products.keySet().stream()
				.map(product -> String.format("\n%s %d %s", product.getName(), products.get(product), product.getPrice()))
				.forEach(sb::append);
		sb.append("\nLiczba pozycji: ").append(products.size());
		return sb.toString();
	}
}
