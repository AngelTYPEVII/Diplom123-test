package request

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.restassured.RestAssured.`given`
import model.Info


object Request {

  var request: HttpRequestBuilder = http("Element")
    .post("/loadtest/api/runCollector".trim)
    .header("authorization", "Bearer ")
    .body(StringBody("{\"requestId\": \"AngelTYPEVII\",\"body\": \"Oo\"}")).asJson
    .check(status.is(200))

  var requestInfo: HttpRequestBuilder = http("Info")
    .post("/loadtest/api/bindQueue".trim)
    .header("authorization", "Bearer " )
    .body(StringBody("{\"queue\":\"test\"}")).asJson
    .check(status.is(200))

}
