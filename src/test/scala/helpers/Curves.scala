package helpers

import io.gatling.core.Predef._

import scala.concurrent.duration._

object Curves {
  def burstsGenerator(numberOfBursts: Int, startRPS: Int, lowRPS: Int, rampTime: Int, constantTime: Int, highRPS: Int,
                      burstRampTime: Int, relaxTime: Int) = {
    var actions = Seq(nothingFor(0.seconds))
    actions = actions :+
      (rampUsersPerSec(startRPS) to lowRPS during (rampTime)) :+
      (constantUsersPerSec(lowRPS) during (constantTime))
    for (i <- 1 to numberOfBursts) {
      actions = actions :+
        (rampUsersPerSec(lowRPS) to highRPS during (burstRampTime)) :+
        (constantUsersPerSec(highRPS) during (2)) :+
        (rampUsersPerSec(highRPS) to lowRPS during (burstRampTime)) :+
        (constantUsersPerSec(lowRPS) during (relaxTime))
    }
    actions
  }

  def spikesGenerator(numberOfBursts: Int, startRPS: Int, lowRPS: Int, rampTime: Int, constantTime: Int, highRPS: Int,
                      spikeRampTime: Int, relaxTime: Int) = {
    var actions = Seq(nothingFor(0.seconds))
    actions = actions :+
      (rampUsersPerSec(startRPS) to lowRPS during (rampTime)) :+
      (constantUsersPerSec(lowRPS) during (constantTime))
    for (i <- 1 to numberOfBursts) {
      actions = actions :+
        (constantUsersPerSec(highRPS) during (spikeRampTime)) :+
        (constantUsersPerSec(lowRPS) during (relaxTime))
    }
    actions
  }
}
