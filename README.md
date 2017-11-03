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


