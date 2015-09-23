package implementations;

import specifications.NumberInterface;

public class Number implements NumberInterface {
	private String numberString;

	public Number(String number) {
		init(number);
	}

	@Override//????
	public int compareTo(NumberInterface o) {
		// TODO Auto-generated method stub
		return 0;
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

}