# Enterprise-Grade Routing Service

## 🏗️ System Architecture

AtlasRoute is a traffic-aware routing engine created with Java 25 and Spring Boot that calculates the shortest pathways over actual road networks.
The system creates a node-level intersection graph from real road data and interfaces with several map providers (Google Maps, Mapbox, HERE) through a clean abstraction layer. For high-performance route calculation, it offers sophisticated path-finding algorithms such as Dijkstra, A*, and an optimized Bidirectional A* implementation.

For both public APIs and external map provider calls, AtlasRoute uses Redis-based route caching, traffic-weighted edge costs, and rate-limit protection to ensure scalability and low latency. The service offers secure secret management and horizontal scaling and is completely containerized and prepared for Kubernetes.
The design is highly extensible, fault-tolerant, and appropriate for real-world, high-traffic production situations since it adheres to SOLID principles, Strategy and Factory patterns, and contemporary Java 25 capabilities.
### Architecture Layers

```
┌─────────────────────────────────────────────────────────────┐
│                     REST API Layer                          │
│              (Controllers, DTOs, Validation)                │
└─────────────────────────────────────────────────────────────┘
                            │
┌─────────────────────────────────────────────────────────────┐
│                   Application Layer                         │
│          (Service Orchestration, Business Logic)            │
└─────────────────────────────────────────────────────────────┘
                            │
┌──────────────┬────────────┴────────────┬──────────────────┐
│   Strategy   │    Provider Factory     │   Cache Layer    │
│   Pattern    │    (Google/Mapbox/HERE) │   (Redis)        │
└──────────────┴─────────────────────────┴──────────────────┘
                            │
┌─────────────────────────────────────────────────────────────┐
│                    Domain Layer                             │
│      (Graph, Node, Edge, Algorithms, Models)                │
└─────────────────────────────────────────────────────────────┘
```