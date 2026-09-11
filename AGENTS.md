# ICanHazStream — AI Agent Rules

This document defines the rules and conventions for AI coding assistants working in this project.

## Architecture

- **Multi-module project** using convention plugins from `build-logic/`.
- **Package namespace:** `me.kartikarora.icanhazstream.*`
- **Source convention:** Kotlin files in `src/main/kotlin/`, Java files in `src/main/java/`.

## Brand Design System

The `:core:ui` module implements the **@kartikarora v2.0.0 Tokyo Neo-Brutalist Compose Design System** (dynamic color disabled):
- `ICanHazStreamTheme` / `KartikAroraTheme` — Material 3 theme using Akihabara Cyan (`#0084C7` light / `#00F0FF` dark), Caution Yellow (`#E6B800` light / `#FFE600` dark), and Concrete / Obsidian surfaces
- Bundled **Chivo** (`FontWeight.Black` headings with `-0.03em` tracking) and **JetBrains Mono** typography in `core/ui/src/main/res/font/`
- `KartikAroraShapes`, `KartikAroraCustomShapes`, and 8dp `MaterialTheme.spacing` scale
- `TokyoCard` / `MovieCard` — Neo-brutalist card containers with 2.dp border, solid offset shadow, and hover/focus/press physics
- `BrandButton` — High-contrast tactile buttons (`Primary`, `Secondary`, `Outline`, `Ghost`, `Small`)
- `SlidingTabs` — Segmented tab bar with active cyan pill and Caution Yellow offset shadow
- `BrandStatement` — Editorial callout with 3.dp signal left border
- `StatusBadge`, `SpecCard`, `TelemetryMetric`, `FeatureCard`, `CLICommandPill`, `ArtifactRow`, `StanceView`, `KeynoteRow`, `FastTransitionSpec`
- `ProviderBadge`, `RatingChip`, `StreamTopBar`

**Always use these components and `MaterialTheme.spacing` tokens instead of creating raw Composables or hardcoded values.**

## Installed AI Skill

The `kartikarora-compose-theme` skill (installed in `.agents/skills/`) teaches the AI
about the pre-built `:core:ui` component library. When generating UI code, always import
`me.kartikarora.icanhazstream.ui.components.*` and `me.kartikarora.icanhazstream.ui.theme.*`.

## Testing Philosophy

- **Fakes over Mocks**: Use test fakes from `:core:testing` (e.g., `FakeMovieRepository`).
- **JUnit 5** for unit tests, **Turbine** for `StateFlow` testing.
- **Compose UI Test** for instrumented tests.

## JetBrains Stack

- **Ktor Client 3.6.0** for HTTP networking
- **kotlinx.serialization 1.11.0** for JSON parsing
- **kotlinx.coroutines 1.11.0** for async operations
