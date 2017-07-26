# Title     : TODO
# Objective : TODO
# Created by: georgegabriel
# Created on: 26/07/2017

N <- 19
P <- 10

# We create an empty matrix (all zeros)
# dim(m) = 200 users x 50 products
m <- matrix(data = 0, nrow = N, ncol = P)

# use the same seed for repeatability
set.seed(1234)

# Fill in matrix m with random likes (1 = like, 0.5 = unknown, 0 = dislike)
# in reality, we will fill in matrix m with the real like/dislike/unknown values for each user

otherUsersData <- apply(m, c(1,2), function(x) rbinom(1, 2, 0.3)/2)

write.table(otherUsersData, file="/Users/georgegabriel/Documents/licenta/SwipeFit-BackEnd/src/main/resources/otherUsersData.txt", row.names=FALSE, col.names=FALSE)