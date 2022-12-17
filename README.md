Copyright 2022 Maria Sfiraiala (maria.sfiraiala@stud.acs.upb.ro)

# POO TV - Project2


<div align="left"><img src="https://i.imgur.com/S1ROjQr.gif" width="250px"></div>

## Design Patterns Used

1. Singleton Pattern

This pattern (or better called, anti-pattern) was used in order to simulate the presence of a global variable, `session`. Due to this value being required to globally maintain its value, in order for `currentUser` and  `currenPage` to be easily updated from other methods, the choice of using a Singleton pattern was obvious.

However, 