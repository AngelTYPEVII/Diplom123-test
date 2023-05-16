package simulation

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import scenarios.Simulation.{createOrder, orderInfo}


class LoadTest extends Simulation {

  val testConfig = ConfigFactory.parseResources("test.conf")
  val rete1 = testConfig.getString("rate1")
  val rete2 = testConfig.getString("rate2")
  val duration = testConfig.getString("duration")
  val httpConf = http.baseUrl(sys.props("WFStand").trim)
  setUp(
    createOrder.inject(
      rampUsersPerSec(rete1.toInt) to (rete2.toInt) during (duration.toInt)
    ).protocols(httpConf)
  )

}

