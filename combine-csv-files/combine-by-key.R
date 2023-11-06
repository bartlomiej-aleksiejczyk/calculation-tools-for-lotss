command_arguments <- commandArgs(trailingOnly = TRUE)
FIRST_FILENAME <- "first_input.csv"
SECOND_FILENAME <- "second_input.csv"

first_key <- command_arguments[1]
second_key <- command_arguments[2]

print(paste("Argument 1:", first_key))
print(paste("Argument 2:", second_key))



first_csv <- read.csv(FIRST_FILENAME, stringsAsFactors = FALSE)
second_csv <- read.csv(SECOND_FILENAME, stringsAsFactors = FALSE)
second_csv <- second_csv.rename(columns={second_key: first_key})

combined_df <- merge(first_csv, second_csv, by = key, all = FALSE)
write.csv(combined_df, output_file, row.names = FALSE)

combine_csv_files("id", "first_file.csv", "second_file.csv", "combined_file.csv")
