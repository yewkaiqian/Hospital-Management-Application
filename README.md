# Hospital Management System

## Overview

The Hospital Management System is a Java-based desktop application designed to digitalise hospital administrative operations. The system provides both a graphical user interface (GUI) developed using JavaFX and a command-line interface (CLI), allowing users to manage doctors, patients, medicines, laboratories, hospital facilities, ambulances and staff records.

The application implements object-oriented programming principles and modular system design to organise hospital records and management operations into dedicated classes.

## Key Features

- **Doctor Management** – Register and view doctor records, including specialisations, qualifications, working hours and room assignments.
- **Patient Management** – Register and view patient information, including medical conditions, age, gender and admission status.
- **Medicine Management** – Maintain medicine records, manufacturers, expiry dates, costs and quantities.
- **Laboratory Management** – Register and view laboratory services and associated costs.
- **Facility Management** – Register and display available hospital facilities.
- **Ambulance Management** – Maintain ambulance records, vehicle registration numbers and passenger capacities.
- **Staff Management** – Register and view staff records, including designations and salaries.
- **Interactive Dashboard** – Access hospital management modules through a centralised JavaFX interface.
- **Input Validation** – Validate required fields, unique identifiers and numerical inputs.
- **Dual Interface Support** – Operate the system through either a graphical or command-line interface.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java 8 | Core application development |
| JavaFX | Graphical user interface development |
| Eclipse IDE | Development environment |
| UML | Object-oriented system modelling |
| Java Collections Framework | In-memory data management |

## Technical Implementation

- Object-oriented programming (OOP)
- Classes and objects
- Inheritance
- Encapsulation
- Constructor overloading
- Association and multiplicity
- Modular programming
- ArrayList collections
- Event-driven programming
- Input validation
- Reusable GUI components

## Repository Structure

| File or Directory | Description |
|---|---|
| `src/MainGUI.java` | JavaFX interface, dashboard, navigation, forms and tables |
| `src/HospitalManagement.java` | Central system logic, record management and command-line interface |
| `src/BaseRecord.java` | Parent class containing shared record attributes and methods |
| `src/Doctor.java` | Doctor management |
| `src/Patient.java` | Patient management |
| `src/Medical.java` | Medicine management |
| `src/Lab.java` | Laboratory management |
| `src/Facility.java` | Hospital facility management |
| `src/Ambulance.java` | Ambulance management |
| `src/Staff.java` | Staff management |
| `src/InputValidator.java` | Input validation and unique identifier verification |
| `.classpath` | Eclipse classpath configuration |
| `.project` | Eclipse project configuration |
| `.settings/` | Project-specific Eclipse settings |
| `Group4_BS_Assignment_UMLDiagrams.pdf` | UML class diagram and system architecture |

## System Architecture

The system separates the presentation layer, hospital management logic, data models and input validation into dedicated classes.

The `HospitalManagement` class manages hospital records using Java ArrayList collections, while the `MainGUI` class provides a JavaFX interface that interacts with the underlying management logic.

The `Doctor`, `Patient` and `Ambulance` classes inherit common attributes and methods from the `BaseRecord` class, demonstrating inheritance and code reusability.

## Graphical User Interface

The JavaFX interface includes:

- Centralised hospital management dashboard
- Interactive module navigation cards
- Structured data entry forms
- Table-based record displays
- Consistent colour schemes and interface styling
- Reusable buttons and navigation components
- Real-time date and time display
- Success, error and confirmation messages

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 with JavaFX support
- Eclipse IDE or another compatible Java development environment

### Installation and Execution

1. Clone or download this repository.
2. Open Eclipse IDE.
3. Import the project using File → Import → General → Existing Projects into Workspace.
4. Configure the project with a compatible Java 8 environment that includes JavaFX.
5. Open the `src` directory.
6. Run `MainGUI.java` as a Java Application to launch the graphical interface.

Alternatively, run `HospitalManagement.java` to launch the command-line interface.

## Individual Contributions – Yew Kai Qian

- Designed and developed the complete graphical user interface using JavaFX.
- Implemented the main dashboard and navigation across all seven hospital management modules.
- Developed interactive data entry forms and table-based record displays.
- Created reusable GUI components and consistent interface styling.
- Implemented graphical input validation and user feedback messages.
- Developed the hospital facility management module.
- Integrated the graphical user interface with the underlying hospital management logic.

## Project Documentation

Refer to the [UML Class Diagram](Group4_BS_Assignment_UMLDiagrams.pdf) for detailed information about the system's class structure, attributes, methods, inheritance and class relationships.

## Project Background

This project was developed collaboratively by undergraduate students at Universiti Tunku Abdul Rahman (UTAR) to demonstrate the practical application of object-oriented programming and graphical user interface development.

- **Project Type:** Academic Group Project
- **Development Year:** 2026
- **Programming Language:** Java
- **Development Approach:** Object-Oriented Programming
- **Application Type:** Desktop Application

## Project Limitations and Future Improvements

The current implementation is an academic prototype that stores hospital records in memory using Java ArrayList collections. Newly added records are not permanently retained after the application closes.

Potential future improvements include:

- Relational database integration
- Persistent data storage
- User authentication and role-based access control
- Record editing and deletion
- Appointment scheduling
- Patient medical history management
- Automated reporting and analytics
