library(lubridate)

INPUT_FILE_NAME <- "input.csv"


data_from_csv <- read.csv(INPUT_FILE_NAME)

convert_date_to_fractional <- function (date_time) {
  date_time <- ymd_hm(date_string)
  year_end <- ymd(paste0(year(date_time) + 1, "-01-01 00:00"))
  year_start <- ymd(paste0(year(date_time), "-01-01 00:00"))
  year_fraction <- as.numeric(difftime(date_time, year_start, units = "secs")) / as.numeric(difftime(year_end, year_start, units = "secs"))
  return(year(date_time) + year_fraction)
}

data_from_csv$lotss_source_date <- apply(df[, "lotss_source_date"], 1, convert_date_to_fractional)
write.csv(df, file, row.names = FALSE)
