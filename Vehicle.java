public class Vehicle {

	private final static String producedBy = "Renault";
	private final int productionYear;
	private final String vin;
	private String plateNumber;
	private double kilometers;
	private int lastSoldOnYear;
	private double positionX;
	private double positionY;

	public Vehicle(int productionYear, String vin) {
		this.productionYear = productionYear;
		this.vin = vin;
	}

	public Vehicle(int productionYear, String vin, String plateNumber, int lastSoldOnYear) {
		this(productionYear, vin);
		this.plateNumber = plateNumber;
		this.lastSoldOnYear = lastSoldOnYear;
	}

	public Vehicle(int productionYear, String vin, String plateNumber, int lastSoldOnYear, int kilometers,
			double positionX, double positionY) {
		this(productionYear, vin, plateNumber, lastSoldOnYear);
		this.kilometers = kilometers;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public String getProducedBy() {
		return Vehicle.producedBy;
	}

	public int getProductionYear() {
		return this.productionYear;
	}

	public String getVin() {
		return this.vin;
	}

	public String getPlateNumber() {
		return this.plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public double getKilometers() {
		return this.kilometers;
	}

	public int getLastSoldOnYear() {
		return this.lastSoldOnYear;
	}

	public void setLastSoldOnYear(int lastSoldOnYear) {
		this.lastSoldOnYear = lastSoldOnYear;
	}

	public double getPositionX() {
		return this.positionX;
	}

	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}

	public double getPositionY() {
		return this.positionY;
	}

	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	public void sellVehicle(String plateNumber, int lastSoldOnYear) {
		this.plateNumber = plateNumber;
		this.lastSoldOnYear = lastSoldOnYear;
	}

	public void moveVehicle(double positionX, double positionY) {
		double distance = Math
				.sqrt(Math.pow((positionX - this.positionX), 2) + Math.pow((positionY - this.positionY), 2));

		this.kilometers += distance;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public boolean isVinValid(boolean isDrivingInNorthAmerica) {
		if (!isDrivingInNorthAmerica) {
			return true;
		}

		if (this.vin.length() != 17) {
			return false;
		}
		
		if (this.vin.contains('I' + "") || this.vin.contains('O' + "") ||
				this.vin.contains('Q' + "")) {
			return false;
		}

		return calculateCheckDigit() < 11;
	}

	private int calculateCheckDigit() {
		String valueVin = "";

		for (int i = 0; i < this.vin.length(); i++) {
			char curentChar = this.vin.charAt(i);

			if (curentChar != 'I' && curentChar != 'O' && curentChar != 'Q') {
				if (curentChar >= 'A' && curentChar <= 'Z') {
					int curentCharValue = 0;
					curentCharValue += (curentChar - 'A' + 1);
	
					int sumCurentCharValue = 0;
	
					while (curentCharValue > 0) {
						sumCurentCharValue += curentCharValue % 10;
						curentCharValue /= 10;
					}
	
					valueVin += sumCurentCharValue;
				} else {
					valueVin += curentChar;
				}
			}
		}

		int[] weights = { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2 };
		int sumVin = 0;

		for (int i = 0; i < valueVin.length(); i++) {
			if (Character.isDigit(valueVin.charAt(i))) {
				sumVin += Character.getNumericValue(valueVin.charAt(i)) * weights[i];
			}
		}

		int checkDigit = sumVin % 11;

		return checkDigit;
	}

	public void printVinDecomposed() {
		StringBuilder sb = new StringBuilder();

		sb.append("Identificatorul producatorului: " + vin.substring(0, 3) + "\n");
		sb.append("Atributele vehiculului: " + vin.substring(3, 8) + "\n");
		sb.append("Cifra de verificare: " + vin.charAt(8) + "\n");
		sb.append("Anul productiei modelului: " + vin.charAt(9) + "\n");
		sb.append("Seria fabricii: " + vin.charAt(10) + "\n");
		sb.append("Identificatorul numeric: " + vin.substring(11) + "\n");

		System.out.print(sb.toString());
	}
	
	public String display() {
		StringBuilder sb = new StringBuilder();

		sb.append("This car has been produced by: " + Vehicle.producedBy + "\n");
		sb.append("Production year: " + this.productionYear + "\n");
		sb.append("Vin: " + this.vin + "\n");
		sb.append("Plate number: " + this.plateNumber + "\n");
		sb.append("Kilometers: " + this.kilometers + "\n");
		sb.append("Last sold on year: " + this.lastSoldOnYear + "\n");
		sb.append("PositionX: " + this.positionX + "\n");
		sb.append("PositionY: " + this.positionY + "\n");

		return sb.toString();
	}

}
