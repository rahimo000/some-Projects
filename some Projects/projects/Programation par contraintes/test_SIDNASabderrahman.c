	/*      ---------------   SIDNAS ABDERRAHMAN    ----------------*/
	
	
	#include<stdio.h>
	#include<stdlib.h>
	#include<string.h>


	FILE *data; //pour pointer sur le fichier 
	int **domain; // pour pointer sur la matrice des domains
	int **tempM; // pour pointer sur la matrice des domains filtres
	int *t; // tableaux qui contien les tuples des valeur de chaque domains pour test le alldiff()  
	int sol = 0;


	main(int argc, char **argv){
		if(argc>1) data = fopen(argv[1],"r"); 				//ouvrir le fichier (lire seulment)
		else {printf("les parametres sont vide , ouverture du fichier parDefaut 'test.txt'\n"); data =fopen("test.txt","r"); 
		}
		int n = varNumbres(data); 						// stocker le nombres des domains (lire le premier lien qui indique le nombres des variables)
		t = (int*)malloc(n * sizeof(int*)); 
		affectDomains(data,n);							// lire le fichier et stocker les dans une matrice entier (**domain)
		printf("\n\n------  le domain initial -----\n\n");
		affD(domain,n);										//affiche le domains initial
		printf("\n\n------ rendre le domain arc-consistant ... -----\n\n");
		Ac_AllDiff(n-1,n);								// la fonction qui parcour tous les combinaison possible et stocker les valeur qui participe a la soulution dans le temp 
		domain = tempM;								// repointer le domain principale vers le temp
		if(sol>0)affD(domain,n);
		else printf("inconssistant");
		freeDom(tempM);
		fclose(data);
		
	}

	// virifier si le fichier text exist ou pas

	int checkFile(FILE *file){
		if(file == NULL){printf("echec d'ouverture de fichier");return 0;}
		else return 1;
	}

	//le nombre de variables (premier line)
	int varNumbres(FILE *FileData){
		int nbrVar;
		if(checkFile(FileData)){
			fscanf(FileData,"%d",&nbrVar);
			if(nbrVar > 0){
				return nbrVar;
			}
		}
		return -1;
	}

	// affecter les valeur du dommain du fichier au tableau domain (tableau 2D)
	int affectDomains(FILE *FileData,int nbrD){
		char data[nbrD][50]; 						//pour stocker le domain en chaine de caractaire
		strcpy(data[0],"");							// je veut pas le premier line qui indique le nombres des vaiable 
		// stock temporairement un line
		char temp[50];
		int i=0;
		if(checkFile(FileData)){
			// lire les information en charactaire
			while(fgets(temp,50,FileData) && i<=nbrD){				
				if(i>0){
					remouveBlanc(temp);
					strcpy(data[i],temp);
					//printf("%s \n",data[i]);	
				}
				i++;
			}
			if(i-1!=nbrD){printf("Format incorect (le nombre des dommains est inferieur du nombre de variables)");exit(1);}
			// transformation en entier
			int r =nbrD, c, j,d,s,k1,k2;char sts[3];
			// arr matrice pour stocker les dommains tel qu'il est
			int** arr = (int**)malloc(r * sizeof(int**)); //arr[r][c] ne marche pas 
	    	for (i = 0; i < r; i++){
	    		c = strlen(data[i])+1;
	        	arr[i] = (int*)malloc(c * sizeof(int));
				}
				
			//temp pour affecter just les valeur qui participe a la solution
	        int** temp_=  (int**)malloc(r+1 * sizeof(int**));
	        for (i = 0; i < r; i++){
	    		c = strlen(data[i]);
	        	temp_[i] = (int*)malloc(c * sizeof(int));
				}
				
			// affecter les valeur a -38 tel que cette valeur indique la fin du domain 
			for(i=0;i<r+1;i++){
				c = strlen(data[i]);
				for(j=0;j<c-1;j++){
					temp_[i-1][j]=-1;					// "si elle marche ne touch pas" jsp pourquoi il veut pas commance de 0 mais si je change i-1 a i il me fais un erreur 
				}
				if(i-1 >= 0)temp_[i-1][c-1]=-38;	
			}
			int b;
			i=0;
	        //convertire de char vers int
			for(i = 1; i<r+1;i++){						// la j ai commance de 1 parceque le premier line est vide (je veut pas recuperer le nombre de domain)
				k1=0;k2=0;j=0;
				
				do{
					b = (data[i][k2]!=',' && data[i][k2]!='\n') ? 1 : 0;     //j ai remplacer les espaces avec , alors si il yas un espace ou un \n il va etre 0 sinon 1
					if(b){k2++;}
					else{
						Scut(data[i],k1,k2,sts);     //fonction pour coper la chaine de caractaire avec les indices donner
						if(sts == "0"){s=0;}
						else {if(atoi(sts) != 0)s=atoi(sts);else {printf("erreur :(il y as un espace au dernier de la lign ou un caractaire alphabitique dans le lien %d)",i+1);exit(0);}}
											// fonction predifinie pour transformer une chaine de caractaire en entier
						if(s>=0){						// si s n'est pas negative alors il va entré 
							arr[i-1][j] = s;			
							j++;	
						}//arr[i-1][j] = -38;
						k2 = k2+1;				//incremontation du k1 et k2 pour stcker le nombre suivant
						k1 = k2;						
					}
				}while(data[i][k2-1]!='\n');   
				if(arr[i-1][j]!=-38)arr[i-1][j] = -38; //parceque le code de transformation du \n c est -38 (c est comme une point d'arret)
			}
			
			domain = arr;
			tempM = temp_;
		}
	}
	//affiche une matrice 
	int affD(int **D,int n){
		int i,j;
		for(i=0;i<n;i++){
			printf("D'%d ={",i);
			j = 0;
			while(D[i][j]!= -38 && D[i][j]!= -1){				//-38 indique la fin du domain original -1 indique la fin du temp 
				printf(" %d ",D[i][j]);
				j++;	
			}
			printf("}\n");
		}
		return 0;
	}
	//afiche un tableau  (just pour le test)
	int affD1(int n){
		int i;
		for(i=0;i<n;i++){
			printf("%d ",t[i]);
		}
		printf("\n");
		return 0;
	}
	//// *************** les fonction general ********************
	int Ac_AllDiff(int im,int n){
		if(im == -1){						//condition d arret
			if(alldiff(n)){
				affectToTemp(n);			//affecter les valeurs du solution trouver vers le temp
				sol++;
			}
				
			return 0;
		}
		else{
			int j=0;
			while(domain[im][j] != -38){		// affectation du tout les combinaison posibles avec la methode generated test (la methode du prof "recursive") dans le tableau t
				t[im]=domain[im][j];
				Ac_AllDiff(im-1,n);
				j++;
			};	
		}
	}

	int alldiff(int n){								//tout est claire la
		int i,j;
		for(i=0;i<n-1;i++){
			for(j=i+1;j<n;j++)
				if(t[i] == t[j]) return 0;		
		}
		return 1;
	}

	//affecter les valeur qui participe a la solution
	void affectToTemp(int n){
		int i=0,j=0;
		for(i=0;i<n;i++){
			j=0;
			while(tempM[i][j] != t[i] && tempM[i][j] !=-38 && tempM[i][j] !=-1 ){		//si la valeur exist ou elle est pas -38 ou -1 incrémenter
				j++;
			}
			if(tempM[i][j] != t[i] &&tempM[i][j] !=-38){								// sinon affecter la valeur
				tempM[i][j] = t[i];
			}
		}
	}

	//////// ******************************
	void freeDom(int **D,int r){														// liberer l allocation de memoire
		int i;
		for(i=0;i<r;i++){
			free(D[i]);
		}
		free(D);
	}

	remouveBlanc(char *temp){ //replace les espaces par , (pourquoi ? pour bien visualiser le traitement et pour la gestion facile des erreur)
		int i;
		for(i=0;i<strlen(temp);i++){
			if(temp[i] == ' ' ) temp[i]= ',';
		}
	}

	Scut(char temp[],int k1,int k2,char stringt[]){    // coper une chaine de caractaire avec des indices
		int i,n = k2-k1;								
		strcpy(stringt,"  ");							// fonction prédifinie pour remplacer avec le vide (pour ne melange pas avec les resultat précedants)
		for(i=0;i<n;i++){
			stringt[i] = temp[k1+i];
		}
	}





