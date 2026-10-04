# Assignment 3: Bridge Pattern

- **Student:** Nursultan Maratov
- **Group:** SE-2523
- **Topic:** Option A (Drawing: Shapes & Renderers)
- **Repository URL:** https://github.com/nursultanmaratov/Assignment3-BridgePattern
- **Base Commit Hash:** `895e20deebecdbd391cb705f5a81309343ce0c4f`
- **Submitted Commit Hash:** `62d26f74a0c8faec3f1366579e40e0be0e5ce6c9`

---

## 1. Role Map

| Role | Class / Interface | Source Path | Description |
|---|---|---|---|
| **Abstraction** | `Shape` | `src/Shape.java` | Abstract base class maintaining the bridge reference |
| **Refined Abstraction A1** | `Circle` | `src/Circle.java` | Extends `Shape`, stores `radius` domain data (default 2) |
| **Refined Abstraction A2** | `Square` | `src/Square.java` | Extends `Shape`, stores `side` domain data (default 3) |
| **Implementor** | `Renderer` | `src/Renderer.java` | Interface declaring low-level rendering operations |
| **Concrete Implementor I1** | `VectorRenderer` | `src/VectorRenderer.java` | Implements vector-based rendering simulation |
| **Concrete Implementor I2** | `RasterRenderer` | `src/RasterRenderer.java` | Implements raster-based rendering simulation |
| **Concrete Implementor I3** | `AsciiRenderer` | `src/AsciiRenderer.java` | Implements ASCII-art rendering simulation (added independently in extension step) |
| **Client** | `Main` | `src/Main.java` | Configures hierarchies, executes `--demo` checks T1–T7 |

---

## 2. Key Method & Field Locations

- **Bridge Reference Field:** `protected Renderer renderer` in [`src/Shape.java`](file:///Users/nursultanmaratov/.gemini/antigravity/scratch/Assignment3_SE2523_Maratov_Nursultan/src/Shape.java#L3).
- **Bridge Constructor Injection:** `public Shape(String id, Renderer renderer)` in [`src/Shape.java`](file:///Users/nursultanmaratov/.gemini/antigravity/scratch/Assignment3_SE2523_Maratov_Nursultan/src/Shape.java#L5-L14).
- **Execution Operation:** `public abstract String execute()` in [`src/Shape.java`](file:///Users/nursultanmaratov/.gemini/antigravity/scratch/Assignment3_SE2523_Maratov_Nursultan/src/Shape.java#L28), implemented in `Circle.java` (delegates to `renderer.renderCircle(radius)`) and `Square.java` (delegates to `renderer.renderSquare(side)`).
- **Runtime Switch Method:** `public void setImplementation(Renderer renderer)` in [`src/Shape.java`](file:///Users/nursultanmaratov/.gemini/antigravity/scratch/Assignment3_SE2523_Maratov_Nursultan/src/Shape.java#L21-L26).
- **T5 Demonstration Check:** Located in [`src/Main.java`](file:///Users/nursultanmaratov/.gemini/antigravity/scratch/Assignment3_SE2523_Maratov_Nursultan/src/Main.java#L59-L86). It creates a `Circle` with `VectorRenderer`, captures reference equality (`refBefore == refAfter`), verifies domain data persistence (`id` and `radius`), switches the implementor at runtime via `setImplementation(new RasterRenderer())`, and executes again.

---

## 3. Standard Build and Run Commands

From the project root:

```bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

---

## 4. Expected Demonstration Outcomes (T1–T7)

| Check | Setup / Action | Expected Result |
|---|---|---|
| **T1** | `Circle` with `VectorRenderer` | `T1 PASS \| Circle + VectorRenderer \| result=VECTOR circle radius=2` |
| **T2** | `Circle` with `RasterRenderer` | `T2 PASS \| Circle + RasterRenderer \| result=RASTER circle radius=2` |
| **T3** | `Square` with `VectorRenderer` | `T3 PASS \| Square + VectorRenderer \| result=VECTOR square side=3` |
| **T4** | `Square` with `RasterRenderer` | `T4 PASS \| Square + RasterRenderer \| result=RASTER square side=3` |
| **T5** | Runtime switch on single `Circle` object from `VectorRenderer` to `RasterRenderer` | `T5 PASS \| sameObject=true \| stateUnchanged=true`<br>`  before=VECTOR circle radius=2 \| after=RASTER circle radius=2` |
| **T6** | `Circle` with new `AsciiRenderer` | `T6 PASS \| Circle + AsciiRenderer \| result=ASCII circle radius=2` |
| **T7** | `Square` with new `AsciiRenderer` | `T7 PASS \| Square + AsciiRenderer \| result=ASCII square side=3` |
| **Summary** | Overall status | `SUMMARY: 7/7 PASS` |

---

## 5. Defense Quick Reference

1. **Two Independent Hierarchies:**
   - **Abstraction Dimension:** Shapes (`Shape` -> `Circle`, `Square`). Defines high-level business entity and identity (`id`, `radius`, `side`).
   - **Implementation Dimension:** Rendering platforms (`Renderer` -> `VectorRenderer`, `RasterRenderer`, `AsciiRenderer`). Defines low-level drawing primitives.
   - Decoupled using **composition** instead of inheritance, avoiding a combinatorial explosion ($M + N$ classes instead of $M \times N$).

2. **Delegation Flow:**
   Client calls `circle.execute()` $\rightarrow$ `Circle` calls `this.renderer.renderCircle(this.radius)` $\rightarrow$ concrete renderer (e.g., `VectorRenderer`) formats and returns the string description.

3. **Runtime Switching (T5):**
   `setImplementation(...)` updates the internal `renderer` reference of the existing `Shape` instance. The identity of the `Circle` object (`refBefore == refAfter`) and its domain data (`id`, `radius`) remain identical, but subsequent calls to `execute()` seamlessly produce the output of the new renderer.

4. **Independent Extension (I3) & Open/Closed Principle:**
   `AsciiRenderer` was added by implementing the `Renderer` interface. None of the existing abstraction classes (`Shape`, `Circle`, `Square`) or implementor classes were modified, verified by `extension.diff`.

5. **Bridge vs Adapter:**
   - **Bridge:** Designed *up front* to let abstraction and implementation vary independently using composition.
   - **Adapter:** Applied *after the fact* to make incompatible, existing interfaces work together.
