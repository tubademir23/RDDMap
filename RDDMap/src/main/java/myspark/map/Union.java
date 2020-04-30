package myspark.map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

import utils.CupModel;
import utils.CupModelMap;

public class Union {
	public static void main(String[] args) {
		// System.setProperty("hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master");
		CupModelMap map = new CupModelMap("local", "Flat Map");

		JavaRDD<CupModel> CupModel_Data = map.mapCupModel("WorldCups.csv");

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

		JavaRDD<CupModel> Turkey_Italy_Data = Turkey_Data.union(Italy_Data); // one line all words are now a line
		Turkey_Italy_Data.foreach(new VoidFunction<CupModel>() {
			public void call(CupModel cupModel) throws Exception {
				System.out.println(cupModel.getYear());
				cupModel.toString();
			}
		});

	}
}
