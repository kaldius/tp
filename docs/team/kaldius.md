---
layout: page
title: kaldius' Project Portfolio Page
---

### Project: DukePro(f)

DukePro(f) is a schedule-planning desktop application that assists university professors in managing their consultations.The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 42k Loc.

Given below are my contributions to the project.

* **New Feature**: Time Slot feature.
  * What it does: Allows an event to span a certain period of time.
  * Justification: Instead of having only a time field, we need events to take up a time slot in order to implement the other features we had in mind (such as list free slots and block slots).
  
* **New Feature**: Added Blocked Slot feature.  (credits: ruthpohrp for implementing blocked slot storage and checks for overlapping)
    * What it does: Blocks out a time slot so that an Event cannot be added at that time slot.
    * Justification: Users are likely to want to block out certain time slots for personal reasons. This feature allows them to do so and not have to worry about accidentally adding an Event that coincides with a blocked period.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=kaldius)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**: TimeSlot field in Event model
    * What it does: Represents a period of time within a day.
    * Justification: This allows users to specify the start and end time of their Events.

* **Documentation**:
    * User Guide:
      * Added documentation for the commands `block`[\#79](https://github.com/AY2122S1-CS2103T-T11-4/tp/commit/4c76daec14f44cf3ced372b024dc2945103103bd), `list_blocked`[#81](https://github.com/AY2122S1-CS2103T-T11-4/tp/commit/e06d6bea4b753ceca15aba312f664fbee2f3cc52) and `delete_blocked`[#92](https://github.com/AY2122S1-CS2103T-T11-4/tp/commit/1899270c5b0762a0efca1568f1e3dc44aa085c5c).
    * Developer Guide [\#172](https://github.com/AY2122S1-CS2103T-T11-4/tp/pull/172):
      * Touch-ups to overall formatting.
      * Added implementation details of the Block feature.
      * Added BlockedSlotClassDiagram (credits: CSYGalvin for diagram sketch).

* **Team-Based Tasks**:
  * Refactoring of `Person` to `Event`.
  * Refactoring of `Time` to `TimeSlot` (credits: luluyousef for refactoring `Email` to `Time`).
  * Updated User Stories and Use Cases of Developer Guide
  * Updated BetterModelClassDiagram, LogicClassDiagram, ModelClassDiagram, StorageClassDiagram of Developer Guide.
