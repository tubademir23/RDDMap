package myspark.map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

public class Sample {
	public static void main(String[] args) {
		// System.setProperty("hadoop.home.dir", "C:\\hadoop-common-2.2.0-bin-master");
		JavaSparkContext context = new JavaSparkContext("local", "Flat Map");
		JavaRDD<String> Raw_Data = context.textFile("WorldCups.csv");
		System.out.println("Raw_Data Count: " + Raw_Data.count());
		// one line all words are now a line
		// transformation struct create net rdd data
		// 0.5 means give me %50 of sample data
		JavaRDD<String> Sample_Data = Raw_Data.sample(false, 0.5);
		System.out.println("Sample_Data Count: " + Sample_Data.count());
		Sample_Data.foreach(new VoidFunction<String>() {
			public void call(String string) throws Exception {
				System.out.println(string);

			}
		});
	}
}
