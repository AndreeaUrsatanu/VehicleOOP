import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class VechicleMain {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int testNumber = scanner.nextInt();
		scanner.close();
		
		switch (testNumber) {
		case 1:
			try {
				int producedByModifs = Vehicle.class.getDeclaredField("producedBy").getModifiers();
				if (Modifier.isStatic(producedByModifs) && Modifier.isFinal(producedByModifs)) {
					System.out.println("producedBy field correctly declared");
				} else {
					System.out.println("producedBy field incorrectly declared!");
				}
			} catch (NoSuchFieldException e) {
				System.out.println("producedBy field not declared!");
			}
			break;
		case 2:
			try {
				int vinModifs = Vehicle.class.getDeclaredField("vin").getModifiers();
				if (Modifier.isFinal(vinModifs)) {
					System.out.println("vin field correctly declared");
				} else {
					System.out.println("vin field incorrectly declared!");
				}
			} catch (NoSuchFieldException e) {
				System.out.println("vin field not declared!");
			}
			break;
		case 3:
			try {
				int productionYearModifs = Vehicle.class.getDeclaredField("productionYear").getModifiers();
				if (Modifier.isFinal(productionYearModifs)) {
					System.out.println("productionYear field correctly declared");
				} else {
					System.out.println("productionYear field incorrectly declared!");
				}
			} catch (NoSuchFieldException e) {
				System.out.println("productionYear field not declared!");
			}
			break;
		case 4:
			boolean setPlateNumberExists = false;
			boolean setKilometersExists = false;
			try {
				Vehicle.class.getDeclaredMethod("setPlateNumber", String.class);
				setPlateNumberExists = true;
			} catch (NoSuchMethodException e) {
				System.out.println("plateNumber field should be modifiable!");
			}
			try {
				Vehicle.class.getDeclaredMethod("setKilometers", double.class);
				setPlateNumberExists = true;
				System.out.println("kilometers field should be read only!");
			} catch (NoSuchMethodException e) {
			}
			if (setPlateNumberExists && !setKilometersExists) {
				System.out.println("plateNumber and kilometers fields correctly declared");
			}
			break;
		case 5:
			int constructorCount = Vehicle.class.getDeclaredConstructors().length;
			if (constructorCount >= 3) {
				System.out.println("Sufficient number of constructors");
			} else {
				System.out.println("Insufficient number of constructors!");
			}
			break;
		case 6:
			Vehicle v1 = new Vehicle(1997, "ABCD");
			v1.sellVehicle("1111", 2010);
			System.out.println(v1.getPlateNumber() + " " + v1.getLastSoldOnYear());
			v1.sellVehicle("1234", 2012);
			System.out.println(v1.getPlateNumber() + " " + v1.getLastSoldOnYear());
			break;
		case 7:
			Vehicle v2 = new Vehicle(1997, "ABCD");
			System.out.println(v2.getPositionX() + " " + v2.getPositionY());
			v2.moveVehicle(99, 199);
			System.out.println(v2.getPositionX() + " " + v2.getPositionY());
			break;
		case 8:
			Vehicle v3 = new Vehicle(1997, "ABCD");
			System.out.println(v3.isVinValid(false));
			System.out.println(v3.isVinValid(true));
			Vehicle v4 = new Vehicle(1997, "1M8GDM9AXKP042788");
			System.out.println(v4.isVinValid(false));
			System.out.println(v4.isVinValid(true));
			break;
		case 9:
			Vehicle v5 = new Vehicle(1997, "1M8GDM9AXKP042788");
			v5.printVinDecomposed();
			break;
		case 10:
			try {
				Method displayMethod = Vehicle.class.getDeclaredMethod("display");
				if (displayMethod.getReturnType() == String.class) {
					System.out.println("display method correctly declared");
				} else {
					System.out.println("display method incorrectly declared!");
				}
			} catch (NoSuchMethodException e) {
				System.out.println("display method not declared!");
			}
			break;
		}
	}
}
