package javaassessment;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opt = 0;
		Scanner s = new Scanner(System.in);
		List<Stock> stl = new ArrayList<>();
		List<Sales> sal = new ArrayList<>();
		Stock st0 = new Stock(101, "Coca Cola", "30", 50);
		stl.add(st0);

		Stock st1 = new Stock(102, "Lays", "20", 50);
		stl.add(st1);

		Stock st2 = new Stock(103, "Butterscotch Ice Cream", "40", 20);
		stl.add(st2);

		Stock st3 = new Stock(104, "Eggs", "6", 40);
		stl.add(st3);

		Stock st4 = new Stock(105, "Cadbury Dairy Milk Chocolate", "30", 30);
		stl.add(st4);

		Stock st5 = new Stock(106, "Maggi", "20", 50);
		stl.add(st5);

		Stock st6 = new Stock(107, "Hide and Seek chocolate cookies ", "30", 25);
		stl.add(st6);
		do {
			System.out.println("______________________________");
			System.out.println();
			System.out.println("             MENU");
			System.out.println("______________________________");
			System.out.println("Select one of the following by typing [1, 2 or 3]");
			System.out.println("1. Stock Update");
			System.out.println("2. Sales");
			System.out.println("3. Display Stock List");
			System.out.println("4. Exit");
			int o = s.nextInt();
			switch (o) {
			case 1:
				System.out.println("Enter Product ID(numbers)");
				String idst = s.next();
				int qua;
				int id = Integer.parseInt(idst);
				if (idst.matches("[0-9]+") && idst.length() == 3) {
					for (Stock sto : stl) {
						if (sto.id == id) {
							System.out.println("Product Name: " + sto.name);
							System.out.println("Product Price: " + sto.price);
							System.out.println("Product quantity in stock: " + sto.quant);
							System.out.println("1. Update Quantity");
							System.out.println("2. Update price");
							System.out.println("Choose [1 or 2]");
							int cho = s.nextInt();
							switch (cho) {
							case 1:
								System.out.println("Enter New Quantity");
								qua = s.nextInt();
								sto.quant = qua;
								System.out.println("Quantity Updated");
								System.out.println();
								System.out.println("UPDATED STOCK LIST");
								System.out.println(
										"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
								System.out.printf("%5s %50s %50s %50s", "ID", "Product Name", "Price", "Quantity");
								System.out.println();
								System.out.println(
										"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

								for (Stock stk : stl) {
									System.out.printf("%5d %50s %50f %50d", stk.id, stk.name, stk.price, stk.quant);
									System.out.println();
								}
								System.out.println(
										"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

								break;
							case 2:
								System.out.println("Enter New Price");
								String nprice = s.next();
								if (nprice.contains(".")) {
									sto.price = Float.parseFloat(nprice);
									System.out.println("Price Updated");
									System.out.println();
									System.out.println("UPDATED STOCK LIST");
									System.out.println(
											"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
									System.out.printf("%5s %50s %50s %50s", "ID", "Product Name", "Price", "Quantity");
									System.out.println();
									System.out.println(
											"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

									for (Stock stk : stl) {
										System.out.printf("%5d %50s %50f %50d", stk.id, stk.name, stk.price, stk.quant);
										System.out.println();
									}
									System.out.println(
											"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

								} else {
									System.err.println("Price should be a float value.");
								}
								break;
							}
						}
					}
				} else {
					System.err.println("ID can only have numbers upto 3 digits.");
				}
				break;

			case 2:
				System.out.println("Enter Product ID");
				String idstr = s.next();
				int quan = 0;
				int c = 0, con = 0, co = 0;
				float totprice = 0;
				if (idstr.matches("[0-9]+") && idstr.length() == 3) {
					int idi = Integer.parseInt(idstr);
					for (Stock sto : stl) {
						if (sto.id == idi) {
							System.out.println("Your product is " + sto.name);
							System.out.println("Product quantity in stock: " + sto.quant);
							System.out.println("Enter required quantity");
							qua = s.nextInt();
							if (sal.size() == 0) {
								if (qua <= sto.quant) {
									totprice = qua * sto.price;
									sto.quant = sto.quant - qua;
									Sales sa = new Sales(idi, sto.name, sto.price, qua, totprice);
									sal.add(sa);
								} else {
									System.err.println("Quantity entered is more than the available stock");
								}
							} else {
								for (Sales sl : sal) {
									if (sl.id == idi && qua <= sto.quant) {
										sl.qua = sl.qua + qua;
										sl.totprice = sl.qua * sto.price;
										sto.quant = sto.quant - qua;
									} else if (sl.id != idi && qua <= sto.quant) {
										co++;
										if (co == sal.size()) {
											totprice = qua * sto.price;
											sto.quant = sto.quant - qua;
											con = 1;
										}
									} else {
										System.err.println("Quantity entered is more than the available stock");
									}
								}
								if (con == 1) {
									Sales sa = new Sales(idi, sto.name, sto.price, qua, totprice);
									sal.add(sa);
								}
							}
						}

						else {
							c++;
						}
					}
					if (c == stl.size()) {
						System.err.println("Product with the entered ID does not exist.");
					}
				} else {
					System.err.println("ID can only have numbers upto 3 digits.");
				}

				float tot = 0f;
				System.out.println("CART");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%5s %50s %50s %50s %50s", "ID", "Product Name", "Price", "Quantity", "Total Price");
				System.out.println();
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				for (Sales sl : sal) {
					System.out.printf("%5d %50s %50f %50d %50f", sl.id, sl.name, sl.price, sl.qua, sl.totprice);
					System.out.println();
					tot = tot + sl.totprice;
				}
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%5s %200f", "Total:", tot);
				System.out.println();
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				break;

			case 3:
				System.out.println("STOCK LIST");
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.printf("%5s %50s %50s %50s", "ID", "Product Name", "Price", "Quantity");
				System.out.println();
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

				for (Stock stk : stl) {
					System.out.printf("%5d %50s %50f %50d", stk.id, stk.name, stk.price, stk.quant);
					System.out.println();
				}
				System.out.println(
						"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				break;

			case 4:
				System.out.println("Exiting...");
				opt = -1;
				break;
			}
		} while (opt != -1);
	}

}
