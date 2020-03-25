package education.x.es_backup.module

import javax.inject.Singleton

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import education.x.es_backup.domain.{UserID, UserInfo}
import education.x.es_backup.repository.{CacheRepository, OnMemoryCacheRepository}
import education.x.es_backup.service.{UserCacheService, UserCacheServiceImpl}

/**
  * Created by SangDang on 9/16/16.
  */
object UserCacheModule extends TwitterModule {
  override def configure: Unit = {
    bind[UserCacheService].to[UserCacheServiceImpl]
  }

  @Singleton
  @Provides
  def providesUserCacheRepository(): CacheRepository[UserID, UserInfo] = {
    new OnMemoryCacheRepository[UserID, UserInfo]()
  }
}
