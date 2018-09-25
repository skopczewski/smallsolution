SmallSolution

Tool will add username to all ViewSets with modify permission.

Location: Put this JAR file in ILM Client installation folder.

Usage: [input file] [username] [output file]

Example 1: java -jar SmallSolution.jar MySolution.imt
- List all ViewSets in MySolution.imt

Example 2: java -jar SmallSolution.jar MySolution.imt administrator
- List all ViewSets and already set users in MySolution.imt

Example 3: java -jar SmallSolution.jar MySolution.imt administrator NewSolution.imt
- List all ViewSets and already set users in MySolution.imt
- Add administrator to users with Modify permission
- Generate new solution NewSolution.imt