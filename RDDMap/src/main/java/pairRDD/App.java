package pairRDD;

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
		JavaPairRDD<String, Double> Pair_Data = CupModel_Data.mapToPair(new PairFunction<CupModel, String, Double>() {
			public Tuple2<String, Double> call(CupModel cupModel) throws Exception {
				return new Tuple2<String, Double>(cupModel.getFirst(), cupModel.getTotalParticipiant());
			}

		});
		Pair_Data.foreach(new VoidFunction<Tuple2<String, Double>>() {
			public void call(Tuple2<String, Double> tuple2) throws Exception {
				System.out.println("First is: " + tuple2._1 + "\t Total: " + tuple2._2);
				// System.out.println(tuple2);

			}
		});
		map.getContext().close();

	}

}
