package myspark.action;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;

import utils.CupModel;
import utils.CupModelMap;

public class Collect {
	public static void main(String[] args) {
		// System.setProperty("hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master");
		CupModelMap map = new CupModelMap("local", "Map Func");

		JavaRDD<CupModel> CupModel_Data = map.mapCupModel("WorldCups.csv", false);
		List<CupModel> list = CupModel_Data.collect();
		map.toStringList(list);

		List<CupModel> listTake = CupModel_Data.take(5);
		map.toStringList(listTake);

	}
}
