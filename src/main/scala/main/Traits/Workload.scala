package main.Traits

import zio._

enum Status:
  case PASS 
  case FAIL 

trait Workload {

  def name: String
  def run:  ZIO[Any, Nothing, Unit]
}
