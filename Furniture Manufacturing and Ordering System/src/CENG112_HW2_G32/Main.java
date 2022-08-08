package CENG112_HW2_G32;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NotInitializedException, EmptyDataException, MaxCapacityException {
		
		IProduct bed = new Bed();
		IProduct bookcase = new Bookcase();
		IProduct chair = new Chair();
		IProduct dresser = new Dresser();
		IProduct sofa = new Sofa();
		IProduct table = new Table();
		
		FactoryLine<IProduct> factoryLine = new FactoryLine<IProduct>();
		
		WareHouse<IProduct> bedWareHouse = new WareHouse<>();
		WareHouse<IProduct> bookcaseWareHouse = new WareHouse<>();
		WareHouse<IProduct> chairWareHouse = new WareHouse<>();
		WareHouse<IProduct> dresserWareHouse = new WareHouse<>();
		WareHouse<IProduct> sofaWareHouse = new WareHouse<>();
		WareHouse<IProduct> tableWareHouse = new WareHouse<>();
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of random request cycles: ");
		int cycleNumber = input.nextInt();
		input.close();
		
		for (int i=0; i<cycleNumber; i++) {
			/*
			if (i<9) {
				System.out.print(i+1 + ".   ");
			}else {
				System.out.print(i+1 + ".  ");
			}
			*/
			
			Random randomNumber = new Random();
			int randomChoice = randomNumber.nextInt(3);

			//MARKETING ANALYST
			if (randomChoice == 0) {
				int randomFurniture = randomNumber.nextInt(6);
				MarketingAnalyst(factoryLine, randomFurniture, bed, bookcase, chair, dresser, sofa, table);
				
			//STORAGE CHIEF
			}else if (randomChoice == 1) {
				try {
					IProduct storedFurniture = factoryLine.dequeue();
					if (storedFurniture != null) {
						switch (storedFurniture.getClass().getSimpleName()) {
							case "Bed":
								bedWareHouse.push(storedFurniture);

							case "Bookcase":
								bookcaseWareHouse.push(storedFurniture);

							case "Chair":
								chairWareHouse.push(storedFurniture);

							case "Dresser":
								dresserWareHouse.push(storedFurniture);

							case "Sofa":
								sofaWareHouse.push(storedFurniture);

							case "Table":
								tableWareHouse.push(storedFurniture);
						}
					storedFurniture.isStored();
					System.out.println("Storage Chief storing "+storedFurniture.toString()+", SUCCESS, "+storedFurniture.toString()+" stored in "+storedFurniture.toString()+" warehouse");
					}else {i--;}
				
				} catch (NotInitializedException exception) {
					exception.printStackTrace();
				}	
			
			//CUSTOMER
			}else if (randomChoice == 2) {
				int randomFurniture = randomNumber.nextInt(6);
				
				if (randomFurniture == 0) {
					Customer(bedWareHouse, "Bed", bed);
				}else if (randomFurniture == 1) {
					Customer(bookcaseWareHouse, "Bookcase", bookcase);
				}else if (randomFurniture == 2) {
					Customer(chairWareHouse, "Chair", chair);
				}else if (randomFurniture == 3) {
					Customer(dresserWareHouse, "Dresser", dresser);
				}else if (randomFurniture == 4) {
					Customer(sofaWareHouse, "Sofa", sofa);
				}else if (randomFurniture == 5) {
					Customer(tableWareHouse, "Table", table);
				}
			}
		}
		
		Report(bed,bookcase,chair,dresser,sofa,table);
	}
	
	public static void MarketingAnalyst(FactoryLine<IProduct> factoryLine, int product_num, IProduct bed, IProduct bookcase, IProduct chair, IProduct dresser, IProduct sofa, IProduct table) {
		IProduct newProduct=null;
		if (product_num == 0) {
			newProduct = bed;
		}else if(product_num == 1) {
			newProduct= bookcase;
		}else if(product_num == 2) {
			newProduct= chair;
		}else if(product_num == 3) {
			newProduct= dresser;
		}else if(product_num == 4) {
			newProduct= sofa;
		}else if(product_num == 5) {
			newProduct= table;
		}
		
		try {
			factoryLine.enqueue(newProduct);
			System.out.println("Marketing Analyst requesting " + newProduct.toString()+ ", SUCCESS, "+newProduct.toString()+" manufactured");
			newProduct.isManufactured();
			
		} catch (NotInitializedException exception) {
			exception.printStackTrace();
			
		} catch (MaxCapacityException exception) {
			exception.printStackTrace();
		}
	}
	
	public static void Customer(WareHouse<IProduct> productWareHouse, String productName, IProduct toBeSold) {
		try {
			IProduct item = productWareHouse.pop();
			if (item == toBeSold)  {
				System.out.println("Customer buying " + productName + ", SUCCESS, Customer bought " + productName);
				toBeSold.isSold();
			}
			
		} catch (NotInitializedException exception) {
			System.out.println("Customer buying "+ productName + ", FAIL, " + productName + " warehouse is empty");  //exception.printStackTrace();
			
		} catch (EmptyDataException exception) {
			System.out.println("Customer buying "+ productName + ", FAIL, " + productName + " warehouse is empty");
		}
	}
	

	public static void Report(IProduct bed, IProduct bookcase, IProduct chair, IProduct dresser, IProduct sofa, IProduct table) {
		
		System.out.println("\nREPORT:\n");
		
		System.out.println("Amount of Bed in Factory Line: " + bed.AmountInFactory());
		System.out.println("Amount of Sofa in Factory Line: " + sofa.AmountInFactory());
		System.out.println("Amount of Dresser in Factory Line: " + dresser.AmountInFactory());
		System.out.println("Amount of Table in Factory Line: " + table.AmountInFactory());
		System.out.println("Amount of Chair in Factory Line: " + chair.AmountInFactory());
		System.out.println("Amount of Bookcase in Factory Line: " + bookcase.AmountInFactory() + "\n");
		
		System.out.println("Amount of Bed in Bed Warehouse: " + bed.AmountInWareHouse());
		System.out.println("Amount of Sofa in Sofa Warehouse: " + sofa.AmountInWareHouse());
		System.out.println("Amount of Dresser in Dresser Warehouse: " + dresser.AmountInWareHouse());
		System.out.println("Amount of Table in Table Warehouse: " + table.AmountInWareHouse());
		System.out.println("Amount of Chair in Chair Warehouse: " + chair.AmountInWareHouse());
		System.out.println("Amount of Bookcase in Bookcase Warehouse: "+ bookcase.AmountInWareHouse()+ "\n");
		
		System.out.println("Amount of Bed Sold: " + bed.AmountSold());
		System.out.println("Amount of Sofa Sold: " + sofa.AmountSold());
		System.out.println("Amount of Dresser Sold: " + dresser.AmountSold());
		System.out.println("Amount of Table Sold: " + table.AmountSold());
		System.out.println("Amount of Chair Sold: " + chair.AmountSold());
		System.out.println("Amount of Bookcase Sold: " + bookcase.AmountSold()+ "\n");
	}
}
