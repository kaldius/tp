---
layout: page
title: jacobnbh's Project Portfolio Page
---

### Project: DukePro(f)

DukePro(f) is a schedule-planning desktop application that assists university professors in managing their 
consultations.The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and 
has about 42k Loc.

Given below are my contributions to the project.

* **New Feature**: Added Command Summary Command feature
  * PR(s): [\#88](https://github.com/AY2122S1-CS2103T-T11-4/tp/pull/88/commits/3fdcb4608f9152855a8dcc6ca2b2f833b70a7f75)
  * What it does: opens a window that shows a list of all available commands
  * Justification: Users may want to view of all available commands for DukePro(f) quickly with a command
* **New Feature**: Created Command Summary Page using JavaFX
  * PR(s): [\#88](https://github.com/AY2122S1-CS2103T-T11-4/tp/pull/88/commits/3fdcb4608f9152855a8dcc6ca2b2f833b70a7f75)
  * What it does: a window that displays a list of all available commands
  * Justification: New users may want to refer to the format, description and examples of each command from
  DukePro(f)'s GUI instead of opening and scrolling through the User Guide
* **New Feature**: Added colour-coded `URGENT` and `supplementary` tags
  * PR(s): [\#73](https://github.com/AY2122S1-CS2103T-T11-4/tp/pull/73/commits/10c3d96835bc7402299e2f73c05e0ef7ab959725)
  * What it does: colour codes `URGENT` tags to red and `supplementary` tags to yellow to show consultation 
    severity
  * Justification: Professors may want to tag their consultations with severity level tags to know which ones they
  should prioritise

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=jacobnbh&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**: Revamped DukePro(f)'s GUI completely
  * PR(s): [\#57](https://github.com/AY2122S1-CS2103T-T11-4/tp/pull/57/commits)
  * Before (UI Mockup):

    <img src="/docs/images/Ui(v1.1).png">
  * After:
  
    <img src="/docs/images/Ui.png">
    
      * Changed DukePro(f)'s colour theme by making major modifications to the CSS file
      * Restructured DukePro(f)'s components by editing FXML files 

* **Documentation**:
    * User Guide:
        * Added documentation for the command `command_summary`
        * Added documentation on how to utilize `URGENT` and `supplementary` tags
    * Developer Guide:
        * Added implementation details for the command 'command_summary'
        * Added the CommandSequenceCommand Sequence Diagram
        * Updated UiClassDiagram to include CommandSummaryWindow
        
* **Team-Based Tasks**:
  * Maintained issue tracker
  * Refactor `AddressBook` to `Schedule`
  * Morphing of original product (AB-3) to current product (DukePro(f))
