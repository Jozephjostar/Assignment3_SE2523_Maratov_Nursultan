# Assignment 3: Bridge Pattern

- **Student:** Nursultan Maratov
- **Group:** SE-2523
- **Topic:** Option A (Drawing: Shapes & Renderers)
- **Repository URL:** https://github.com/nursultanmaratov/Assignment3-BridgePattern
- **Base Commit Hash:** PENDING_BASE_COMMIT

## Role Map

| Role | Class / Interface | Source Path | Description |
|---|---|---|---|
| Abstraction | `Shape` | `src/Shape.java` | Abstract base class holding Implementor bridge |
| Refined Abstraction A1 | `Circle` | `src/Circle.java` | Extends Shape, radius domain data |
| Refined Abstraction A2 | `Square` | `src/Square.java` | Extends Shape, side domain data |
| Implementor | `Renderer` | `src/Renderer.java` | Interface for low-level render operations |
| Concrete Implementor I1 | `VectorRenderer` | `src/VectorRenderer.java` | Vector-based drawing simulation |
| Concrete Implementor I2 | `RasterRenderer` | `src/RasterRenderer.java` | Raster-based drawing simulation |
| Concrete Implementor I3 | `AsciiRenderer` | `src/AsciiRenderer.java` | ASCII art drawing simulation (extension) |
| Client | `Main` | `src/Main.java` | Runs test checks T1-T7 via `--demo` |

## Key Implementation References

- **Bridge Field:** `Shape.renderer` (`protected Renderer renderer` in `src/Shape.java`)
- **Execute Method:** `public abstract String execute()` in `src/Shape.java`, implemented in `Circle` and `Square`
- **Runtime Switch Method:** `public void setImplementation(Renderer renderer)` in `src/Shape.java`
- **T5 Demonstration Check:** Located in `Main.java` (demonstrates runtime replacement using `setImplementation` and tests reference equality `==`)

## Standard Build and Run Commands

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```
