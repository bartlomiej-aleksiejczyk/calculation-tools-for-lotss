args <- commandArgs(trailingOnly = TRUE)
arg1 <- args[1]
arg2 <- args[2]
print(paste("Argument 1:", arg1))
print(paste("Argument 2:", arg2))
df1 <- read.csv(arg1, stringsAsFactors = FALSE)
df2 <- read.csv(arg2, stringsAsFactors = FALSE)
df2 = df2.rename(columns={arg2: arg1})  
combined_df <- merge(df1, df2, by = key, all = FALSE)
write.csv(combined_df, output_file, row.names = FALSE)
}
combine_csv_files("id", "first_file.csv", "second_file.csv", "combined_file.csv")
