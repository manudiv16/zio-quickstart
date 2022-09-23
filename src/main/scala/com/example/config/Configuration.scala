package com.example.config

import zio._
import zio.config._
import zio.config.ConfigDescriptor._

object Configuration:

  final case class ServerConfig(port: Int)

  object ServerConfig:

    private val conf: ConfigDescriptor[ServerConfig] =
      (int("PORT").default(8090)).to[ServerConfig]

    private val pgmConfig: ConfigDescriptor[ServerConfig] =
      conf from ConfigSource.fromSystemEnv()

    val layer = ZLayer(
      read(
        pgmConfig 
      )
    )

// from(
//           TypesafeConfigSource.fromTypesafeConfig(
//             ZIO attempt ConfigFactory.defaultApplication
//           )
//         )