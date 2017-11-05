# MirrorFriendlyMinimumSpanningTree (MFMST)
The algorithm in this project will try to find the MFMST of a given graph
## Getting started
For the algorithm to work it need to know what kind of graph it is dealing with
### Graph construction
Graphs are internally built from `.uwg` (Undirected Weighted Graph) files, located in the `test_files` folder.
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
The program will run the MFMST algorithm for all files located in the `custom_test_files` folder, a subfolder the local `MirrorFriendlyMinimumSpanningTrees` folder. This folder contains a number of custom `.uwg` files. 
The course test files are located in the `test_files` folder with the same root folder. The program will not evaluate the `.uwg` files in this folder per default, as it contains a file representing a graph of significant size (`test04.uwg`). The algorithm can not deterministically solve the MFMST problem for this graph before running out of memory, and the algorithm will terminate prematurely due to a 60 second kill timer.

To run the program for just a single file, simply remove/break the loop going through the folder, or list the file path to your specific file.
