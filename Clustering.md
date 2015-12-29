# Introduction #

The current Versions allows clustering with the k-means algorithm using the Weka library. The number of clusters is fixed to two, this number can be changed easily.

# Links #
  * [k-means clustering algorithm](http://en.wikipedia.org/wiki/K-means)
  * [Weka 3 library](http://www.cs.waikato.ac.nz/ml/weka/index.html)

# How to install Weka 3 #
  1. Go to [Weka download page](http://www.cs.waikato.ac.nz/ml/weka/index_downloading.html).
  1. Under section "Stable book 3rd ed. version" choose "Other platforms (Linux, etc.)".
  1. Extract the files to the lib folder in acape workspace.

# View Results #
Assuming a running acape instance at the local machine, goto http://localhost:9000/application/showResultTable and scroll down.

| **Respondent** | **Cluster** |
|:---------------|:------------|
| 0              | 0           |
| 1              | 1           |
| 2              | 0           |

The first and the third respondents share a cluster while the second respondent is the only one in her cluster.

# Algorithms #
Other algorithms implemented by Weka 3 may used too. The following algorithms are supported by the library:
  * CheckClusterer
  * CLOPE
  * ClusterEvaluation
  * Cobweb
  * DBScan
  * EM
  * FarthestFirst
  * FilteredClusterer
  * HierarchicalClusterer
  * MakeDensityBasedClusterer
  * OPTICS
  * RandomizableClusterer
  * RandomizableDensityBasedClusterer
  * RandomizableSingleClustererEnhancer
  * sIB
  * **SimpleKMeans**
  * SingleClustererEnhancer
  * XMeans