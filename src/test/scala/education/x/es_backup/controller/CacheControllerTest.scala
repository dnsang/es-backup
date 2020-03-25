package education.x.es_backup.controller

import java.net.InetSocketAddress

import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.thrift.ThriftClient
import com.twitter.inject.server.FeatureTest
import com.twitter.finagle.http.Status.Ok
import com.twitter.finagle.thrift.ThriftClientFramedCodec
import com.twitter.util.Future
import org.scalatest.Assertions
import education.x.es_backup.Server
import education.x.es_backup.domain.UserID
import education.x.es_backup.domain.thrift.{TUserID, TUserInfo}
import education.x.es_backup.service.TUserCacheService.FinagledClient
import education.x.es_backup.service.{TUserCacheService, UserCacheService}

/**
  * Created by SangDang on 9/18/16.
  */
class CacheControllerTest extends FeatureTest {
  override protected val server = new EmbeddedHttpServer(twitterServer = new Server)

  "[HTTP] Put cache" should {
    "successfull" in {
      server.httpPost(
        path = "/addUser",
        postBody =
          """
            {
              "user_id":{
                "id":"1"
              },
              "user_info":{
                "user_id":{
                  "id":"1"
                },
                "user_name":"test_1",
                "age":99,
                "sex":"male"
              }
            }
          """.stripMargin,
        andExpect = Ok
      )
    }
    "be able to get back" in {
      server.httpGet(
        path = "/getUser?user_id=1",
        andExpect = Status.Ok,
        withJsonBody =
          """
            {
              "user_id": {
                "id": "1"
              },
              "user_name": "test_1",
              "age": 99,
              "sex": "male"
            }
          """.stripMargin

      )
    }

  }

}