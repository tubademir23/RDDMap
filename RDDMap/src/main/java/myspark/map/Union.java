package myspark.map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

public class Union {
	public static void main(String[] args) {
		// System.setProperty("hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master");
		JavaSparkContext context = new JavaSparkContext("local", "Flat Map");
		JavaRDD<String> Raw_Data = context.textFile("WorldCups.csv");
		System.out.println("Raw_Data Count: " + Raw_Data.count());

		JavaRDD<CupModel> CupModel_Data = Raw_Data.map(new Function<String, CupModel>() {
			public CupModel call(String line) throws Exception {

				String[] split = line.split(",");

				return new CupModel(split[0], split[1], split[2], split[3], split[4], split[5],
						Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]),
						Double.parseDouble(split[9]));

			}
		});

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
				System.out.println(cupModel.year);
				cupModel.toString();
			}
		});

	}
}
