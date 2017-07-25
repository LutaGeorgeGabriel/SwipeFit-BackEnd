# Overnight calculation of suggestions for all users based on their previous activity

# install.packages('NMF', 'FNN')

options(repos="https://cran.rstudio.com" )

install.packages("NMF")
library(NMF)
install.extras('NMF')

#install.packages("FNN")
#library(FNN)

#install.packages("xlsx")
#library(xlsx)

#install.packages('rJava')

require(NMF)
require(FNN)
require(rJava)

N <- 200
P <- 50

# We create an empty matrix (all zeros)
# dim(m) = 200 users x 50 products
m <- matrix(data = 0, nrow = N, ncol = P)

# use the same seed for repeatability
set.seed(1234)

# Fill in matrix m with random likes (1 = like, 0.5 = unknown, 0 = dislike)
# in reality, we will fill in matrix m with the real like/dislike/unknown values for each user
m <- apply(m, c(1,2), function(x) rbinom(1, 2, 0.3)/2)

# calculate the matrix decomposition for m
# the rank parameter to be chosen based on the number of columns (products) in matrix m
# it represents the number of meta-features that describe a user or a product
f <- nmf(m, rank = 5, method = 'lee')

# extract the user-feature and product-feature matrices
a <- basis(f)
b <- coef(f)


# which are the closest other k users similar to each user
kn <- get.knnx(a, query = a, k = 10)

m_suggested <- matrix(0, nrow = N, ncol = P)

# which are the preferences of those kn closest neighbor users to user u ?
for (i in 1:N)
{
  # calculate preference means for each user
  km <- colMeans(m[setdiff(kn$nn.index[i, ], i), ])
  
  # replace already seen products with 0 and unseen products with their suggested score
  sugg <- ifelse(m[i, ] == 0.5, km, 0)
  
  # sort suggested products by the decreasing score
  m_suggested[i, ] <- order(sugg, decreasing = T)
}


# m_suggested matrix contains pre-calculated suggestions for all users.
# row X means that user X should be shown product IDs in this order

# we write the m_suggested matrix to disk so it can be pre-loaded into the frontend application
write.csv(m_suggested, '/Users/georgegabriel/Documents/machine_learning/m_suggested.csv')

# export to txt file
write.table(m_suggested, file="/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/mymatrix.txt", row.names=FALSE, col.names=FALSE)