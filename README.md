# Kotlin Project Template README

## Overview

This is a Kotlin project template that demonstrates how to build, test, and publish a Kotlin application using Gradle. It is configured to publish packages to GitHub Packages, and it includes CI/CD workflows using GitHub Actions.

## Features

- **Kotlin Application Support**: Set up for building a Kotlin application using Gradle.
- **GitHub Packages Publishing**: Configured to publish packages to GitHub Packages.
- **Version Check on Publish**: Automatically checks for existing versions before publishing.
- **CI/CD with GitHub Actions**: Includes workflows for building, testing, and publishing the application.

## Prerequisites

- [Java Development Kit (JDK) 17](https://adoptopenjdk.net/) or later.
- [Gradle](https://gradle.org/install/) (if not using the Gradle wrapper).
- A GitHub account with access to create repositories and GitHub Packages.
- Set up a [GitHub Personal Access Token](https://github.com/settings/tokens) with the following scopes:
  - `repo`: Full control of private repositories.
  - `write:packages`: Upload and publish packages to GitHub Packages.
  - `read:packages`: Download and install packages from GitHub Packages.
  - `delete:packages` (optional): Delete packages from GitHub Packages.


## Project Structure

```
kotlin_template/
├── app/
│   ├── build.gradle.kts        # Gradle build script for the application
│   ├── checkVersion.gradle.kts  # Gradle script for checking version before publish
│   ├── projectConfig.build.kts  # Additional project configurations
├── .github/
│   └── workflows/
│       ├── main.yml             # GitHub Actions workflow for build and publication
│       └── test.yml             # GitHub Actions workflow for testing
├── settings.gradle.kts           # Gradle settings file (for multi-project builds)
└── README.md                    # Project documentation
```

## Configuration

1. **Set up your Gradle environment:**
   Ensure you have the necessary Gradle wrapper files to build the project.

2. **Configure Project IDs:**
   Set the `GROUP_ID` and `ARTIFACT_ID` in the `projectConfig.gradle.kts` file as follows:
   
   ```kotlin
   extra["GROUP_ID"] = "your.group.id"   // e.g., com.example
   extra["ARTIFACT_ID"] = "your.artifact.id" // e.g., myapp
   ```

3. **Set environment variables:**
   The following environment variables need to be defined for successful publishing:
   
   ```
   ORGANIZATION      # GitHub organization or username
   GH_USERNAME       # GitHub username
   GH_TOKEN          # Personal access token for GitHub
   ```


4. **Customize build.gradle.kts:**
   Adjust the `mainClass` and dependencies according to your project needs.

## Usage

### Build the Project

To build the project, run:

```bash
./gradlew build
```

### Run Tests

To execute the tests, run:

```bash
./gradlew test
```

### Check Version & Publish

Use the `checkVersion` task to verify if the version exists before publication, then publish the package:

```bash
./gradlew publish
```

### GitHub Actions CI/CD

Two workflows are available in the `.github/workflows/` directory:

1. **Build and Publish Workflow (`main.yml`)**
    - Manually triggered.
    - Steps: Checkout code, set up Java (Temurin JDK 17), set up Gradle, build the package, tag the release, and publish to GitHub Packages.

2. **Test Workflow (`test.yml`)**
    - Automatically runs on pull requests and pushes to `develop`, `master`, or `main`.
    - Steps: Checkout code, set up Java (Temurin JDK 17), set up Gradle, and run tests.

## Contributing

Feel free to submit issues and pull requests. Any contributions to enhance the template are welcome.

## License

This software is published under the DO WHAT THE F*CK YOU WANT TO PUBLIC LICENSE license.
