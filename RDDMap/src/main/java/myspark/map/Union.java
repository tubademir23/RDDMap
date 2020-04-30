package myspark.map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import utils.CupModel;
import utils.CupModelMap;

public class Union {
	public static void main(String[] args) {
		// System.setProperty("hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master");
		CupModelMap map = new CupModelMap("local", "Union");

		JavaRDD<CupModel> CupModel_Data = map.mapCupModel("WorldCups.csv", false);

		JavaRDD<CupModel> Turkey_Data = CupModel_Data.filter(new Function<CupModel, Boolean>() {
			public Boolean call(CupModel cupModel) throws Exception {
				return cupModel.hasCup("Turkey");
			}
		});
		JavaRDD<CupModel> Italy_Data = CupModel_Data.filter(new Function<CupModel, Boolean>() {
			public Boolean call(CupModel cupModel) throws Exception {
				return cupModel.hasCup("Italy");
			}
		});

		JavaRDD<CupModel> Turkey_Italy_Data = Turkey_Data.union(Italy_Data); //

		JavaRDD<String> Turkey_Italy_String_Data = Turkey_Italy_Data.map(new Function<CupModel, String>() {
			public String call(CupModel cupModel) throws Exception {

				return cupModel.toString();
			}
		});

		// sample output
		Turkey_Italy_String_Data.saveAsTextFile("TurkeyAndItaly.csv");

		map.getContext().close();

	}
}
