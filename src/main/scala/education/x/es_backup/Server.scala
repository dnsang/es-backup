package education.x.es_backup


import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import education.x.es_backup.controller.http
import education.x.es_backup.controller.http.HealthController
import education.x.es_backup.module.UserCacheModule
import education.x.es_backup.util.ZConfig

/**
  * Created by SangDang on 9/8/
  **/
object MainApp extends Server
class Server extends HttpServer{

  override protected def defaultFinatraHttpPort: String = ZConfig.getString("server.http.port",":8080")

  override protected def disableAdminHttpServer: Boolean = ZConfig.getBoolean("server.admin.disable",true)

  override val modules = Seq(UserCacheModule)

  override protected def configureHttp(router: HttpRouter): Unit = {
  router.filter[CommonFilters]
      .add[http.CacheController]
      .add[HealthController]
  }


}
