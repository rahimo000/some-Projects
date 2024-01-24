#include <stdio.h>
#include <stdlib.h>
#define INF 999;

//représente les arc et leur valeur , sommet de depar et sommet d arrivet
typedef struct Edge
{
    int source, destination, weight;
}Edge;


typedef struct Graph
{
    int V, E;
    // V est le nombre de sommets et E le nombre d'arcs.
    Edge *edge;
}Graph;


//creation d un graph
Graph *createGraph(int V, int E)
{
    Graph *graph = (Graph *)malloc(sizeof(Graph));
    graph->V = V; 
    graph->E = E;
    graph->edge = (Edge *)malloc(graph->E * sizeof(Edge));
    return graph;
}

int aficherSoulution(int dist[], int n)
{
    
    printf("\nsommet d'arrivee'x\t\t\t\tDistance\n");
    int i;

    for (i = 0; i < n; ++i)
    {
        printf("%d \t\t\t\t\t\t %d\n", i, dist[i]);
    }
}

void BellmanFord(Graph *graph, int source)
{
    int V = graph->V;

    int E = graph->E;

    int StoreDistance[V];

    int i, j;

    // nous initialisons toutes les distances a l'infini sauf la source.
    // Nous attribuons la distance de la source a  0

    for (i = 0; i < V; i++)
        StoreDistance[i] = INF;

    StoreDistance[source] = 0;

    
    for (i = 1; i <= V - 1; i++)
    {
        for (j = 0; j < E; j++)
        {
            int u = graph->edge[j].source;
            int v = graph->edge[j].destination;
            int weight = graph->edge[j].weight;

            if (StoreDistance[u] + weight < StoreDistance[v])
                StoreDistance[v] = StoreDistance[u] + weight;
        }
    }
    //         verifier les cycles negatives
    for (i = 0; i < E; i++)
    {
        int u = graph->edge[i].source;
        int v = graph->edge[i].destination;
        int weight = graph->edge[i].weight;

        if (StoreDistance[u] + weight < StoreDistance[v])
            printf("Ce graphe contient un cycle negatif\n");
    }

    aficherSoulution(StoreDistance, V);

    return;
}

int main()
{
    int V, E, S; // V = nombre de sommets, E = nombre d'aretes, S est le sommet source.
	
    printf("Enter le nombres des sommets: ");
    scanf("%d", &V);

    printf("Enter le nombre des arc : ");
    scanf("%d", &E);

    printf("Enter le nombre de sommet de depart (le choix de 0 a %d): ",V-1);
    scanf("%d", &S);

    struct Graph *graph = createGraph(V, E); // appeler la fonction pour allouer de l'espace a ces nombreux sommets et aretes

    int i;
    for (i = 0; i < E; i++)
    {
        printf("\nEnter les propietés de l'arc %d:\n  Source: ", i +1);
        scanf("%d", &graph->edge[i].source);
        printf("destination : ");
        scanf("%d", &graph->edge[i].destination);
        printf("valeur de l'arc : ");
        scanf("%d", &graph->edge[i].weight);
    }

    BellmanFord(graph, S); // passage du graphe cree et du sommet source Ã  la fonction Algorithme BellmanFord

    return 0;
}
