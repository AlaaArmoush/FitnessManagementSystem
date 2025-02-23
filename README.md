
# Fitness Management System Backend 💪

Maven-based backend prototype of a comprehensive fitness management system. Designed using Agile methodologies, Test-Driven Development (TDD), and Continuous Integration (CI) practices, the project features a modular design with clear separation of concerns, while ensuring maintainability through a robust, modular architecture and adherence to best practices.


## Overview

The system supports three distinct user roles:

- Admin: Manage users, programs, content, subscriptions, and monitor overall activity. 🛠️
- Instructor: Create and manage fitness programs, interact with clients, and track client progress. 🏋️‍♂️
- Client: Authenticate, create and manage profiles, explore fitness programs, track progress, and provide feedback. 🏃‍♀️

Developed with an iterative Agile workflow, the project follows a Gherkin-first approach—defining features in plain language (see the attached Gherkin feature files) before implementing tests and code. This approach ensured all requirements were captured early and validated via automated tests. 
## Features

- User Authentication & Role Management: Secure login with role-specific navigation. 🔐
- Program Management: Creation, updating, deletion, and scheduling of fitness programs. 📆
- Progress Tracking: Clients can monitor BMI, weight changes, achievements, and badges. 🏅
- Subscription Handling: Create, update, view, and delete subscription plans tailored to user needs. 💳
- Content & Feedback Management: Manage articles, reviews, and feedback for continuous improvement. 📝
- Client-Instructor Interaction: Direct messaging, discussion forums, and progress report distribution. 🤝
- Reports & Analytics: Generate program rankings, revenue reports, attendance, and progress analytics. 📊
## Tech Stack

- Java & Maven: Core development language and build tool. ☕️
- JUnit & TDD: Extensive unit testing driven by Test-Driven Development. 🧪
- Cucumber & Gherkin: For defining behavior specifications before development. 📝
- SonarQube: Continuous code quality and refactoring analysis. 🧐
- CI/CD Pipelines: Automated builds, tests, and deployments for rapid and reliable releases. 🚀

## Gherkin-First Approach

The project was developed by first defining user stories and behaviors in Gherkin syntax. This enabled:

- Clear Requirements: Stakeholders can review and understand expected behavior through human-readable scenarios.
- Test-Driven Development: Tests are written based on Gherkin scenarios before code implementation, ensuring feature compliance.

## Setup & Installation

1. Clone the Repository:
```
git clone https://github.com/AlaaArmoush/Fitness-Management-System.git
cd cd Fitness-Management-System
```

2. Build the Project: Use Maven to compile and run tests:
```
mvn clean install
```

3. Configure CI/CD: Ensure your CI/CD pipeline (e.g., Jenkins, GitHub Actions, or GitLab CI) is set up to trigger Maven builds and run SonarQube analysis on every commit.
```
mvn exec:java -Dexec.mainClass="FeaturesMain.main"
```

4. Run the Application:
```
mvn exec:java -Dexec.mainClass="FeaturesMain.main"
```
