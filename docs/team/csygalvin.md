---
layout: page
title: CSYGalvin's Project Portfolio Page
---

### Project: DukePro(f)

DukePro(f) is a schedule-planning desktop application that assists university professors in managing their consultations.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about XX Loc.

Given below are my contributions to the project.

* **New Feature**: List Free Slots Command
    * What it does: Goes through all events and blocked time slots in the schedule 
      and finds all free slots between them. 
      Display this list of free slots in a format that is easy to read and distribute.
    * Justification: This feature allows users to easily find free time slots in their schedule. 
      Professors can copy this list and send to students so students can choose a date and time for consultation,
      allowing them to easily schedule consultations.
    
* **New Feature**: Sorted Event List
    * What it does: Maintains the Event list of a schedule as a sorted list, sorted chronologically by date and start time of consultation.
    * Justification: This feature allows users to view their consultation chronologically instead of
      the order the user inputted the consultation.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=CSYGalvin)

* **Documentation**:
    * User Guide:
        * Added usage instructions for List Free Slots Command
    * Developer Guide:
        * Added NFR and Glossary to requirements section
        * Added implementation details of the addition of the Sorted Event List feature and List Free Slots Command

* **Team-Based Tasks**:
    * Set up team's organisation and repository
    * Enabled assertions
    * Maintained issue tracker
    * Refactor `Address` to `Location`
    * Refactor `person` to `event`
