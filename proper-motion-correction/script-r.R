library(lubridate)


date <- ymd_hm(date_string)
start_of_year <- ymd(paste0(year(date), "-01-01 00:00"))
end_of_year <- ymd(paste0(year(date) + 1, "-01-01 00:00"))
year_fraction <- as.numeric(difftime(date, start_of_year, units = "secs")) / as.numeric(difftime(end_of_year, start_of_year, units = "secs"))
return(year(date) + year_fraction)
