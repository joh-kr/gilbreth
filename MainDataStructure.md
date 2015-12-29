# Introduction #

A matrix is used to store user preferences.

Assume the data set contains 5 levels. The matrix then has 5 + 1 columns.
The first 5 columns represent the levels and the last column represents a weight and direction of the preference.

# Usage in the five stages #
## Stage 1 ##
Even if a level is excluded in the first stage, the matrix will contain a column for this level but the column always contains a 0.

## Stage 2 ##
For each level evaluated level a new row is added.

| **row** | **l1** | **l2** | **l3** | **l4** | **l5** | **weigth** |
|:--------|:-------|:-------|:-------|:-------|:-------|:-----------|
| **0**   |1       |0       |0       |0       |0       |0.5         |
| **1**   |0       |1       |0       |0       |0       |-0.5        |
Row 0 indicates that level 1 (l1) is preferred (0.5 > 0)
Row 1 indicates that level 2 (l2) is not preferred ( 0.5 < 0)
The weight is normalized, so only the relative preference between all levels of an attribute count

## Stage 3 ##
In this stage the user indicate how important the differences between two levels are to her. In this case the distinction is of high importance and a weighting factor of 6 was chosen. The selected weighting factor is multiplied with the existing weighting value.

| **row** | **l1** | **l2** | **l3** | **l4** | **l5** | **weigth** |
|:--------|:-------|:-------|:-------|:-------|:-------|:-----------|
| **0**   |1       |0       |0       |0       |0       |3           |
| **1**   |0       |1       |0       |0       |0       |-3          |
This table shows that the user prefers l1, does not care for l2 and the differentiation is of high importance.

## Stage 4 ##
For each pair-wise comparison a new row is added.

Two concepts were presented
  1. A concept with the level l1 and l4
  1. A concept with the level l2 and l5

| **row** | **l1** | **l2** | **l3** | **l4** | **l5** | **weigth** |
|:--------|:-------|:-------|:-------|:-------|:-------|:-----------|
| **2**   |-1      |1       |0       |-1      |1       |3           |

The user preferred concept two over concept 1, therefore the corresponding levels are indicated with a negative value. The levels of the preferred concept a represented with the positive value. The weight of the user's preference is indicated with the value in the weight column.