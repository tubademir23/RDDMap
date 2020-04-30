package pairRDD;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;

import scala.Tuple2;
import utils.CupModel;
import utils.CupModelMap;

public class App {

	public static void main(String[] args) {
		CupModelMap map = new CupModelMap("local", "Map Func");

		JavaRDD<CupModel> CupModel_Data = map.mapCupModel("WorldCups.csv");

		// this get, first as key and totalparticipiant as value.
		// then group by key so, iterable values
		JavaPairRDD<String, Iterable<Double>> Pair_Group_Data = CupModel_Data
				.mapToPair(new PairFunction<CupModel, String, Double>() {
					public Tuple2<String, Double> call(CupModel cupModel) throws Exception {
						return new Tuple2<String, Double>(cupModel.getFirst(), cupModel.getTotalParticipiant());
					}

				}).groupByKey();

		DecimalFormat df = new DecimalFormat("#,##0.00");
		Pair_Group_Data.foreach(new VoidFunction<Tuple2<String, Iterable<Double>>>() {
			public void call(Tuple2<String, Iterable<Double>> tuple2) throws Exception {
				Iterator<Double> values = tuple2._2().iterator();

				double t = 0.0d;

				while (values.hasNext()) {
					double next = values.next().doubleValue();
					t = Double.sum(next, t);
				}

				System.out.println(
						"First is: " + tuple2._1 + "\t P(Array): " + tuple2._2 + "\tT: " + String.format("%,.2f", t));

			}
		});
		map.getContext().close();

	}

}
