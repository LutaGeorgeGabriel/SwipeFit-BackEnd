# Title     : TODO
# Objective : TODO
# Created by: georgegabriel
# Created on: 28/07/2017

require(NMF)
require(FNN)

N <- 20
P <- 15

# We create an empty matrix (all zeros)
# dim(m) = 20 users x 10 products
m <- matrix(data = 0, nrow = N, ncol = P)

m <- read.table(file = "/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/otherUsersData.txt")

# calculate the matrix decomposition for m
# the rank parameter to be chosen based on the number of columns (products) in matrix m
# it represents the number of meta-features that describe a user or a product
f <- nmf(m, rank = 3, method = 'lee')

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
write.table(m_suggested, file="/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/ml_output.txt", row.names=FALSE, col.names=FALSE)

