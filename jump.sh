#!/bin/bash
# ICanHazStream — Step Jump System
# Usage: ./jump.sh <step-number>
# Cumulatively applies all solution files from Step 1 up to <step-number>.

set -euo pipefail

STEP=${1:?"Usage: ./jump.sh <1-17>"}

if [[ $STEP -lt 1 || $STEP -gt 17 ]]; then
    echo "Error: Step must be between 1 and 17"
    exit 1
fi

echo "🎬 Fast-forwarding project to Step $STEP (applying all intermediate solutions)..."

# Apply all solution steps incrementally from 1 to $STEP
for s in $(seq 1 "$STEP"); do
    STEP_PADDED=$(printf '%02d' "$s")
    SOLUTION_DIR=".solutions/step-$STEP_PADDED"

    if [[ -d "$SOLUTION_DIR" ]]; then
        # Copy everything including dotfiles (.gemini, etc.)
        cp -a "$SOLUTION_DIR/." .
    fi

    # Handle legacy file removals when reaching specific migration steps
    if [[ $s -ge 4 ]]; then
        rm -f core/data/src/main/java/me/kartikarora/icanhazstream/data/legacy/WatchCostUtils.java 2>/dev/null || true
    fi

    if [[ $s -ge 5 ]]; then
        rm -f feature/explore/src/main/java/me/kartikarora/icanhazstream/explore/legacy/MovieProviderAdapter.java 2>/dev/null || true
        rm -f feature/explore/src/main/res/layout/item_movie_provider.xml 2>/dev/null || true
    fi
done

echo "✅ Successfully jumped to Step $STEP!"
echo "   All solutions from Step 01 to Step $(printf '%02d' "$STEP") have been applied."
echo "   Run './gradlew assembleDebug test' to verify."
