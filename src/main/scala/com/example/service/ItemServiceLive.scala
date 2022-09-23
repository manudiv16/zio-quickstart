package com.example.service

import zio._
import zio.stream._
import com.example.domain._
import com.example.domain.DomainError.BusinessError
import com.example.repo._

final class ItemServiceLive(repo: ItemRepository) extends ItemService:
  def addItem(description: String): UIO[ItemId] =
    repo.add(description).orDie

  def deleteItem(id: ItemId): UIO[Unit] =
    repo.delete(id).orDie

  def getAllItems(): UIO[List[Item]] =
    repo.getAll().orDie

  def getItemById(id: ItemId): UIO[Option[Item]] =
    repo.getById(id).orDie

  def updateItem(id: ItemId, description: String): IO[DomainError, Unit] =
    for
      foundOption <- getItemById(id)
      _ <- ZIO
        .fromOption(foundOption)
        .mapError(_ => BusinessError(s"Item with ID ${id.value} not found"))
        .flatMap(item => repo.update(Item(id, description)).orDie)
    yield ()

object ItemServiceLive:
  val layer: URLayer[ItemRepository, ItemService] =
    ZLayer(ZIO.service[ItemRepository].map(ItemServiceLive(_)))
