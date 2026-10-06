import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the ProNSCL entity.
 */
class ProNSCLGatlingTest extends Simulation {

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

    val scn = scenario("Test the ProNSCL entity")
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
            exec(http("Get all proNSCLS")
            .get("/api/pro-nscls")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new proNSCL")
            .post("/api/pro-nscls")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "taiKhoan":"SAMPLE_TEXT"
                , "ngaySanXuat":"2020-01-01T00:00:00.000Z"
                , "stt":"0"
                , "mucTieuNgay":"0"
                , "thucHienNgay":"0"
                , "loiNgay":"0"
                , "suaNgay":"0"
                , "btpCap":"0"
                , "qty1h":"0"
                , "qty2h":"0"
                , "qty3h":"0"
                , "qty4h":"0"
                , "qty5h":"0"
                , "qty6h":"0"
                , "qty7h":"0"
                , "qty8h":"0"
                , "qty9h":"0"
                , "qty10h":"0"
                , "qty11h":"0"
                , "qty12h":"0"
                , "qty13h":"0"
                , "qty14h":"0"
                , "reject1h":"0"
                , "reject2h":"0"
                , "reject3h":"0"
                , "reject4h":"0"
                , "reject5h":"0"
                , "reject6h":"0"
                , "reject7h":"0"
                , "reject8h":"0"
                , "reject9h":"0"
                , "reject10h":"0"
                , "reject11h":"0"
                , "reject12h":"0"
                , "reject13h":"0"
                , "reject14h":"0"
                , "man1h":"0"
                , "man2h":"0"
                , "man3h":"0"
                , "man4h":"0"
                , "man5h":"0"
                , "man6h":"0"
                , "man7h":"0"
                , "man8h":"0"
                , "man9h":"0"
                , "man10h":"0"
                , "man11h":"0"
                , "man12h":"0"
                , "man13h":"0"
                , "man14h":"0"
                , "manOnly7Hours":"0"
                , "fix1h":"0"
                , "fix2h":"0"
                , "fix3h":"0"
                , "fix4h":"0"
                , "fix5h":"0"
                , "fix6h":"0"
                , "fix7h":"0"
                , "fix8h":"0"
                , "fix9h":"0"
                , "fix10h":"0"
                , "fix11h":"0"
                , "fix12h":"0"
                , "fix13h":"0"
                , "fix14h":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_proNSCL_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created proNSCL")
                .get("${new_proNSCL_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created proNSCL")
            .delete("${new_proNSCL_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
