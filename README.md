## Comparação de Algoritmos de Ordenação

Este trabalho implementa os algoritmos Comb Sort, Gnome Sort e Bucket Sort e compara seu desempenho com Bubble Sort (com flag), Selection Sort e Cocktail Sort. <br>

Não foram utilizadas funções prontas (como List, ArrayList, Collections) nem bibliotecas externas. <br>

### Vetores utilizados: 
```bash
int[] vetor1 = {12, 18, 9, 25, 17, 31, 22, 27, 16, 13, 19, 23, 20, 30, 14, 11, 15, 24, 26, 28};

int[] vetor2 = {5, 7, 9, 10, 12, 14, 15, 17, 19, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32};

int[] vetor3 = {99, 85, 73, 60, 50, 40, 35, 30, 25, 20, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6};

```

### Resultados: 
Usamos num. de trocas e tempo (medido em ns, nativo do java) <br>
Vetor 1: 
| Algoritmo | Trocas | Tempo (ns) |
|-----------|--------:|------------:|
| Bubble    | 78      | 7700        |
| Selection | 18      | 4700        |
| Cocktail  | 78      | 7800        |
| Comb      | 22      | 4400        |
| Gnome     | 78      | 15900       |
| Bucket    | 4       | 6400        |


Vetor 2:
| Algoritmo | Trocas | Tempo (ns) |
|-----------|--------:|------------:|
| Bubble    | 0       | 900         |
| Selection | 0       | 3000        |
| Cocktail  | 0       | 500         |
| Comb      | 0       | 2200        |
| Gnome     | 0       | 600         |
| Bucket    | 0       | 5600        |

Vetor 3:
| Algoritmo | Trocas | Tempo (ns) |
|-----------|--------:|------------:|
| Bubble    | 190     | 9800        |
| Selection | 10      | 3700        |
| Cocktail  | 190     | 7700        |
| Comb      | 18      | 3200        |
| Gnome     | 190     | 21100       |
| Bucket    | 47      | 7600        |

