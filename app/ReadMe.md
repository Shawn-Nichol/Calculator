This a math app, that contains a calculator and mini games to help improve basic arithmetic.

Calculator can currently only hand two inputs

This App contains
- Single Activity
- Architecture Components
  - DataBinding
    - Handles Calculator btn clicks
    - Uses Observables to update the screen when new values are entered by the user. 
  - MVVM 
    - ViewModel
    - Saves the following data from destroyed on screen rotation. User entered formula, Answer. 

  - WorkManager
    - Solves user entered formula on a worker thread.
    - Live Data will update the result after the work is completed. 
  - Saving States
    - Handles Process Death
      - User entered data will be saved upon process death and reload once the app becomes active again. 
      
- Navigation Component
  - NavGraph
  - Host Fragment
  - NavDrawer, allows user to select different fragments. 

- User Interface
  - Calculator Fragment uses <include/> to load a number pad and a screen to display the results. Linear
  - Layout is equally weight to split the screen into two sections. One for the number pad and one for the 
    screen.  
  - Multiple layout for different landscapes


  
  