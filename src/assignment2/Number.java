package assignment2;


public class Number implements NumberInterface{
	private String numberString;

	public Number(String number) {
		init(number);
	}

	@Override
	public int compareTo(NumberInterface number) {
		double x = Double.parseDouble(toString());
		double y = Double.parseDouble(number.toString());
		
		return Double.compare(x, y);
	}

	@Override
	public void init(String numberString) {
		this.numberString = numberString;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj == this) {
			return true;
		} else if (obj.getClass() != getClass()) {
			return false;
		}

		Number numberObj = (Number) obj;
		return numberObj.numberString.equals(numberString) ? true : false;
	}

	@Override
	public NumberInterface clone() {
		return new Number(numberString);
	}

	public String toString(){
		return numberString;
	}
}
