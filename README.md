## Touch Joystick
The touch joystick control was inspired by the UI of SpaceX Crew Dragon spaceship.
The control has a touch point that can be dragged around. You can add listeners to several properties like
- value (0.0 - 1.0)
- angle (0.0 - 360)
- x (0.0 - 1.0)
- y (0.0 - 1.0)

In addition you can lock axis to restrict movement in only on direction.
```java
joystick.setLockState(LockState.Y_LOCKED))
```
Will set the movement locked to the y-axis.
Around the drag control you will find 8 buttons that can be used to change
the value and direction by a given step size (0.001 - 10.0), which is in pixels.
And you can also set it to sticky mode which means the touch point will NOT automatically
move back to center (which is the default behavior).
One can also set the inactive- and activeColor for the control.
The more you move the touch point in one direction the more of the chevrons will show 
up on the opposite side of the touch point. This gives an indication on how
far you dragged the point. I've put this indicator on the opposite side because if
you drag the point with the finger your hand might block the view on the control.

You can also start a little demo to play around with it by executing the following command
on the command line (make sure you are on Java 11):
```java
gradle Demo
```

Donations are welcome at [Paypal](https://paypal.me/hans0l0)

## Overview
![Overview](https://raw.githubusercontent.com/HanSolo/touchjoystick/master/TouchJoystick.png)

