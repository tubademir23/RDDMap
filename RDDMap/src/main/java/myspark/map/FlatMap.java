package myspark.map;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

public class FlatMap {
	public static void main(String[] args) {
		// System.setProperty("hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master");
		JavaSparkContext context = new JavaSparkContext("local", "Flat Map");
		JavaRDD<String> Raw_Data = context.textFile("WorldCups.csv");
		System.out.println("Raw_Data Count: " + Raw_Data.count());
		// one line all words are now a line
		JavaRDD<String> Flatmap_Data = Raw_Data.flatMap(new FlatMapFunction<String, String>() {
			public Iterator<String> call(String line) throws Exception {
				return Arrays.asList(line.split(",")).iterator();

			}
		});
		JavaRDD<String> Distinct_Data = Flatmap_Data.distinct();
		System.out.println("Distinct_Data Count: " + Distinct_Data.count());
		context.close();
	}
}
