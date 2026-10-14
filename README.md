# Torque – DO NOT RUN OUTSIDE OF A VIRTUAL MACHINE

A lightweight system stress-testing and performance monitoring tool written in Scala with ZIO.

- Scala
- ZIO combinators/fibers
- ZIO HTTP
- OSHI

## Features

- **CPU**: monitor and test CPU performance under load
- **Memory**: RAM stress testing with configurable memory allocation
- **Metrics**: live system performance monitoring via an HTTP API

## Quick Start

### Prerequisites

- Java 25
- Scala 3.7.4
- sbt 1.11.7

### Installation

```bash
git clone https://github.com/nasrlol/torque
cd torque
sbt run
```
Or get it from my Gitea:

```bash
git clone https://git.nsrddyn.com/nasr/torque
cd torque
sbt run
```

### Usage

Start the Torque server:
```bash
sbt run
```

TODO: explain how to run the tests.

The server will start on `http://localhost:8080` with the following endpoints:

- `GET /platform`: API status
- `GET /cpu`: CPU performance metrics and stress testing
- `GET /ram`: RAM usage metrics and memory allocation tests

### Example API Calls

```bash
# Check server status
curl http://localhost:8080/

# Get CPU metrics
curl http://localhost:8080/cpu

# Get RAM metrics  
curl http://localhost:8080/ram
```

## Architecture

Torque is built using modern Scala functional programming patterns:

- **ZIO**: for effect management and concurrency
- **ZIO HTTP**: for a high-performance HTTP server
- **OSHI**: for cross-platform system information

### Tree 

```

.
├── LICENSE
├── README.md
├── build.sbt
├── project
│   ├── build.properties
├── src
│   └── main
│       └── scala
│           └── main
│               ├── Main.scala
│               ├── domain
│               │   ├── CpuOperations.scala
│               │   └── MemoryOperations.scala
│               ├── infrastructure
│               │   ├── Http.scala
│               │   ├── Resources.scala
│               │   └── routes
│               │       └── Routes.scala
│               └── services
│                   ├── Benchmark.scala
│                   └── Stress.scala

```

## Development

### Building from Source

```bash
sbt compile
```

## API Documentation

### CPU Endpoint
`GET /cpu`
Returns CPU utilization metrics and allows initiating stress tests.

### RAM Endpoint  
`GET /ram`
Returns memory usage statistics and memory allocation test results.

## License

This project is licensed under the MIT License; see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with [ZIO](https://zio.dev/) for functional effects
- Uses [OSHI](https://github.com/oshi/oshi) for system information
- Inspired by stress-testing tools like `Linpack` and `Prime95`
