package main.domain

/**
 *
 * Using case classes for immutable resource data
 * These will store the averages for everything we've gathered 
 * and will be used for the API endpoints
 * */

enum Status: 
  case PASS
  case FAIL

case class CpuInfo(load: Double, temperature: Double, cores: Int, physicalCores: Int, status: Status)

case class RamInfo(usage: Double, total: Double, status: Status) 

case class PlatformInfo(currentPlatform: String) 

