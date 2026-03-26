# 🚀 Selenium Automation Framework

## 📌 Setup & Execution Guide

Follow the below steps to set up and run the project locally:

---

## 🔽 1. Clone the Repository

```bash
git clone https://github.com/shubhamgupta23/UISeleniumFramework.git
cd <your-project-folder>
```

---

## 📦 2. Install Dependencies

Ensure all required dependencies are downloaded via Maven:

```bash
mvn clean install
```

Or you can manually check the `pom.xml` and allow your IDE to download dependencies.

---

## ▶️ 3. Run Tests via TestNG

* Navigate to `testng.xml`
* Right-click and select **Run**

> ⚠️ Make sure TestNG plugin is installed in your IDE (IntelliJ/Eclipse)

---

## 💻 4. Run Tests via Maven (Recommended)

You can execute tests using Maven command:

```bash
mvn test -Denvironment=dev
```

### 🔧 Environment Configuration

* The above command will pick:

  ```
  src/test/resources/properties/dev.properties
  ```

* You can create multiple environment files like:

    * `dev.properties`
    * `stage.properties`
    * `prod.properties`

And run using:

```bash
mvn test -Denvironment=dev
```

---

## 📁 Project Structure (Important Paths)

```plaintext
src/test/java        → Test & Page classes
src/test/resources   → Config files (TestNG, properties, log4j2)
target/              → Build output (ignored in Git)
```

---

## ⚠️ Prerequisites

* Java 17 or above
* Maven installed
* TestNG plugin installed in IDE

---

## ✅ Notes

* Ensure correct environment is passed before execution
* Logs and reports will be generated during test execution
* Avoid committing `target/`, logs, and screenshots (already in `.gitignore`)

---

## 👨‍💻 Author

Shubham Gupta
