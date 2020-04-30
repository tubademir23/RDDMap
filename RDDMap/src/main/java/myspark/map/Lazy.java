package myspark.map;

import org.apache.spark.api.java.JavaRDD;

import utils.CupModel;
import utils.CupModelMap;

public class Lazy {
	public static void main(String[] args) {
		CupModelMap map = new CupModelMap("local", "Lazy Func");

		JavaRDD<CupModel> CupModel_Data = map.mapCupModel("WorldCups.csv", true);
//until any action; lazy evaluation, so if you want get any rdd etc. call at least one action whatever it is. here; first

		CupModel_Data.first();
		map.getContext().close();
	}
}
