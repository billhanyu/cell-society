In our `Builder.java` and `Cell.java` superclass files, there were many variables that were set to be `private`. When we accessed them in children classes, we just directly accessed them without getters, so it could be very confusing where those variables are from.
In the new version, I set all the previous protected variables to private and added getters and setters. When we access them in child classes, I used `this.getXX()` instead of just `XX`. This makes the code much more readable.
The commits are 7a9aba2eb4712b939244bfadc61e9aa04cc7a6aa and b35ccc348398a96f046f56fdb9bec2994128917a.
