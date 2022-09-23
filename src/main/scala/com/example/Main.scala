package com.example

import io.getquill.context.ZioJdbc._
import zhttp.http._
import zhttp.service._
import zhttp.service.server.ServerChannelFactory
import zio._
import zio.config._
import zio.stream._
import com.example.api._
import com.example.config.Configuration._
import com.example.healthcheck._
import com.example.repo._
import com.example.service._


object Main extends ZIOAppDefault:

  private val dataSourceLayer = DataSourceLayer.fromPrefix("postgres-db")

  private val repoLayer = ItemRepositoryLive.layer

  private val serviceLayer = ItemServiceLive.layer


  val routes =
    HttpRoutes.app ++
    Healthcheck.routes
  

  val program = 
    for
      config <- getConfig[ServerConfig]
      _      <- Server.start(config.port, routes)
    yield ()

  override val run = 
    program.provide(ServerConfig.layer, serviceLayer, repoLayer, dataSourceLayer)
