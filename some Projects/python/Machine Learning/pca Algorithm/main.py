# Importation des bibliothèques nécessaires
import pandas as pd
from sklearn.decomposition import PCA
import matplotlib.pyplot as plt

# Chargement des données à partir d'un fichier CSV
data = pd.read_csv('data.csv')

# Affichage des premières lignes des données pour vérifier l'importation
print(data.head())

# Normalisation des données
normalized_data = (data - data.mean()) / data.std()

# Création d'un objet PCA avec 2 composantes principales
pca = PCA(n_components=2)

# Calcul des composantes principales
principal_components = pca.fit_transform(normalized_data)

# Création d'un dataframe pour les composantes principales
principal_df = pd.DataFrame(data=principal_components, columns=['PC1', 'PC2'])

# Affichage des premières lignes du dataframe des composantes principales
print(principal_df.head())

# Affichage de la proportion de la variance expliquée par chaque composante principale
print(pca.explained_variance_ratio_)

# Affichage du graphe des composantes principales
plt.scatter(principal_df['PC1'], principal_df['PC2'])
plt.xlabel('Composante Principale 1')
plt.ylabel('Composante Principale 2')
plt.title('Graphe des Composantes Principales')
plt.show()