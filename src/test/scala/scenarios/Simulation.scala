package scenarios

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef.scenario
import request.Request.{request, requestInfo}


object Simulation {
  def createOrder = scenario("First scenario").exec(request)
  def orderInfo = scenario("orderInfo").exec(requestInfo)

  val testConfig = ConfigFactory.parseResources("test.conf")
  val rete1 = testConfig.getString("rate1")
  val rete2 = testConfig.getString("rate2")
  val duration = testConfig.getString("duration")
}
