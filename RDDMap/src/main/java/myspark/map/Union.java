package myspark.map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

import utils.CupModel;
import utils.CupModelMap;

public class Union {
	public static void main(String[] args) {
		// System.setProperty("hadoop.home.dir",
		// "C:\\hadoop-common-2.2.0-bin-master");
		CupModelMap map = new CupModelMap("local", "Union");

		JavaRDD<CupModel> CupModel_Data = map.mapCupModel("WorldCups.csv",
				false);

		JavaRDD<CupModel> Turkey_Data = CupModel_Data
				.filter(new Function<CupModel, Boolean>() {
					public Boolean call(CupModel cupModel) throws Exception {
						return cupModel.hasCup("Turkey");
					}
				});
		JavaRDD<CupModel> Italy_Data = CupModel_Data
				.filter(new Function<CupModel, Boolean>() {
					public Boolean call(CupModel cupModel) throws Exception {
						return cupModel.hasCup("Italy");
					}
				});

		JavaRDD<CupModel> Turkey_Italy_Data = Italy_Data.union(Turkey_Data); //
		Turkey_Italy_Data.first();
		System.out.format("T: %s\t I: %s \t Union:  %s", Turkey_Data.count(),
				Italy_Data.count(), Turkey_Italy_Data.count());
		try {
			Files.deleteIfExists(Paths.get("TurkeyAndItaly.csv"));
			Turkey_Italy_Data.saveAsTextFile("TurkeyAndItaly.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Turkey_Italy_Data.foreach(model -> {
			System.out.println(model.toString());
		});
		map.getContext().close();

	}
}
