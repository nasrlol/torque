package com.nsrddyn.Traits

trait Workload {

  def name: String
  def run:  ZIO[Any, Nothing, Unit]
}
