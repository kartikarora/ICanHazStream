# ICanHazStream — AI Agent Rules

This document defines the rules and conventions for AI coding assistants working in this project.

## Architecture

- **Multi-module project** using convention plugins from `build-logic/`.
- **Package namespace:** `me.kartikarora.icanhazstream.*`
- **Source convention:** Kotlin files in `src/main/kotlin/`, Java files in `src/main/java/`.

## Brand Design System

The `:core:ui` module contains the **@kartikarora Compose Design System** with:
- `ICanHazStreamTheme` — Material 3 theme with Space Grotesk typography
- `MovieCard` — Brand-styled movie card component
- `ProviderBadge` — Streaming platform badge (Netflix, Disney+, Prime Video, Stan, Binge)
- `RatingChip` — Movie rating display chip
- `StreamTopBar` — Top app bar with brand typography

**Always use these components instead of creating raw Composables.**

## Installed AI Skill

The `kartikarora-compose-theme` skill (installed in `.agents/skills/`) teaches the AI
about the pre-built `:core:ui` component library. When generating UI code, always import
`me.kartikarora.icanhazstream.ui.components.*` and `me.kartikarora.icanhazstream.ui.theme.*`.

## Testing Philosophy

- **Fakes over Mocks**: Use test fakes from `:core:testing` (e.g., `FakeMovieRepository`).
- **JUnit 5** for unit tests, **Turbine** for `StateFlow` testing.
- **Compose UI Test** for instrumented tests.

## JetBrains Stack

- **Ktor Client 3.5.2** for HTTP networking
- **kotlinx.serialization 1.11.0** for JSON parsing
- **kotlinx.coroutines 1.11.0** for async operations
