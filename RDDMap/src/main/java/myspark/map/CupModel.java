package myspark.map;

import java.io.Serializable;

public class CupModel implements Serializable {
	String year;
	String houseHolder;
	String first;
	String second;
	String third;
	String forth;
	int countGoal;
	int countCountry;
	int countMatch;
	double totalParticipiant;

	public CupModel(String year, String houseHolder, String first, String second, String third, String forth,
			int countGoal, int countCountry, int countMatch, double totalParticipiant) {
		super();
		this.year = year;
		this.houseHolder = houseHolder;
		this.first = first;
		this.second = second;
		this.third = third;
		this.forth = forth;
		this.countGoal = countGoal;
		this.countCountry = countCountry;
		this.countMatch = countMatch;
		this.totalParticipiant = totalParticipiant;
	}

	public boolean hasCup(String country) {
		// System.out.println("??? " + country + "\t1.: " + first.equals(country) +
		// "\t2.: " + second + "\t3.: " + third);
		return first.equals(country) || second.equals(country) || third.equals(country);
	}

	public String toString() {
		return "Year: " + year + "\tHH: " + houseHolder + "\t1.: " + first + "\t2.: " + second + "\t3.: " + third
				+ "\t4.:" + forth + "\t Goal: " + countGoal + "\t Country:" + countCountry + "\tMatch:" + countMatch
				+ "\tParticipiant:" + totalParticipiant;

	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(String houseHolder) {
		this.houseHolder = houseHolder;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public String getForth() {
		return forth;
	}

	public void setForth(String forth) {
		this.forth = forth;
	}

	public int getCountGoal() {
		return countGoal;
	}

	public void setCountGoal(int countGoal) {
		this.countGoal = countGoal;
	}

	public int getCountCountry() {
		return countCountry;
	}

	public void setCountCountry(int countCountry) {
		this.countCountry = countCountry;
	}

	public int getCountMatch() {
		return countMatch;
	}

	public void setCountMatch(int countMatch) {
		this.countMatch = countMatch;
	}

	public double getTotalParticipiant() {
		return totalParticipiant;
	}

	public void setTotalParticipiant(double totalParticipiant) {
		this.totalParticipiant = totalParticipiant;
	}

}
