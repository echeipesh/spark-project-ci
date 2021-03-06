package whatif

import geotrellis.layer._
import geotrellis.vector._
import geotrellis.proj4._
import geotrellis.raster._
import geotrellis.spark._

import cats.implicits._
import com.monovore.decline._

import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.rdd._

import org.log4s._

object  Main {
  @transient private[this] lazy val logger = getLogger

  private val inputsOpt = Opts.options[String](
    "inputs", help = "The path that points to data that will be read")
  private val outputOpt = Opts.option[String](
    "output", help = "The path of the output tiffs")
  private val partitionsOpt =  Opts.option[Int](
    "numPartitions", help = "The number of partitions to use").orNone

  private val command = Command(name = "spark-project-ci", header = "GeoTrellis App: spark-project-ci") {
    (inputsOpt, outputOpt, partitionsOpt).tupled
  }

  def main(args: Array[String]): Unit = {
    command.parse(args, sys.env) match {
      case Left(help) =>
        System.err.println(help)
        sys.exit(1)

      case Right((i, o, p)) =>
        try {
          run(i.toList, o, p)(Spark.context)
        } finally {
          Spark.session.stop()
        }
    }
  }

  def run(inputs: List[String], output: String, numPartitions: Option[Int])(implicit sc: SparkContext): Unit = {
    // Job Logic
  }
}
