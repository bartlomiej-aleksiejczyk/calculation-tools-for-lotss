command_arguments <- commandArgs(trailingOnly = TRUE)
FIRST_FILENAME <- "first_input.csv"
SECOND_FILENAME <- "second_input.csv"
OUTPUT_FILE <- "combined_results.csv"

first_key <- "source_id"#command_arguments[1]
second_key <- "source_id"#command_arguments[2]

print(paste("Argument 1:", first_key))
print(paste("Argument 2:", second_key))



first_csv <- read.csv(FIRST_FILENAME, stringsAsFactors = FALSE)
second_csv <- read.csv(SECOND_FILENAME, stringsAsFactors = FALSE)
names(second_csv)[names(second_csv) == second_key] <- first_key

combined_df <- merge(first_csv, all = FALSE)
write.csv(combined_df, OUTPUT_FILE, row.names = FALSE)
