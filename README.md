# Cross-Platform Application Architecture — Bridge Pattern

## 1. Architecture Overview
This project demonstrates the **Bridge** structural design pattern in Java by decoupling high-level user-facing software applications (**Abstraction**) from the underlying low-level operating system platforms (**Implementor**).

By separating the two hierarchies, applications and operating systems can vary independently without creating an exponential Cartesian product of classes ($M \times N \to M + N$).

* **Abstraction (`Application`)**: 
  - Abstract base class maintaining a reference to the `OperatingSystem` implementor.
  - Defines the core lifecycle method `openApp()` and supports runtime platform switching via `setOs(OperatingSystem os)`.
* **Refined Abstractions**:
  - `WebBrowser`: Implements web browsing logic, URL validation, and home page rendering.
  - `IDE`: Implements workspace indexing and software project creation.
* **Implementor (`OperatingSystem`)**:
  - Interface declaring low-level platform primitives: `openWindow(String title)` and `showInfo()`.
* **Concrete Implementors**:
  - `Windows`: Simulates Windows 11 platform drawing and system details.
  - `Linux`: Simulates Ubuntu Linux / Wayland compositor operations.
  - `MacOS`: Simulates macOS 27 Golden Gate interface operations.
* **Client / Entry Point (`Main`)**:
  - Instantiates concrete implementations and refines abstractions, proving dynamic runtime OS migration without altering abstraction code.

---

## 2. Clean Code Justification (Annotated Code Excerpts)

### 1. Clear Separation of Responsibilities (Decoupling & No Leaking)
The client and abstraction interact strictly through high-level operations (`browse`, `createProject`), while platform-specific window rendering and OS versioning remain completely encapsulated inside `OperatingSystem` implementations.

```java
// File: src/application/Application.java

public abstract class Application {
    protected String name;
    // Bridge reference: Abstraction delegates low-level platform work to the Implementor
    protected OperatingSystem os;

    public Application(String name, OperatingSystem os){
        this.name = name;
        this.os = os;
    }

    // Enables dynamic runtime switching of implementation
    public void setOs(OperatingSystem os){
        this.os = os;
    }

    public abstract void openApp();
}
```

### 2. Meaningful, Intention-Revealing Names
Class and method names immediately communicate their role within the design pattern and domain without requiring the reader to inspect implementation details:
- Abstraction side clearly identifies software products: `Application`, `WebBrowser`, `IDE`.
- Implementor side distinctly designates platforms: `OperatingSystem`, `Windows`, `Linux`, `MacOS`.
- Domain verbs (`openWindow`, `browse`, `createProject`, `showInfo`) clearly specify intent.

```java
// File: src/operatingsystem/OperatingSystem.java

public interface OperatingSystem {
    // Parameterized primitive: window title is supplied directly, avoiding output fragmentation
    void openWindow(String title);
    void showInfo();
}
```

### 3. Small, Focused Classes & Single Responsibility Principle (SRP)
Every class is concise (< 30 lines) with a single, well-defined reason to change. `WebBrowser` only manages browser-specific logic, while `Windows` exclusively handles Windows platform routines.

```java
// File: src/operatingsystem/Windows.java

public class Windows implements OperatingSystem {
    @Override
    public void openWindow(String title) {
        System.out.println("Opening window with Windows interface: " + title);
    }

    @Override
    public void showInfo() {
        System.out.println("Operating system: Windows\nVersion: \"Windows 11\"");
    }
}
```

### 4. Input Validation & Fail-Fast Principle (Guard Clauses)
Domain invariants are asserted at the earliest possible moment. In `WebBrowser`, invalid URLs are rejected immediately before attempting any platform rendering, preventing invalid system state.

```java
// File: src/application/WebBrowser.java

public void browse(String url){
    // Guard clause: validates URL scheme before invoking low-level OS operations
    if (url.strip().toLowerCase().startsWith("https://")){
        this.os.openWindow(name);
        System.out.println("Readdressing to " + url + "...");
        System.out.println();
    } else {
        throw new IllegalArgumentException("Invalid url address");
    }
}
```

### 5. Backward-Compatible Design & Open/Closed Principle (OCP)
Adding a new operating system (e.g., `AndroidOS` or `FreeBSD`) requires creating a single new class implementing `OperatingSystem`. Zero changes are needed in `Application`, `WebBrowser`, or `IDE`. Furthermore, runtime switching allows seamless cross-platform execution.

```java
// File: src/Main.java

WebBrowser chrome = new WebBrowser("Google Chrome", windows);
chrome.openApp();
chrome.browse("Https://youtube.com");

// Runtime implementation switch: same abstraction instance now executes on Linux
chrome.setOs(linux);
chrome.browse("https://github.com");
```

---

## 3. Project Structure

```text
src/
├── Main.java
├── application/
│   ├── Application.java       # Base Abstraction
│   ├── IDE.java               # Refined Abstraction
│   └── WebBrowser.java        # Refined Abstraction
└── operatingsystem/
    ├── Linux.java             # Concrete Implementor
    ├── MacOS.java             # Concrete Implementor
    ├── OperatingSystem.java   # Implementor Interface
    └── Windows.java           # Concrete Implementor
```

---

## 4. How to Compile & Run

### Windows (PowerShell)
```powershell
# Compilation
javac -d out src/operatingsystem/*.java src/application/*.java src/Main.java

# Execution
java -cp out Main
```

### Linux / macOS (Bash)
```bash
# Compilation
javac -d out $(find src -name "*.java")

# Execution
java -cp out Main
```

### Sample Output
```text
Opening window with Windows interface: Google Chrome
Opening default home page: https://google.com
Operating system: Windows
Version: "Windows 11"

Opening window with Windows interface: Google Chrome
Readdressing to Https://youtube.com...

Opening window with Linux interface: Google Chrome
Readdressing to https://github.com...

Opening window with MacOS interface: InteliJ IDEA
Indexing project workspace files...

Operating system: MacOS
Version: "27 Golden Gate"

Opening window with MacOS interface: InteliJ IDEA
Project: bridgePattern created

Opening window with Linux interface: InteliJ IDEA
Project: Example created
```