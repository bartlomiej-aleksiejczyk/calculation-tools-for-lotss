import pandas as pd
import random

filename = "data.csv"
n = 1000000
s = 10000
skip = sorted(random.sample(range(1, n+1), n-s))
df = pd.read_csv(filename, skiprows=skip, nrows=s)
df.to_csv("random_10k.csv", index=False)
