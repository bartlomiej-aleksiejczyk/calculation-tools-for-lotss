#!/usr/bin/env Rscript
options(warn=0)
if (require(FITSio)){
    install.packages("FITSio", dependencies = TRUE)
    library("FITSio", character.only = TRUE)
}

raw_output <- readFITS("fits-input.fits")

data_frame_output <- data.frame(lapply(raw_output$col, unlist))
colnames(data_frame_output) <- raw_output$colNames
write.csv(data_frame_output, "output-unchanged-names.csv", row.names=TRUE)
