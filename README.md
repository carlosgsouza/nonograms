# Nonograms

Status: **DRAFT**

## References

* https://en.wikipedia.org/wiki/Nonogram
* https://en.wikipedia.org/wiki/Discrete_tomography

## Model

* Grid
* Cell
  * Filled
  * Blank
  * Unknown
* Clues

## Examples

Input

3
```

  1
x 1 2 1
2
2
1
```

Output
```
  1
x 1 2 1
2 x x _
2 _ x x
1 x _ _
```

Solving

All cells marked as `UNKNOWN`
```
  1
x 1 2 1
2 ? ? ?
2 ? ? ?
1 ? ? ?
```

Row/column has a single hint that is greater than half the lenght of the row/column
```
  1
x 1 2 1
2 ? x ?
2 ? x ?
1 ? ? ?
```

The sum of the hints plus the separators equals the lrngth of the row/cell. 
```
  1
x 1 2 1
2 x x ?
2 _ x ?
1 x ? ?
```

The row/cell has all hints satisfied. 
```
  1
x 1 2 1
2 x x _
2 _ x ?
1 x _ _
```

Complete remaining cells

```
  1
x 1 2 1
2 x x _
2 _ x x
1 x _ _
```