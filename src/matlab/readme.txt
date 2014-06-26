This folder consist of matlab source code and the data set which ere used to train and develop algorithm for detection of pothole. 



The information on the requirements can be obtained from  root/doc folder in DesignDocument.pdf and/or RequirementVerification.pdf.



Following provide description of how matlab source code were tested.



Requirement id: B.1.1

1. Implement Algorithm-I on Matlab.

2. Filter X-Axis acceleration data through Algorithm-I transfer function.


Expected Results

1. X-axis acceleration values is filtered through this transfer function, then output
should not contain any window which contains either Low-Speed or Curb Hit flag.


The filter parameters used were the one which provided closest approximation to the actualy pothol location on the data set of 32 files.




Requirement id: B.1.2

1. Implement Algorithm-II on Matlab.

2. Filter Z-Axis acceleration data through Algorithm-II transfer function.


Expected Results

1. Z-axis acceleration values is filtered through this transfer function, then output should
not contain any window which contains flags like either Smooth Road or Turn.


The filter parameters used were the one which provided closest approximation to the actualy pothol location on the data set of 32 files.




Requirement id: B.4.3

1. Implement Algorithm-III on Matlab.

2. Filter Z-Axis acceleration data through Algorithm-III transfer function.

Expected Results

1. Z-axis acceleration values is filtered through this transfer function, then output should
not contain any window which contains flags like Break and Phone Drop.


The filter parameters used were the one which provided closest approximation to the actualy pothol location on the data set of 32 files.





Requirement id: B.4.4

1. Implement Algorithm-IV on Matlab.

2. Filter Z-Axis acceleration data through Algorithm-IV transfer function.


Expected Results

1. X-axis acceleration values is filtered through this transfer function, then output should
not contain any window which contains flags like Speed bump and Joint
Expansion/Railway Crossing.


The filter parameters used were the one which provided closest approximation to the actualy pothol location on the data set of 32 files.




Requirement id: B.4.5


1. Implement Algorithm-V on Matlab.

2. Process Z-Axis acceleration data through Algorithm-V function.


Expected Results

1. Z-axis acceleration values is processed through this function, then output should not
contain any window which contains flags like High speed trivial hit.


The filter parameters used were the one which provided closest approximation to the actualy pothol location on the data set of 32 files.


