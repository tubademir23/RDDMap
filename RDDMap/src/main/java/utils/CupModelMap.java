package utils;

import java.io.Serializable;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class CupModelMap implements Serializable {
	private static JavaSparkContext context;

	public CupModelMap(String master, String appName) {
		context = new JavaSparkContext(master, appName);
	}

	public JavaSparkContext getContext() {
		return context;
	}

	public void setContext(JavaSparkContext context) {
		this.context = context;
	}

	public JavaRDD<CupModel> mapCupModel(String path, boolean out) {
		JavaRDD<String> String_Data = context.textFile(path);

		JavaRDD<CupModel> CupModel_Data = String_Data.map(new Function<String, CupModel>() {
			public CupModel call(String line) throws Exception {
				if (out) {
					System.out.println(line);
				}
				String[] split = line.split(",");

				return new CupModel(split[0], split[1], split[2], split[3], split[4], split[5],
						Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]),
						Double.parseDouble(split[9]));

			}
		});

		return CupModel_Data;

	}

	public void toStringList(List<CupModel> list) {
		for (CupModel cupModel : list) {
			System.out.println(cupModel.toString());
		}
		System.out.println(list.size());
	}

}
