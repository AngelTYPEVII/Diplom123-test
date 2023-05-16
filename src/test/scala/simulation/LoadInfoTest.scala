package simulation

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import scenarios.Simulation.createOrder


class LoadInfoTest extends Simulation {
  val httpConf = http.baseUrl("sys.props(\"WFStand\"")
  val testConfig = ConfigFactory.parseResources("test.conf")
  val userPerSec = testConfig.getString("userPerSec")
  val steps = testConfig.getString("steps")
  val timeUp = testConfig.getString("timeUp")
  val timeHold = testConfig.getString("timeHold")
  val startingUser = testConfig.getString("startingUser")

  setUp(
    createOrder.inject(
      incrementUsersPerSec(userPerSec.toInt) // на сколько поднимаем пользаков
        .times(steps.toInt) // кол-во ступенек
        .eachLevelLasting(timeUp.toInt) //время подъема
        .separatedByRampsLasting(timeHold.toInt) // время удержания
        .startingFrom(startingUser.toInt) //  с какой нагрузки начианется долбежка
    ).protocols(httpConf)
  )

}

