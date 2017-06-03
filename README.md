# clusterAlgorithm1D

Algorithm that can take as an input an array of Integers and can group them into N groups depending on how close they are to each other. 
This can also be called as clustering using distance as criteria.

In this case, it is used the Kmeas method. 


## Things you may want to know

### k-means clustering

Is a method of vector quantization, originally from signal processing, that is popular for cluster analysis in data mining. 
k-means clustering aims to partition n observations into k clusters in which each observation belongs to the cluster with the nearest mean,
serving as a prototype of the cluster.

-cons:

1) uniform effect: often produce clusters with relatively uniform size even if the input data have different cluster size;

2) spherical assumption hard to satisfied: correlation between features break it, would put extra weights on correlated features (should take actions depending on the problems);

3) different densities: may work poorly with clusters with different densities but spherical shape;

4) When K value not known: how to solve K? 1)for small range of K value, say 2-10, for each K value run lots of times(20-100times),take the clustering result with the lowest J (diff) value among all K values; 2) using Elbow method to decide K value; 3) GAPs; 4)decide the K down streams: decide by the purposes/goals of the projects

5) sensitive to outliers;

6) sensitive to initial points and local optimal, and there is no unique solution for a certain K value:thus run K mean for a K value lots of times(20-100times), then pick the results with lowest J;


-pros:

1) practically work well even some assumptions are broken;

2) simple, easy to implement;

3) easy to interpret the clustering results;

4) fast and efficient in terms of computational cost
