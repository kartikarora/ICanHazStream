# ICanHazStream — AI Agent Rules

This document defines the rules and conventions for AI coding assistants working in this project.

## Build System: Android Lightbuild

This project is built and managed using **Android Lightbuild** (Lightbuild 0.0.30, AGP 9.2.0, Kotlin 2.3.20, JDK 21).

### Architecture & Module Conventions
- **Multi-module project** structured via Lightbuild declarative YAML:
  - Root configuration: `project.lightbuild.yaml`
  - Per-module configuration: `<module>/lightbuild.yaml`
  - Lockfiles: `<module>/resolved.deps`
- **Application Shell (`//app`)**:
  - Thin entrypoint shell module containing only `AndroidManifest.xml` and launcher resources.
  - Declares dependency solely on `//app-core`.
  - Application modules in Lightbuild do not hold shared code or feature dependencies.
- **App Core (`//app-core`)**:
  - Central application logic and navigation library (`type: library`, namespace: `me.kartikarora.icanhazstream.core`).
  - Aggregates feature modules (`//feature/explore`, `//feature/watchlist`, `//feature/detail`, etc.) and core libraries (`//core/ui`, `//core/data`, `//core/model`).
  - Hosts MainActivity, root Composable destinations, and navigation routing.
- **Package namespace:** `me.kartikarora.icanhazstream.*`
- **Source convention:** Kotlin files in `src/main/kotlin/`, Java files in `src/main/java/`, unit tests in `src/test/kotlin/`.

### Lightbuild CLI Workflows
- **Build APK:** `lightbuild build app:app-debug.apk`
- **Run Tests:** `lightbuild test <target>` (e.g. `lightbuild test feature/watchlist:hostTest`)
- **Query Modules / Targets:** `lightbuild query //...`
- **Dependency Resolution:**
  - `lightbuild build`, `lightbuild test`, and Android Studio Canary run tasks **automatically** resolve dependencies and update `resolved.deps` when `lightbuild.yaml` changes.
  - `lightbuild resolve` is used primarily for pre-warming caches or in CI with `lightbuild resolve --locked-dependencies`.

## Brand Design System

The `:core:ui` module contains the **@kartikarora Compose Design System** with:
- `ICanHazStreamTheme` — Material 3 theme with Space Grotesk typography
- `MovieCard` — Brand-styled movie card component
- `ProviderBadge` — Streaming platform badge (Netflix, Disney+, Prime Video, Stan, Binge)
- `RatingChip` — Movie rating display chip
- `StreamTopBar` — Top app bar with brand typography

**Always use these components instead of creating raw Composables.**

## Installed AI Skill

The `kartikarora-compose-theme` skill teaches the AI about the pre-built `:core:ui` component library. When generating UI code, always import:
- `me.kartikarora.icanhazstream.ui.components.*`
- `me.kartikarora.icanhazstream.ui.theme.*`

## Testing Philosophy

- **Fakes over Mocks**: Use test fakes from `:core:testing` (e.g., `FakeMovieRepository`).
- **JUnit 5 & Turbine**: Use JUnit 5 (`org.junit.jupiter.api.*`) and Turbine (`app.cash.turbine:turbine`) for ViewModel and StateFlow unit tests.
- **Host Test Configuration**: In module `lightbuild.yaml`, declare unit test dependencies under `android.tests.test.dependencies` (`module: [//core/testing]` and `maven:` dependencies).
- **Compose UI Test**: For instrumented UI tests.

## JetBrains & Android Stack

- **Lightbuild 0.0.30** / **AGP 9.2.0** / **Kotlin 2.3.20** / **Java 21**
- **Ktor Client 2.3.9** for HTTP networking
- **kotlinx.serialization 1.6.3** for JSON parsing
- **kotlinx.coroutines 1.8.0** for async operations
