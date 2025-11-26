package com.nsrddyn.Traits

import zio._

trait Workload {

  def name: String
  def run:  ZIO[Any, Nothing, Unit]
}
