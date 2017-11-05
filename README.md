# MirrorFriendlyMinimumSpanningTree (MFMST)
The algorithm in this project will try to find the MFMST of a given graph
## Getting started
For the algorithm to work it need to know what kind of graph it is dealing with
### Graph construction
Graphs are internally built from `.uwg` (Undirected Weighted Graph) files.
The `.uwg`  files are simply glorified plain text files of a certain layout:
```
test01.uwg
----------
3
3
1 2 1
2 3 2 
1 3 3
```
The two first lines indicate \#vertices *m* and \#edges *n*, followed by *n* lines of edge definitions.
Each number in the *n* last lines indicate *source* vertex, *target* vertex and the edge *weight*. 
For the showcased test file, the following graph is created:

<p align="center">
  <img src = MirrorFriendlyMinimumSpanningTrees/img/example_graph.png height="200">
</p>

### Test files
The program will run the MFMST algorithm for all files located in both the `test_files` folder and `custom_test_files` folder, subfolders of the local `MirrorFriendlyMinimumSpanningTrees` folder. 

Both of the test folders contain a number of custom `.uwg` files. The course test files are located in the `test_files`. 
Note that the course test files folder contains a file representing a graph of significant size (`test04.uwg`). The algorithm can not deterministically solve the MFMST problem for this graph before running out of memory, and the will terminate prematurely due to a kill timer. The algorithm will display the best candidate for MFMST at the time of termination, should the time limit be exceeded.

To run the program for just a single file, simply remove/break the loop going through the folders, or list the file path to your specific file.
