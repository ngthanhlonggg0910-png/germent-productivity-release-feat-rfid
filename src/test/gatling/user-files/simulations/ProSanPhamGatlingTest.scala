import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the ProSanPham entity.
 */
class ProSanPhamGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the ProSanPham entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all proSanPhams")
            .get("/api/pro-san-phams")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new proSanPham")
            .post("/api/pro-san-phams")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "maSanPham":"SAMPLE_TEXT"
                , "maDonHang":"SAMPLE_TEXT"
                , "po":"SAMPLE_TEXT"
                , "soLuongSanXuat":"0"
                , "thongSoValue1":"SAMPLE_TEXT"
                , "thongSoCaption1":"SAMPLE_TEXT"
                , "thongSoCheck1":null
                , "thongSoValue2":"SAMPLE_TEXT"
                , "thongSoCaption2":"SAMPLE_TEXT"
                , "thongSoCheck2":null
                , "thongSoValue3":"SAMPLE_TEXT"
                , "thongSoCaption3":"SAMPLE_TEXT"
                , "thongSoCheck3":null
                , "thongSoValue4":"SAMPLE_TEXT"
                , "thongSoCaption4":"SAMPLE_TEXT"
                , "thongSoCheck4":null
                , "thongSoValue5":"SAMPLE_TEXT"
                , "thongSoCaption5":"SAMPLE_TEXT"
                , "thongSoCheck5":null
                , "thongSoValue6":"SAMPLE_TEXT"
                , "thongSoCaption6":"SAMPLE_TEXT"
                , "thongSoCheck6":null
                , "thongSoValue7":"SAMPLE_TEXT"
                , "thongSoCaption7":"SAMPLE_TEXT"
                , "thongSoCheck7":null
                , "thongSoValue8":"SAMPLE_TEXT"
                , "thongSoCaption8":"SAMPLE_TEXT"
                , "thongSoCheck8":null
                , "thongSoValue9":"SAMPLE_TEXT"
                , "thongSoCaption9":"SAMPLE_TEXT"
                , "thongSoCheck9":null
                , "thongSoValue10":"SAMPLE_TEXT"
                , "thongSoCaption10":"SAMPLE_TEXT"
                , "thongSoCheck10":null
                , "color":"SAMPLE_TEXT"
                , "hinhMatTruocURL":"SAMPLE_TEXT"
                , "hinhMatSauURL":"SAMPLE_TEXT"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_proSanPham_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created proSanPham")
                .get("${new_proSanPham_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created proSanPham")
            .delete("${new_proSanPham_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
